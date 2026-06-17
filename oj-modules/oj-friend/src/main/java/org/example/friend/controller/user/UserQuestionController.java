package org.example.friend.controller.user;

import org.example.api.domain.vo.UserQuestionResultVO;
import org.example.common.core.controller.BaseController;
import org.example.common.core.domain.R;
import org.example.friend.domain.user.dto.UserSubmitDTO;
import org.example.friend.service.user.IUserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/question")
public class UserQuestionController extends BaseController {

    @Autowired
    private IUserQuestionService userQuestionService;

    //用户代码提交   请求方法  地址  参数  响应数据结构
    @PostMapping("/submit")
    public R<UserQuestionResultVO> submit(@RequestBody UserSubmitDTO submitDTO) {
        return userQuestionService.submit(submitDTO);
    }

    @GetMapping("/exe/result")
    public  R<UserQuestionResultVO> exeResult(Long examId, Long questionId, String currentTime) {
        return R.ok(userQuestionService.exeResult(examId, questionId, currentTime));
    }
}
