package org.example.friend.test;

import org.example.common.core.controller.BaseController;
import org.example.common.core.domain.R;
import org.example.common.message.service.AliSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private AliSmsService aliSmsService;

    @GetMapping("/sendCode")
    public R<Void> sendCode(String phone, String code) {
        return toR(aliSmsService.sendMobileCode(phone, code));
    }
}
