package com.example.demobot.webhook;

import com.example.demobot.SubscribeBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookBotController {

    private final SubscribeBot subscribeBot;

    public WebhookBotController(SubscribeBot subscribeBot) {
        this.subscribeBot = subscribeBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return subscribeBot.onWebhookUpdateReceived(update);
    }
}
