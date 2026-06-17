package org.example.friend.service.user;

import org.example.common.core.domain.dto.PageQueryDTO;
import org.example.common.core.domain.TableDataInfo;

public interface IUserMessageService {
    TableDataInfo list(PageQueryDTO dto);
}
