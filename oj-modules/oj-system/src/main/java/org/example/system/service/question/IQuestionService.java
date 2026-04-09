package org.example.system.service.question;

import org.example.system.domain.question.dto.QuestionQueryDTO;
import org.example.system.domain.question.vo.QuestionVO;

import java.util.List;

public interface IQuestionService {

    List<QuestionVO> list(QuestionQueryDTO questionQueryDTO);
}
