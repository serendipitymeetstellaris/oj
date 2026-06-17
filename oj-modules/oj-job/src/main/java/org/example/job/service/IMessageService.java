package org.example.job.service;

import org.example.job.domain.message.Message;

import java.util.List;

public interface IMessageService {

    boolean batchInsert(List<Message> messageTextList);
}
