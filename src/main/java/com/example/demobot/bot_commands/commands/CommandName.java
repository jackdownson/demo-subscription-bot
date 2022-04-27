package com.example.demobot.bot_commands.commands;

public enum CommandName {

    START("/start"),
    INFO("/info");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }
    public String getCommandName() {
        return commandName;
    }
}
