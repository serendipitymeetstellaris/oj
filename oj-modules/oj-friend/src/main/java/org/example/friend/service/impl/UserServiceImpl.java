package org.example.friend.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.core.constants.CacheConstants;
import org.example.common.core.constants.Constants;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.enums.UserIdentity;
import org.example.common.core.enums.UserStatus;
import org.example.common.message.service.AliSmsService;
import org.example.common.redis.service.RedisService;
import org.example.common.security.exception.ServiceException;
import org.example.common.security.service.TokenService;
import org.example.friend.domain.User;
import org.example.friend.domain.dto.UserDTO;
import org.example.friend.mapper.UserMapper;
import org.example.friend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AliSmsService aliSmsService;

    @Autowired
    private RedisService redisService;

    @Value("${sms.code-expiration:5}")
    private Long phoneCodeExpiration;

    @Value("${sms.send-limit:3}")
    private Integer sendLimit;

    @Value("${sms.is-send:false}")
    private boolean isSend;  //开关打开：true  开关关闭false

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean sendCode(UserDTO userDTO) {
        if (!checkPhone(userDTO.getPhone())) {
            throw new ServiceException(ResultCode.FAILED_USER_PHONE);
        }
        String phoneCodeKey = getPhoneCodeKey(userDTO.getPhone());
        Long expire = redisService.getExpire(phoneCodeKey, TimeUnit.SECONDS);
        if (expire != null && (phoneCodeExpiration * 60 - expire) < 60 ){
            throw new ServiceException(ResultCode.FAILED_FREQUENT);
        }
        //每天的验证码获取次数有一个限制  50次  第二天  计数清0 重新开始计数     计数  怎么存  存在哪
        //操作这个次数数据频繁   、 不需要存储、  记录的次数 有有效时间的（当天有效） redis  String  key：c:t:手机号
        //获取已经请求的次数  和50 进行比较     如果大于限制抛出异常。如果不大于限制，正常执行后续逻辑，并且将获取计数 + 1
        String codeTimeKey = getCodeTimeKey(userDTO.getPhone());
        Long sendTimes = redisService.getCacheObject(codeTimeKey, Long.class);
        if (sendTimes != null && sendTimes >= sendLimit) {
            throw new ServiceException(ResultCode.FAILED_TIME_LIMIT);
        }
        String code = isSend ? RandomUtil.randomNumbers(6) : Constants.DEFAULT_CODE;
        //存储到redis  数据结构：String  key：p:c:手机号  value :code
        redisService.setCacheObject(phoneCodeKey, code, phoneCodeExpiration, TimeUnit.MINUTES);
        if (isSend) {
            boolean sendMobileCode = aliSmsService.sendMobileCode(userDTO.getPhone(), code);
            if (!sendMobileCode) {
                throw new ServiceException(ResultCode.FAILED_SEND_CODE);
            }
        }
        redisService.increment(codeTimeKey);
        if (sendTimes == null) {  //说明是当天第一次发起获取验证码的请求
            long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0));
            redisService.expire(codeTimeKey, seconds, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public String codeLogin(String phone, String code) {
        checkCode(phone, code);
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (user == null) {  //新用户
            //注册逻辑
            user = new User();
            user.setPhone(phone);
            user.setStatus(UserStatus.Normal.getValue());
            userMapper.insert(user);
        }
        return tokenService.createToken(user.getUserId(), secret, UserIdentity.ORDINARY.getValue(), user.getNickName());
//        if (user != null) {  //说明是老用户
//            String phoneCodeKey = getPhoneCodeKey(phone);
//            String cacheCode = redisService.getCacheObject(phoneCodeKey, String.class);
//            if (StrUtil.isEmpty(cacheCode)) {
//                throw new ServiceException(ResultCode.FAILED_INVALID_CODE);
//            }
//            if (!cacheCode.equals(code)) {
//                throw new ServiceException(ResultCode.FAILED_ERROR_CODE);
//            }
//            //验证码比对成功
//            redisService.deleteObject(phoneCodeKey);
//            return tokenService.createToken(user.getUserId(), secret, UserIdentity.ORDINARY.getValue(), user.getNickName());
//        }
    }

    private void checkCode(String phone, String code) {
        String phoneCodeKey = getPhoneCodeKey(phone);
        String cacheCode = redisService.getCacheObject(phoneCodeKey, String.class);
        if (StrUtil.isEmpty(cacheCode)) {
            throw new ServiceException(ResultCode.FAILED_INVALID_CODE);
        }
        if (!cacheCode.equals(code)) {
            throw new ServiceException(ResultCode.FAILED_ERROR_CODE);
        }
        //验证码比对成功
        redisService.deleteObject(phoneCodeKey);
    }

    public static boolean checkPhone(String phone) {
        Pattern regex = Pattern.compile("^1[2|3|4|5|6|7|8|9][0-9]\\d{8}$");
        Matcher m = regex.matcher(phone);
        return m.matches();
    }

    private String getPhoneCodeKey(String phone) {
        return CacheConstants.PHONE_CODE_KEY + phone;
    }

    private String getCodeTimeKey(String phone) {
        return CacheConstants.CODE_TIME_KEY + phone;
    }
}
