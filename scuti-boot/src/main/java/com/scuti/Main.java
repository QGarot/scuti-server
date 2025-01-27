package com.scuti;

import com.scuti.providers.ServiceProvider;
import com.scuti.providers.UserServiceProvider;
import database.Database;
import database.IDatabase;
import game.GameController;
import game.IGameController;
import server.IServer;
import server.ScutiServer;
import services.IUserService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Scuti emulator");

        try {
            // Database
            IDatabase database = new Database("localhost", "root", "", "scuti");

            // Providers
            ServiceProvider<IUserService> userServiceProvider = new UserServiceProvider(database);

            // Services
            IUserService userService = userServiceProvider.getService();
            userService.start();

            // Game controller
            IGameController gameController = new GameController(userService);

            // Server
            IServer server = new ScutiServer("192.168.1.15", 3000, gameController);
            server.run();
        } catch (Exception _) {

        }
    }
}