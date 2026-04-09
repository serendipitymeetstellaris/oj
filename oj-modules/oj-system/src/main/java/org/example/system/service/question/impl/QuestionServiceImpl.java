package org.example.system.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import org.example.common.core.domain.TableDataInfo;
import org.example.common.core.enums.ResultCode;
import org.example.common.security.exception.ServiceException;
import org.example.system.domain.question.Question;
import org.example.system.domain.question.dto.QuestionAddDTO;
import org.example.system.domain.question.dto.QuestionQueryDTO;
import org.example.system.domain.question.vo.QuestionVO;
import org.example.system.mapper.question.QuestionMapper;
import org.example.system.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionVO> list(QuestionQueryDTO questionQueryDTO) {
        PageHelper.startPage(questionQueryDTO.getPageNum(), questionQueryDTO.getPageSize());
        return questionMapper.selectQuestionList(questionQueryDTO);
    }

    @Override
    public int add(QuestionAddDTO questionAddDTO) {
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>()
                .eq(Question::getTitle, questionAddDTO.getTitle()));
        if (CollectionUtil.isNotEmpty(questionList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        Question question = new Question();
        BeanUtil.copyProperties(questionAddDTO, question);
        return questionMapper.insert(question);
    }
}