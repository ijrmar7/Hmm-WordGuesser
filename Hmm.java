import java.util.Scanner;

class Hmm {

    // Variables
    public static final int ATTEMPTS = 6;
    public static final int LENGTH = 5;
    public static final String TO_GUESS = "DOORS";
    public static String guess;
    // New scanner object
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // Variables
        int no_of_attempts = 0;
        Boolean is_winner = false;

        // Initialize the array to have some dash
        for(int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                GUESSES[i][j] = '_';
            }
        }

        // Loop until out of attempts
        do
        {       
            // Print the menu     
            print();
            
            // Get guess
            guess = get_guess();

            // If not a five letter word
            if (guess.length() != 5)
            {
                // Output error message
                println_color(YELLOW, "    ⚠️ You are supposed to enter a " + LENGTH + " letter word.");
                println_color(YELLOW, "    ⚠️ Is it clear? I am going to ask you a 5 letter word again.");

                // Some random delay
                delay(5000);

                // Skip this loop
                continue;
            }

            // Output guess
            println_color(CYAN, "    🔎 You entered " + guess + ", let me check it. 🔍");

            // Some random delay
            delay(5000);

            // Loop to insert the letters to array
            for (int i = 0; i < guess.length(); i++)
            {
                GUESSES[no_of_attempts][i] = guess.charAt(i);
            }

            // Check if the guess == to_guess
            if(is_winner = guess.equalsIgnoreCase(TO_GUESS))
            {
                // Increment the number of attempts
                no_of_attempts++;

                // Print the menu
                print();

                // Break the loop
                break;
            }

            // Increment the number of attempts
            no_of_attempts++;
        }
        while (no_of_attempts != ATTEMPTS);
        if (is_winner)
        {
            System.out.println("You won!");
            System.out.println("Word to Guess: " + TO_GUESS);
            System.out.println("No. of ATTEMPTS: " + no_of_attempts);
        }
        else
        {
            System.out.println("You lose!");
            System.out.println("Word to Guess: " + TO_GUESS);
        }
    scan.close();
    }
        // Initialize array
    public static char[][] GUESSES = new char[ATTEMPTS][LENGTH];

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

    // Get guess
    public static String get_guess() {
        print_color(CYAN, "    🤔 Enter your guess 📝: ");
        String g = scan.next().toUpperCase();
        return g;
    }

    // Word block
    public static void word_block() {
        for(int i = 0; i < ATTEMPTS; i++)
        {
            System.out.print("             ");            
            for (int j = 0; j < LENGTH; j++)
            {
                print_color(WHITE," " + GUESSES[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Delay
    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    // Check
    public static void check_char(char c) {
        if(c == '_')
        {
            print_color(WHITE," " + c + " ");
        }
    }

    public static void print() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.print(CYAN);
        System.out.println("                                 ,------.  ");
        System.out.println(",--.  ,--.,--.   ,--.,--.   ,--.'  .--.  ' ");
        System.out.println("|  '--'  ||   `.'   ||   `.'   |'--' _|  | ");
        System.out.println("|  .--.  ||  |'.'|  ||  |'.'|  | .--' __'  ");
        System.out.println("|  |  |  ||  |   |  ||  |   |  | `---'     ");
        System.out.println("`--'  `--'`--'   `--'`--'   `--' .---.     ");
        System.out.println("                                 '---'     ");
        System.out.print(RESET_ALL);
        println_color(CYAN, "    🤔 Hmm? - A Word Guesser Game 🕹️");
        println_color(GREEN, "    😎 By: Jonash Marcelino 👌");
        println_color(PURPLE, "    💻 Github: ijrmar7 🤖");
        System.out.println();
        word_block();
        System.out.println();
        println_color(CYAN, "    ℹ️ The game have already randomly selected a 5 letter word. 📖");
        println_color(CYAN, "    ℹ️ Now, try guessing and I will help you in this challenge. 🔍");
        println_color(CYAN, "    ℹ️ I will change the color of the text as a hint! 🪄");
    }
}