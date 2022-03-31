package com.example.demobot.config;

import com.example.demobot.service.GetChatMemberUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotFacade {

    private final GetChatMemberUseCase chatMemberService;

    @Autowired
    public BotFacade(GetChatMemberUseCase chatMemberService) {
        this.chatMemberService = chatMemberService;
    }

    public SendMessage handleMessage(Update update) {

        return chatMemberService.generateFeedbackMessage(update);

    }
}
