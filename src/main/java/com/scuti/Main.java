package com.scuti;

import com.scuti.server.netty.NettyServer;
import com.scuti.storage.Database;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("  _________              __  .__ ");
        System.out.println(" /   _____/ ____  __ ___/  |_|__|");
        System.out.println(" \\_____  \\_/ ___\\|  |  \\   __\\  |");
        System.out.println(" /        \\  \\___|  |  /|  | |  |");
        System.out.println("/_________/\\_____>____/ |__| |__|");



        NettyServer server = new NettyServer("127.0.0.1", 35000);
        server.createSocket();
        server.bind();
    }

    public static void initialize() throws SQLException {
        Database.getInstance();
    }
}