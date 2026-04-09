package org.example.system.service.exam.impl;

import com.github.pagehelper.PageHelper;
import org.example.system.domain.exam.dto.ExamQueryDTO;
import org.example.system.domain.exam.vo.ExamVO;
import org.example.system.mapper.exam.ExamMapper;
import org.example.system.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
