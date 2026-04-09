package org.example.system.service.exam;

import org.example.system.domain.exam.dto.ExamQueryDTO;
import org.example.system.domain.exam.vo.ExamVO;

import java.util.List;

public interface IExamService {

    List<ExamVO> list(ExamQueryDTO examQueryDTO);
}
