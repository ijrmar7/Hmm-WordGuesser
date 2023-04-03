import java.util.Scanner;

class Hmm {

    // Variables
    public static final int ATTEMPS = 6;
    public static final int LENGTH = 5;
    public static final String TO_GUESS = "DOORS";

    // Initialize array
    public static String[][] GUESSES = new String[ATTEMPS][LENGTH];

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

    public static void print() {
        System.out.println("Hmm? - A Word Guesser Game");
        System.out.println("Created by: Jonash Marcelino");
        System.out.println("Github: https://github.com/ijrmar7");
        for(int i = 0; i < ATTEMPS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                System.out.print(GUESSES[i][j]);
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
                    GUESSES[i][j] = WHITE_BG + BLACK + " # " + RESET_ALL;
                }
            }
            print();
            System.out.print("Enter your guess: ");
            String guess = scan.next();
            if (guess.length() != 5)
            {
                System.out.println("(i) You are supposed to enter a " + LENGTH + " letter word.");
                continue;
            }
            for (int i = 0; i < guess.length(); i++)
            {
                GUESSES[no_of_attemps][i] = WHITE_BG + BLACK + " " + Character.toString(guess.charAt(i)).toUpperCase() + " " + RESET_ALL;
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