package com.example.demobot.bot_commands;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {

    void execute(Update update);
}
