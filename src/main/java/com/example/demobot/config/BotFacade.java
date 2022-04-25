package com.example.demobot.config;

import com.example.demobot.service.feedback.SubscribedFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotFacade {

    private final SubscribedFeedback subscribeFeedbackService;

    @Autowired
    public BotFacade(@Qualifier("SubscribedFeedback") SubscribedFeedback subscribeFeedbackService) {
        this.subscribeFeedbackService = subscribeFeedbackService;
    }

    public SendMessage checkIfUserIsSubscribed(Update update) {
        return subscribeFeedbackService.giveFeedback(update);

    }
}
