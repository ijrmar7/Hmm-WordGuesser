package main;

public class Printer {

    // Text Colors
    static final String BLACK = "\u001B[30m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final String WHITE = "\u001B[37m";

    // Background Colors
    static final String RESET_ALL = "\u001B[0m";
    static final String BLACK_BG = "\u001B[40m";
    static final String RED_BG = "\u001B[41m";
    static final String GREEN_BG = "\u001B[42m";
    static final String YELLOW_BG = "\u001B[43m";
    static final String BLUE_BG = "\u001B[44m";
    static final String PURPLE_BG = "\u001B[45m";
    static final String CYAN_BG = "\u001B[46m";
    static final String WHITE_BG = "\u001B[47m";

    static void inline(String color, String text) {
        System.out.print(color + text + RESET_ALL);
    }

    static void newline(String color, String text) {
        System.out.println(color + text + RESET_ALL);
    }
}
