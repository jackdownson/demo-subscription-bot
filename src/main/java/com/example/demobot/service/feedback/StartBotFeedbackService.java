package com.example.demobot.service.feedback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

@RequiredArgsConstructor
@Slf4j
@Service("startBotMessageFeedbackService")
public class StartBotFeedbackService implements FeedbackService {

    @Value("${telegram.chat.name}")
    private String chatToSubscribe;

    private final MessageSource messageSource;

    @Override
    public SendMessage giveFeedback(Update update) {
        SendMessage message = generateSendMessageFeedback(update);
        log.info("User {} registered", update.getMessage().getFrom());
        return message;
    }


    private SendMessage generateSendMessageFeedback(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(generateStringFeedback());

        return message;
    }

    private String generateStringFeedback() {
        return messageSource.getMessage("start-message", new Object[]{chatToSubscribe}, Locale.ENGLISH);
    }
}
