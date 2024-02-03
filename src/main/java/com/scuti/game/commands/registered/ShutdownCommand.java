package com.scuti.game.commands.registered;

import com.scuti.game.commands.Command;
import com.scuti.game.users.User;
import com.scuti.util.logger.Logger;

import java.util.List;

public class ShutdownCommand extends Command {

    public ShutdownCommand(int minRank) {
        this.setMinRank(minRank);
    }

    @Override
    public void handle(User user, List<String> args) {
        System.out.println();
        System.out.println();

        Logger.logInfo("[SHUTDOWN] The server is about to be closed.");
        System.exit(0);
    }
}
