package com.example.demobot.config;

import com.example.demobot.bot_commands.CommandHandler;
import com.example.demobot.service.feedback.SubscribedFeedback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class BotFacade {

    private final SubscribedFeedback subscribeFeedbackService;
    private final CommandHandler commandHandler;

    @Autowired
    public BotFacade(@Qualifier("SubscribedFeedback") SubscribedFeedback subscribeFeedbackService,
                     CommandHandler commandHandler) {
        this.subscribeFeedbackService = subscribeFeedbackService;
        this.commandHandler = commandHandler;
    }

    public SendMessage checkIfUserIsSubscribed(Update update) {
        return subscribeFeedbackService.giveFeedback(update);

    }

    public SendMessage handleCommand(Update update) {
        return commandHandler.handle(update);
    }

}
