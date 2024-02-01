package com.scuti.commands;

import com.scuti.game.users.User;

import java.util.List;

public abstract class Command {
    private int minRank;

    /**
     * Handle command
     * @param user: user who calls the command
     * @param args: arguments (list of Strings)
     */
    public abstract void handle(User user, List<String> args);

    /**
     * Get the min rank to use the command
     * @return min rank (int)
     */
    public int getMinRank() {
        return minRank;
    }
}
