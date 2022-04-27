package com.example.demobot.bot_commands.service;

import com.example.demobot.service.UserRegistrationService;
import com.example.demobot.service.feedback.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class StartCommandService {

    private final UserRegistrationService userRegistrationService;
    private final FeedbackService startBotMessageService;

    StartCommandService(UserRegistrationService userRegistrationService,
    @Qualifier("startBotMessageFeedback")
    FeedbackService startBotMessageService) {
        this.userRegistrationService = userRegistrationService;
        this.startBotMessageService = startBotMessageService;
    }

    public SendMessage initUserWithFeedback(Update update) {
        userRegistrationService.register(update.getMessage().getFrom());
        log.info("register - OK!");
        SendMessage sendMessage = startBotMessageService.giveFeedback(update);
        log.info("sendMessage {}", sendMessage);
        return startBotMessageService.giveFeedback(update);
    }
}
