package org.example.friend.service.user;

import org.example.common.core.domain.TableDataInfo;
import org.example.friend.domain.exam.dto.ExamQueryDTO;

public interface IUserExamService {

    int enter(String token, Long examId);

    TableDataInfo list(ExamQueryDTO examQueryDTO);
}
