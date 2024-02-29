package com.scuti.game.commands;

import com.scuti.api.utils.IManager;
import com.scuti.game.commands.registered.ShutdownCommand;
import com.scuti.util.logger.Logger;

import java.util.HashMap;

public class CommandManager implements IManager {
    private HashMap<String, Command> commands;
    private static CommandManager instance;

    public CommandManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.commands = new HashMap<>();
        this.registerUserCommands();
        this.registerStaffCommands();
        Logger.logInfo("CommandManager loaded!");
    }

    @Override
    public void unload() {
        this.getCommands().clear();
        this.commands = null;
        Logger.logInfo("CommandManager unloaded!");
    }

    public HashMap<String, Command> getCommands() {
        return this.commands;
    }

    public void registerUserCommands() {

    }

    public void registerStaffCommands() {
        this.getCommands().put(":shutdown", new ShutdownCommand(7));
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }

        return instance;
    }
}
