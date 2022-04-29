package com.example.demobot.bot_commands.bot_commands;

public enum CommandName {

    START("/start"),
    INFO("/info");

    private final String command;

    CommandName(String command) {
        this.command = command;
    }
    public String getCommand() {
        return command;
    }
}
