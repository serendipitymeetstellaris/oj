package org.example.system.service.question;

import org.example.system.domain.question.dto.QuestionAddDTO;
import org.example.system.domain.question.dto.QuestionEditDTO;
import org.example.system.domain.question.dto.QuestionQueryDTO;
import org.example.system.domain.question.vo.QuestionDetailVO;
import org.example.system.domain.question.vo.QuestionVO;

import java.util.List;

public interface IQuestionService {

    List<QuestionVO> list(QuestionQueryDTO questionQueryDTO);

    int add(QuestionAddDTO questionAddDTO);

    QuestionDetailVO detail(Long questionId);

    int edit(QuestionEditDTO questionEditDTO);

    int delete(Long questionId);
}
