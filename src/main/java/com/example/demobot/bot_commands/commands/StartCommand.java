package com.example.demobot.bot_commands.commands;


import com.example.demobot.bot_commands.service.StartCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommand implements BotCommand {

    private final StartCommandService startCommandService;

    @Override
    public SendMessage execute(Update update) {
        return startCommandService.initUserWithFeedback(update);

    }



}
