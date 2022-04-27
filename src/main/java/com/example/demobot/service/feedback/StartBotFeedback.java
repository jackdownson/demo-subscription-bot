package com.example.demobot.service.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service("startBotMessageFeedback")
@RequiredArgsConstructor
public class StartBotFeedback implements FeedbackService {

    @Override
    public SendMessage giveFeedback(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("registered");
        return message;
    }
}
