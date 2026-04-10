package org.example.friend.service.question;

import org.example.common.core.domain.TableDataInfo;
import org.example.friend.domain.question.dto.QuestionQueryDTO;
import org.example.friend.domain.question.vo.QuestionDetailVO;

public interface IQuestionService {

    TableDataInfo list(QuestionQueryDTO questionQueryDTO);

    QuestionDetailVO detail(Long questionId);

    String preQuestion(Long questionId);

    String nextQuestion(Long questionId);
}
