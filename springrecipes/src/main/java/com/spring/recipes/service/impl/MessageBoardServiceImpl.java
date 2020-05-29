package com.spring.recipes.service.impl;

import com.spring.recipes.domain.Message;
import com.spring.recipes.service.MessageBoardService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MessageBoardServiceImpl implements MessageBoardService {

    private Map<Long, Message> messageMap = new LinkedHashMap<Long, Message>();

    @Override
    public List<Message> listMessage() {
        return new ArrayList<Message>(messageMap.values());
    }

    @Override
    public synchronized void postMessage(Message message) {
        message.setId(System.currentTimeMillis());
        messageMap.put(message.getId(), message);
    }

    @Override
    public synchronized void deleteMessage(Message message) {
        messageMap.remove(message.getId());
    }

    @Override
    public Message findMessageById(Long messageId) {
        return messageMap.get(messageId);
    }
}
