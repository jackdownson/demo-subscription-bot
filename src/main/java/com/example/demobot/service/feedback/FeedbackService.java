package com.example.demobot.service.feedback;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/*
Generates messages to user
 */
public interface FeedbackService {

    SendMessage giveFeedback(Update update);
}
