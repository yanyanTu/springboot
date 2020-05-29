package com.spring.recipes.controller;

import com.spring.recipes.domain.Message;
import com.spring.recipes.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageListController {

    private MessageBoardService messageBoardService ;

    @Autowired
    public MessageListController(MessageBoardService messageBoardService){
        this.messageBoardService = messageBoardService ;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generateList(Model model){
        List<Message> messages = Collections.emptyList();
        messages = messageBoardService.listMessage();
        model.addAttribute("messages", messages);
        return "messageList";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model){
        Message message = new Message();
        model.addAttribute("message", message);
        return "messagePost";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("message") Message message, BindingResult result){
        if( result.hasErrors()){
            return "messagePost";
        }else{
            messageBoardService.postMessage(message);
            return "redirect:messageList";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String messageDelete(@RequestParam(required = true, value="messageId") Long messageId, Model model){
        Message message = messageBoardService.findMessageById(messageId);
        messageBoardService.deleteMessage(message);
        model.addAttribute("message", messageBoardService.listMessage());
        return "redirect:messageList";
    }
}
