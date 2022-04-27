package com.example.demobot.bot_commands;


import com.example.demobot.bot_commands.commands.BotCommand;
import com.example.demobot.bot_commands.commands.CommandName;
import com.example.demobot.bot_commands.commands.InfoCommand;
import com.example.demobot.bot_commands.commands.StartCommand;
import com.example.demobot.bot_commands.commands.UnknownCommand;
import com.example.demobot.bot_commands.service.InfoCommandService;
import com.example.demobot.bot_commands.service.StartCommandService;
import com.example.demobot.bot_commands.service.UnknownCommandService;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

@Component
public class CommandContainer {

    private final ImmutableMap<String, BotCommand> commandMap;
    private final BotCommand unknownCommand;

    public CommandContainer(StartCommandService startCommandService,
                            InfoCommandService infoCommandService,
                            UnknownCommandService unknownCommandService) {
        unknownCommand = new UnknownCommand(unknownCommandService);
        commandMap = ImmutableMap.<String, BotCommand>builder()
                .put(CommandName.START.getCommandName(), new StartCommand(startCommandService))
                .put(CommandName.INFO.getCommandName(), new InfoCommand(infoCommandService))
                .build();
    }

    public BotCommand retrieveCommand(String commandName) {
        return commandMap.getOrDefault(commandName, unknownCommand);
    }


}
