package com.example.demobot;

import com.example.demobot.config.BotFacade;
import com.example.demobot.service.GetChatMemberUseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
@Slf4j

public class SubscribeBot extends TelegramWebhookBot {
    private String botToken = "5278510472:AAGfDAPhkInDdKUra0e4CkpV2hnK40R_4Kk";
    private String botName = "sogr_bot";
    private String webhookPath = "https://25ba-188-32-209-237.ngrok.io";

    private final BotFacade bot;

    public SubscribeBot(BotFacade bot) {
        this.bot = bot;
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {

            return  bot.handleMessage(update);

        }
            return new SendMessage();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return null;
    }

    public String getWebhookPath() {
        return webhookPath;
    }
}
