import java.util.Scanner;

class Hmm {

    // Variables
    public static final int ATTEMPS = 6;
    public static final int LENGTH = 5;
    public static final String TO_GUESS = "DOORS";
    public static String guess;

    // Initialize array
    public static char[][] GUESSES = new char[ATTEMPS][LENGTH];

    // Text Colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Background Colors
    public static final String RESET_ALL = "\u001B[0m";
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";

    // Print with new line with text color
    public static void println_color(String color, String text) {
        System.out.println(color + text + RESET_ALL);
    }

    // Print with text color
    public static void print_color(String color, String text) {
        System.out.print(color + text + RESET_ALL);
    }

    public static void print() {
        System.out.print(CYAN);
        System.out.println("                                 ,------.  ");
        System.out.println(",--.  ,--.,--.   ,--.,--.   ,--.'  .--.  ' ");
        System.out.println("|  '--'  ||   `.'   ||   `.'   |'--' _|  | ");
        System.out.println("|  .--.  ||  |'.'|  ||  |'.'|  | .--' __'  ");
        System.out.println("|  |  |  ||  |   |  ||  |   |  | `---'     ");
        System.out.println("`--'  `--'`--'   `--'`--'   `--' .---.     ");
        System.out.println("                                 '---'     ");
        System.out.print(RESET_ALL);
        println_color(CYAN, "    (p) Hmm? - A Word Guesser Game");
        println_color(GREEN, "    (c) Created by: Jonash Marcelino");
        println_color(PURPLE, "    (c) Github: https://github.com/ijrmar7");
        for(int i = 0; i < ATTEMPS; i++)
        {
            System.out.print("             ");            
            for (int j = 0; j < LENGTH; j++)
            {
                System.out.print(WHITE_BG);
                print_color(BLACK," " + GUESSES[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int no_of_attemps = 0;
        Boolean is_winner = false;
        do
        {
            for(int i = no_of_attemps; i < ATTEMPS; i++)
            {
                for (int j = 0; j < LENGTH; j++)
                {
                    GUESSES[i][j] = '_';
                }
            }
            print();
            print_color(CYAN, "    (?) Enter your guess: ");
            guess = scan.next();
            guess = guess.toUpperCase();
            if (guess.length() != 5)
            {
                System.out.println(YELLOW +"(i) You are supposed to enter a " + LENGTH + " letter word." + RESET_ALL);
                System.out.println(YELLOW + "(i) Is it clear? I am going to ask you a 5 letter word again." + RESET_ALL);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            for (int i = 0; i < guess.length(); i++)
            {
                GUESSES[no_of_attemps][i] = guess.charAt(i);
            }
            if(guess.equalsIgnoreCase(TO_GUESS))
            {
                is_winner = true;
                no_of_attemps++;
                print();
                break;
            }
            no_of_attemps++;
        }
        while (no_of_attemps != ATTEMPS);
        if (is_winner)
        {
            System.out.println("You won!");
            System.out.println("Word to Guess: " + TO_GUESS);
            System.out.println("No. of Attemps: " + no_of_attemps);
        }
        else
        {
            System.out.println("You lose!");
            System.out.println("Word to Guess: " + TO_GUESS);
        }
        scan.close();
    }
}