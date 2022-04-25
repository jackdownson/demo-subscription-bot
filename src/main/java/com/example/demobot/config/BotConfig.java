package com.example.demobot.config;

import com.example.demobot.SubscribeBot;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Slf4j
@PropertySource("classpath:application.yml")
@Component
public class BotConfig {
    @Value("${telegram.webhookPath}")
    private String webhookPath;
    @Value("${telegram.botToken}")
    private String botToken;
    @Value("${telegram.botName}")
    private String botName;

    @Bean
    public SubscribeBot mySubscribeBot(BotFacade botFacade) {
        SubscribeBot bot = new SubscribeBot(botFacade);
        bot.setBotName(botName);
        bot.setBotToken(botToken);
        bot.setWebhookPath(webhookPath);
        log.info("Bot started with props: " +
                "{}" +
                " {}" +
                " {}",
                botName,
                botToken,
                webhookPath);
        return bot;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {

        var source = new ResourceBundleMessageSource();
        source.setDefaultEncoding("UTF-8");
        source.setBasenames("messages/labels");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }

}
