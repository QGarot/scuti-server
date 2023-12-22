package com.scuti.util.logger;

public class Logger {
    public static void logError(String msg) {
        System.out.println("[ERROR] ".concat(msg));
    }

    public static void logWarning(String msg) {
        System.out.println("[WARNING] ".concat(msg));
    }

    public static void debug(String msg) {
        System.out.println("[DEBUG] ".concat(msg));
    }

    public static void logInfo(String msg) {
        System.out.println("[INFO] ".concat(msg));
    }

    public static void logIncoming(int header) {
        System.out.println(">> ".concat(Integer.toString(header)));
    }
}
