package org.example.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.job.domain.message.MessageText;
import org.example.job.mapper.message.MessageTextMapper;
import org.example.job.service.IMessageTextService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageTextServiceImpl extends ServiceImpl<MessageTextMapper, MessageText> implements IMessageTextService {

    @Override
    public boolean batchInsert(List<MessageText> messageTextList) {
        return saveBatch(messageTextList);
    }
}
