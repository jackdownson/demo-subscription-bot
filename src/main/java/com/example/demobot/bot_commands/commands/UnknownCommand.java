package com.example.demobot.bot_commands.commands;

import com.example.demobot.bot_commands.service.UnknownCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UnknownCommand implements BotCommand {
    private final UnknownCommandService unknownCommandService;


    @Override
    public SendMessage execute(Update update) {
        return unknownCommandService.sendDummyMessage(update);
    }
}
