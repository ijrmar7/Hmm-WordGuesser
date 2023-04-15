package main;

public class Printer {

    // Text Colors
    final String BLACK = "\u001B[30m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String PURPLE = "\u001B[35m";
    final String CYAN = "\u001B[36m";
    final String WHITE = "\u001B[37m";

    // Background Colors
    final String RESET_ALL = "\u001B[0m";
    final String BLACK_BG = "\u001B[40m";
    final String RED_BG = "\u001B[41m";
    final String GREEN_BG = "\u001B[42m";
    final String YELLOW_BG = "\u001B[43m";
    final String BLUE_BG = "\u001B[44m";
    final String PURPLE_BG = "\u001B[45m";
    final String CYAN_BG = "\u001B[46m";
    final String WHITE_BG = "\u001B[47m";

    void print(String color, String text) {
        System.out.print(color + text + RESET_ALL);
    }

    void println(String color, String text) {
        System.out.println(color + text + RESET_ALL);
    }
}
