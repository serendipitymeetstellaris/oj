package org.example.system.mapper.exam;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.system.domain.exam.Exam;
import org.example.system.domain.exam.dto.ExamQueryDTO;
import org.example.system.domain.exam.vo.ExamVO;

import java.util.List;

public interface ExamMapper extends BaseMapper<Exam> {

    List<ExamVO> selectExamList(ExamQueryDTO examQueryDTO);

}
