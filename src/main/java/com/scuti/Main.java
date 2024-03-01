package com.scuti;

import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.commands.CommandManager;
import com.scuti.game.items.ItemManager;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.rooms.RoomModelManager;
import com.scuti.game.users.UserManager;
import com.scuti.server.netty.NettyServer;
import com.scuti.storage.Database;

public class Main {
    public static NettyServer server;
    public static void main(String[] args) {
        System.out.println("  _________              __  .__ ");
        System.out.println(" /   _____/ ____  __ ___/  |_|__|");
        System.out.println(" \\_____  \\_/ ___\\|  |  \\   __\\  |");
        System.out.println(" /        \\  \\___|  |  /|  | |  |");
        System.out.println("/_________/\\_____>____/ |__| |__|");

        initialize();

        server = new NettyServer("127.0.0.1", 35000);
        server.createSocket();
        server.bind();

        Runtime.getRuntime().addShutdownHook(new Thread(Main::unload));
    }

    /**
     * Initialize managers
     */
    public static void initialize() {
        Database.getInstance();
        UserManager.getInstance();
        RoomManager.getInstance();
        RoomModelManager.getInstance();
        CommandManager.getInstance();
        CatalogManager.getInstance();
        ItemManager.getInstance();
    }

    /**
     * Unload managers and Netty server
     */
    public static void unload() {
        RoomManager.getInstance().unload();
        RoomModelManager.getInstance().unload();
        CommandManager.getInstance().unload();
        CatalogManager.getInstance().unload();
        ItemManager.getInstance().unload();
        UserManager.getInstance().unload();
        server.dispose();
    }
}