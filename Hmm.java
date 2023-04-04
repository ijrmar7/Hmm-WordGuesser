import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

class Hmm {

    // Variables
    public static final int ATTEMPTS = 6;
    public static final int LENGTH = 5;
    public static final String TO_GUESS = choose_word().toUpperCase();
    public static String GUESS;
    public static int NO_OF_ATTEMPTS = 0;
    public static int WRONG = 0;
    public static int CLOSE = 1;
    public static int CORRECT = 2;
    

    // Initialize array
    public static char[][] GUESSES = new char[ATTEMPTS][LENGTH];
    public static int[][] STATUS = new int[ATTEMPTS][LENGTH];

    // New scanner object
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Boolean is_winner = false;
        initialize();

        // Loop until out of attempts
        do
        {       
            // Print the menu     
            print();
            
            // Get GUESS
            GUESS = get_guess();

            // If not a five letter word
            if (GUESS.length() != 5)
            {
                // Output error message
                println_color(YELLOW, "⚠️ You are supposed to enter a " + LENGTH + " letter word.");
                println_color(YELLOW, "⚠️ Is it clear? I am going to ask you a 5 letter word again.");

                // Some random delay
                delay(3);

                // Skip this loop
                continue;
            }

            // Output GUESS
            println_color(CYAN, "🔎 You entered " + WHITE + GUESS + CYAN + ", let me check it. 🔍");

            // Some random delay
            delay(3);

            // Loop to insert the letters to array
            check_word(GUESS);
            for (int i = 0; i < GUESS.length(); i++)
            {
                GUESSES[NO_OF_ATTEMPTS][i] = GUESS.charAt(i);
            }

            // Check if the GUESS == to_guess
            if(is_winner = GUESS.equalsIgnoreCase(TO_GUESS))
            {
                // Increment the number of attempts
                NO_OF_ATTEMPTS++;

                // Print the menu
                print();

                // Break the loop
                break;
            }

            // Increment the number of attempts
            NO_OF_ATTEMPTS++;
        }
        while (NO_OF_ATTEMPTS != ATTEMPTS);
        print_result(is_winner);
        scan.close();
    }

    public static void print_result(Boolean b) {
        if (b)
        {
            println_color(CYAN, "🎊 CONGRATULATIONS! 🎉");
            delay(2);
            println_color(CYAN, "🥳 YOU ARE CORRECT AND YOU WON! 🙌");
            delay(2);
            println_color(CYAN, "🤔 WORD TO GUESS 📝: " + WHITE + TO_GUESS);
            delay(2);
            println_color(CYAN, "🔎 NO. OF ATTEMPTS 👌: " + WHITE + NO_OF_ATTEMPTS);
        }
        else
        {
            println_color(CYAN, "😔 VERY SAD! 💔");
            delay(2);
            println_color(CYAN, "🥲 YOU ARE OUT OF ATTEMPTS AND YOU LOSE. 0️⃣");
            delay(2);
            println_color(CYAN, "🤔 WORD TO GUESS 📝: " + WHITE + TO_GUESS);
        }
    }

    // This thing prints menu
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
        println_color(CYAN, "🤔 Hmm? - A Word Guesser Game 🕹️");
        println_color(GREEN, "😎 By: Jonash Marcelino 👌");
        println_color(PURPLE, "💻 Github: ijrmar7 🤖");
        System.out.println();
        word_block();
        System.out.println();
        println_color(CYAN, "ℹ️ The game have already randomly selected a 5 letter word. 📖");
        println_color(CYAN, "ℹ️ Now, try guessing and I will help you in this challenge. 🔍");
        println_color(CYAN, "ℹ️ I will change the color of the text as a hint! 🪄");
        println_color(CYAN, "ℹ️ Hint 🤖: " + WHITE + "Starts with " + TO_GUESS.charAt(0));
    }

    // Print with new line with text color
    public static void println_color(String color, String text) {
        System.out.println(color + text + RESET_ALL);
    }

    // Print with text color
    public static void print_color(String color, String text) {
        System.out.print(color + text + RESET_ALL);
    }

    public static void initialize() {
        // Initialize the array to have some dash
        for(int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                GUESSES[i][j] = '_';
            }
        }
        // Initialize the array to fill WRONG
        for(int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                STATUS[i][j] = WRONG;
            }
        }
    }

    // Get GUESS
    public static String get_guess() {
        print_color(CYAN, "🤔 Enter your guess 📝: ");
        String g = scan.next().toUpperCase();
        return g;
    }

    // Word block
    public static void word_block() {
        for (int i = 0; i < ATTEMPTS; i++)
        {
            System.out.print(BLACK + "             ");            
            for (int j = 0; j < LENGTH; j++)
            {
                if (GUESSES[i][j] == '_')
                {
                    System.out.print(BLACK);
                    print_color(WHITE_BG," " + GUESSES[i][j] + " ");
                }
                else if (STATUS[i][j] == CORRECT)
                {
                    System.out.print(WHITE);
                    print_color(GREEN_BG," " + GUESSES[i][j] + " ");
                }
                else if (STATUS[i][j] == CLOSE)
                {
                    System.out.print(WHITE);
                    print_color(YELLOW_BG," " + GUESSES[i][j] + " ");
                }
                else
                {
                    System.out.print(WHITE);
                    print_color(RED_BG," " + GUESSES[i][j] + " ");
                }
                System.out.print(RESET_ALL);
            }
            System.out.println();
        }
    }

    // Delay
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    // Check
    public static void check_word(String s) {
        for (int i = 0; i < LENGTH; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                if (s.charAt(i) == TO_GUESS.charAt(j))
                {
                    if (i == j)
                    {
                        STATUS[NO_OF_ATTEMPTS][i] = CORRECT;
                        break;
                    }
                    else
                    {
                        STATUS[NO_OF_ATTEMPTS][i] = CLOSE;
                    }
                }
            }
        }
    }

    // Chose word
    public static String choose_word() {
        Random random = new Random();
        int rand = random.ints(0, 58)
            .findFirst()
            .getAsInt();
        String word = "DOORS";
        try {
            word = Files.readAllLines(Paths.get("words.txt")).get(rand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    // Some not very important stuff

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
}