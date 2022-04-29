package com.example.demobot.bot_commands.bot_commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {

    SendMessage execute(Update update);
}
