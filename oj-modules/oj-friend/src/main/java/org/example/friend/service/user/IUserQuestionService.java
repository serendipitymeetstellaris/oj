package org.example.friend.service.user;

import org.example.api.domain.vo.UserQuestionResultVO;
import org.example.common.core.domain.R;
import org.example.friend.domain.user.dto.UserSubmitDTO;

public interface IUserQuestionService {
    R<UserQuestionResultVO> submit(UserSubmitDTO submitDTO);
}
