package com.example.demobot.service.bot_commands_s;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UnknownCommandService {

    public SendMessage sendDummyMessage(Update update) {

        return new SendMessage();
    }
}
