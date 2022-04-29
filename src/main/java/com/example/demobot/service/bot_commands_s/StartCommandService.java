package com.example.demobot.service.bot_commands_s;

import com.example.demobot.service.UserRegistrationService;
import com.example.demobot.service.feedback.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service("startBotMessageFeedback")
public class StartCommandService {

    private final UserRegistrationService userRegistrationService;
    private final FeedbackService startBotMessageService;

    StartCommandService(UserRegistrationService userRegistrationService,
    @Qualifier("startBotMessageFeedbackService")
    FeedbackService startBotMessageService) {
        this.userRegistrationService = userRegistrationService;
        this.startBotMessageService = startBotMessageService;
    }

    public SendMessage initUserWithFeedback(Update update) {
        userRegistrationService.register(update.getMessage().getFrom());

        SendMessage sendMessage = startBotMessageService.giveFeedback(update);
        log.info("sendMessage {}", sendMessage);
        return startBotMessageService.giveFeedback(update);
    }
}
