package org.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.core.domain.R;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.enums.UserIdentity;
import org.example.common.security.service.TokenService;
import org.example.system.domain.SysUser;
import org.example.system.mapper.SysUserMapper;
import org.example.system.service.ISysUserService;
import org.example.system.util.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

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
}
