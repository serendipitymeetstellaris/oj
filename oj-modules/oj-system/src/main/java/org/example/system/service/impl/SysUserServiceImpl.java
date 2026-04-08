package org.example.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.core.domain.R;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.enums.UserIdentity;
import org.example.common.security.exception.ServiceException;
import org.example.common.security.service.TokenService;
import org.example.system.domain.SysUser;
import org.example.system.domain.SysUserSaveDTO;
import org.example.system.mapper.SysUserMapper;
import org.example.system.service.ISysUserService;
import org.example.system.util.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RefreshScope
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public R<String> login(String userAccount, String password) {
        //通过账号去数据库中查询，对应的用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper
                .select(SysUser::getUserId, SysUser::getPassword).eq(SysUser::getUserAccount, userAccount));
        if (sysUser == null) {
            return R.fail(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        if (BCryptUtils.matchesPassword(password, sysUser.getPassword())) {
            return R.ok(tokenService.createToken(sysUser.getUserId(), secret, UserIdentity.ADMIN.getValue()));
        }
        return R.fail(ResultCode.FAILED_LOGIN);
    }

    @Override
    public int add(SysUserSaveDTO sysUserSaveDTO) {
        //重复
        //将dto转为实体
        List<SysUser> sysUserList = sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserAccount, sysUserSaveDTO.getUserAccount()));

        if (CollectionUtil.isNotEmpty(sysUserList)) {
            //用户已经存在
            throw new ServiceException(ResultCode.AILED_USER_EXISTS);
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserAccount(sysUserSaveDTO.getUserAccount());
        sysUser.setPassword(BCryptUtils.encryptPassword(sysUserSaveDTO.getPassword()));
        return sysUserMapper.insert(sysUser);
    }
}
