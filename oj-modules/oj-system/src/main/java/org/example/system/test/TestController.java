package org.example.system.test;


import lombok.extern.slf4j.Slf4j;
import org.example.common.redis.service.RedisService;
import org.example.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/redisAddAndGet")
    public String redisAddAndGet() {
        SysUser sysUser = new SysUser();
        sysUser.setUserAccount("redisTest");
        redisService.setCacheObject("u", sysUser);

        SysUser us = redisService.getCacheObject("u", SysUser.class);
        return us.toString();
    }

    @GetMapping("/log")
    public String log() {
        for(int i = 0; i < 100; i++) {
            log.info("我是info级别的日志");
        }
        //log.error("我是error级别的日志");
        return "日志测试";
    }
}
