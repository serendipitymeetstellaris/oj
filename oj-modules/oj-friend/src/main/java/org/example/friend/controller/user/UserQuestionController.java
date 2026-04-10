package org.example.friend.controller.user;

import org.example.common.core.controller.BaseController;
import org.example.common.core.domain.R;
import org.example.friend.domain.user.dto.UserSubmitDTO;
import org.example.friend.domain.user.vo.UserQuestionResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/question")
public class UserQuestionController extends BaseController {

    //用户代码提交   请求方法  地址  参数  响应数据结构
    @PostMapping("/submit")
    public R<UserQuestionResultVO> submit(@RequestBody UserSubmitDTO submitDTO) {
        return null;
    }
}
