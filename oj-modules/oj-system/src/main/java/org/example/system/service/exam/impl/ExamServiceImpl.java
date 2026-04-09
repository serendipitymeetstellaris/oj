package org.example.system.service.exam.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import org.example.common.core.enums.ResultCode;
import org.example.common.security.exception.ServiceException;
import org.example.system.domain.exam.Exam;
import org.example.system.domain.exam.dto.ExamAddDTO;
import org.example.system.domain.exam.dto.ExamQueryDTO;
import org.example.system.domain.exam.vo.ExamVO;
import org.example.system.mapper.exam.ExamMapper;
import org.example.system.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<ExamVO> list(ExamQueryDTO examQueryDTO) {
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
        return examMapper.selectExamList(examQueryDTO);
    }

    @Override
    public int add(ExamAddDTO examAddDTO) {
        List<Exam> examList = examMapper
                .selectList(new LambdaQueryWrapper<Exam>().eq(Exam::getTitle, examAddDTO.getTitle()));
        if (CollectionUtil.isNotEmpty(examList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        if (examAddDTO.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResultCode.EXAM_START_TIME_BEFORE_CURRENT_TIME);  //竞赛开始时间不能早于当前时间
        }
        if (examAddDTO.getStartTime().isAfter(examAddDTO.getEndTime())) {
            throw new ServiceException(ResultCode.EXAM_START_TIME_AFTER_END_TIME);
        }
        Exam exam = new Exam();
        BeanUtil.copyProperties(examAddDTO, exam);
        return examMapper.insert(exam);
    }
}
