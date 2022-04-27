package com.example.demobot.bot_commands.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class InfoCommandService {

    public SendMessage sendInfoMessage(Update update) {

        return new SendMessage();
    }
}
