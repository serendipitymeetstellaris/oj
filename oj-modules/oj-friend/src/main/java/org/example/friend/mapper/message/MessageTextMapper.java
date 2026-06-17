package org.example.friend.mapper.message;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.friend.domain.message.MessageText;
import org.example.friend.domain.message.vo.MessageTextVO;

import java.util.List;

public interface MessageTextMapper extends BaseMapper<MessageText> {

    List<MessageTextVO> selectUserMsgList(Long userId);
}
