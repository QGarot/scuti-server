package com.scuti.game.commands.registered;

import com.scuti.game.commands.Command;
import com.scuti.game.users.User;
import com.scuti.util.logger.Logger;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShutdownCommand extends Command {

    public ShutdownCommand(int minRank) {
        this.setMinRank(minRank);
    }

    /**
     * Close the server
     * @param user: user who calls the command
     * @param args: arguments (list of Strings)
     */
    @Override
    public void handle(User user, List<String> args) {
        System.out.println();
        System.out.println();

        Logger.logInfo("[SHUTDOWN] The server is about to be closed.");
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        scheduledThreadPool.schedule(() -> System.exit(0), 3, TimeUnit.SECONDS);
    }
}
