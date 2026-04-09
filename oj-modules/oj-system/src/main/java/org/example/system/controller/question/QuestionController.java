package org.example.system.controller.question;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.core.controller.BaseController;
import org.example.common.core.domain.R;
import org.example.common.core.domain.TableDataInfo;
import org.example.system.domain.question.dto.QuestionAddDTO;
import org.example.system.domain.question.dto.QuestionQueryDTO;
import org.example.system.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@Tag(name = "题目管理接口")
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/list")
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO) {
        return getTableDataInfo(questionService.list(questionQueryDTO));
    }

    @PostMapping("/add")
    public R<Void> add(@RequestBody QuestionAddDTO questionAddDTO) {
        return toR(questionService.add(questionAddDTO));
    }
}
