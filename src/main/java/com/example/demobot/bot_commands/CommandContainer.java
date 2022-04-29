package com.example.demobot.bot_commands;


import com.example.demobot.bot_commands.bot_commands.BotCommand;
import com.example.demobot.bot_commands.bot_commands.CommandName;
import com.example.demobot.bot_commands.bot_commands.InfoCommand;
import com.example.demobot.bot_commands.bot_commands.StartCommand;
import com.example.demobot.bot_commands.bot_commands.UnknownCommand;
import com.example.demobot.service.bot_commands_s.InfoCommandService;
import com.example.demobot.service.bot_commands_s.StartCommandService;
import com.example.demobot.service.bot_commands_s.UnknownCommandService;
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
                .put(CommandName.START.getCommand(), new StartCommand(startCommandService))
                .put(CommandName.INFO.getCommand(), new InfoCommand(infoCommandService))
                .build();
    }

    public BotCommand retrieveCommand(String commandName) {
        return commandMap.getOrDefault(commandName, unknownCommand);
    }


}
