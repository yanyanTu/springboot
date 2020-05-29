package com.spring.recipes.service;

import com.spring.recipes.domain.Message;

import java.util.List;

public interface MessageBoardService {

    public List<Message> listMessage();

    public void postMessage(Message message);

    public void deleteMessage(Message message);

    public Message findMessageById(Long messageId) ;
}
