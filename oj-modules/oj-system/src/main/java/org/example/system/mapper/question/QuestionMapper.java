package org.example.system.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.system.domain.question.Question;
import org.example.system.domain.question.dto.QuestionQueryDTO;
import org.example.system.domain.question.vo.QuestionVO;


import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {

    List<QuestionVO> selectQuestionList(QuestionQueryDTO questionQueryDTO);
}
