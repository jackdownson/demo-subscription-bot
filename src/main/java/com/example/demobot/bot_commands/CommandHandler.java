package com.example.demobot.bot_commands;


import com.example.demobot.bot_commands.commands.BotCommand;
import com.example.demobot.service.feedback.SubscribedFeedback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class CommandHandler {

    private final String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;
    private final SubscribedFeedback subscribeFeedbackService;


    public CommandHandler(CommandContainer commandContainer, SubscribedFeedback subscribeFeedbackService) {
        this.commandContainer = commandContainer;
        this.subscribeFeedbackService = subscribeFeedbackService;
    }


    public SendMessage handle(Update update) {

        log.info("start handling");


        if (isCommand(update)) {
            log.info("isCommand");
            BotCommand botCommand = commandContainer.retrieveCommand(getCommandName(update));
            return botCommand.execute(update);
        }

        if (isPlainText(update)) {
            log.info("isPlainText");
            return subscribeFeedbackService.giveFeedback(update);
        }

        return new SendMessage();
    }

    private boolean isCommand(Update update) {
        boolean startsWith = update.getMessage().getText().startsWith(COMMAND_PREFIX);
        log.info("message: {}", update.getMessage().getText());
        log.info("startsWith {}", startsWith);
        return (update.getMessage() != null
                && update.getMessage().hasText())
                && update.getMessage().getText().startsWith(COMMAND_PREFIX);

    }

    private String getCommandName(Update update) {
        String message = update.getMessage().getText().trim();
        return message.split(" ")[0].toLowerCase();
    }

    private boolean isPlainText(Update update) {
        return (update.getMessage() != null
                && update.getMessage().hasText());

    }
}
