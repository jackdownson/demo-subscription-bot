package com.example.demobot.bot_commands.commands;

import com.example.demobot.bot_commands.service.InfoCommandService;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@RequiredArgsConstructor
public class InfoCommand implements BotCommand {

    private final InfoCommandService infoCommandService;

    @Override
    public SendMessage execute(Update update) {
        return infoCommandService.sendInfoMessage(update);
    }
}
