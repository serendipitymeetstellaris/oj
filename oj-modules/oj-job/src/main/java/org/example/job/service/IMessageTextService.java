package org.example.job.service;

import org.example.job.domain.message.MessageText;

import java.util.List;

public interface IMessageTextService {

    boolean batchInsert(List<MessageText> messageTextList);
}
