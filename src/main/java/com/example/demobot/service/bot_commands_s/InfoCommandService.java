package com.example.demobot.service.bot_commands_s;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class InfoCommandService {

    public SendMessage sendInfoMessage(Update update) {
//TODO: implement
        return new SendMessage();
    }
}
