package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    final int ATTEMPTS = 6;
    final int LENGTH = 5;
    final int WORDS_SIZE = 58;
    final int WRONG = 0;
    final int CLOSE = 1;
    final int CORRECT = 2;
    int NO_OF_ATTEMPTS = 0;
    char guessBox[][] = new char[ATTEMPTS][LENGTH];
    int scores[][] = new int[ATTEMPTS][LENGTH];
    String TO_GUESS = chooseWord().toUpperCase();

    Game() {
        // Initialize the array to have some dash
        for(int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                guessBox[i][j] = '_';
            }
        }
        // Initialize the array to fill WRONG
        for(int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                scores[i][j] = WRONG;
            }
        }
    }

    // Print Result
    void printResult(Boolean b) {
        if (b)
        {
            Printer.newline(Printer.CYAN, "🎊 CONGRATULATIONS! 🎉");
            delay(2);
            Printer.newline(Printer.CYAN, "🥳 YOU ARE CORRECT AND YOU WON! 🙌");
            delay(2);
            Printer.newline(Printer.CYAN, "🤔 WORD TO GUESS 📝: " + Printer.WHITE + TO_GUESS);
            delay(2);
            Printer.newline(Printer.CYAN, "🔎 NO. OF ATTEMPTS 👌: " + Printer.WHITE + NO_OF_ATTEMPTS);
            delay(2);
            Printer.newline(Printer.CYAN, "🔢 SCORES 😎: 60/60");
            delay(2);
        }
        else
        {
            Printer.newline(Printer.CYAN, "😔 VERY SAD! 💔");
            delay(2);
            Printer.newline(Printer.CYAN, "🥲 YOU ARE OUT OF ATTEMPTS AND YOU LOSE. 0️⃣");
            delay(2);
            Printer.newline(Printer.CYAN, "🤔 WORD TO GUESS 📝: " + Printer.WHITE + TO_GUESS);
            delay(2);
            Printer.newline(Printer.CYAN, "🔢 SCORES 😎: " + calculateScore() + "/60");
            delay(2);
        }
    }

    // Delay
    void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }


    // Calculate Score
    int calculateScore() {
        int sum = 0;
        for(int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                sum += scores[i][j];
            }
        }
        return sum;
    }

    // Word Block
    void wordBlock() {
        for (int i = 0; i < ATTEMPTS; i++)
        {     
            for (int j = 0; j < LENGTH; j++)
            {
                if (guessBox[i][j] == '_')
                {
                    System.out.print(Printer.BLACK);
                    Printer.inline(Printer.WHITE_BG," " + guessBox[i][j] + " ");
                }
                else if (scores[i][j] == CORRECT)
                {
                    System.out.print(Printer.WHITE);
                    Printer.inline(Printer.GREEN_BG," " + guessBox[i][j] + " ");
                }
                else if (scores[i][j] == CLOSE)
                {
                    System.out.print(Printer.WHITE);
                    Printer.inline(Printer.YELLOW_BG," " + guessBox[i][j] + " ");
                }
                else
                {
                    System.out.print(Printer.WHITE);
                    Printer.inline(Printer.RED_BG," " + guessBox[i][j] + " ");
                }
                System.out.print(Printer.RESET_ALL);
            }
            System.out.println();
        }
    }

    // Check Word
    Boolean checkWord() {
        int sum = 0;
        for (int i = 0; i < LENGTH; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                if (guessBox[NO_OF_ATTEMPTS][i] == TO_GUESS.charAt(j))
                {
                    if (i == j)
                    {
                        scores[NO_OF_ATTEMPTS][i] = CORRECT;
                        sum += CORRECT;
                        break;
                    }
                    else
                    {
                        scores[NO_OF_ATTEMPTS][i] = CLOSE;
                        sum += CLOSE;
                    }
                }
            }
        }
        if (sum == 10)
            return true;
        return false;
    }

    // Get Guess
    void getGuess(String guess) {
        for (int i = 0; i < LENGTH; i++)
        {
            guessBox[NO_OF_ATTEMPTS][i] = guess.charAt(i);
        }
    }

    // Choose Word
    String chooseWord() {
        File f = new File("words.txt");
        Scanner myReader;
        String word = "DOORS";
        try {
            myReader = new Scanner(f);
            int rand = (int) (Math.random() * WORDS_SIZE);
            for (int i = 0; i <= rand; i++) {
                if (i == rand) {
                    word = myReader.nextLine();
                }
                else {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return word;
    }

    // Menu
    void menu(int i) {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.print(Printer.CYAN);
        System.out.println("                                 ,------.  ");
        System.out.println(",--.  ,--.,--.   ,--.,--.   ,--.'  .--.  ' ");
        System.out.println("|  '--'  ||   `.'   ||   `.'   |'--' _|  | ");
        System.out.println("|  .--.  ||  |'.'|  ||  |'.'|  | .--' __'  ");
        System.out.println("|  |  |  ||  |   |  ||  |   |  | `---'     ");
        System.out.println("`--'  `--'`--'   `--'`--'   `--' .---.     ");
        System.out.println("                                 '---'     ");
        System.out.print(Printer.RESET_ALL);
        Printer.newline(Printer.CYAN, "🤔 Hmm? - A Word Guesser Game 🕹️");
        Printer.newline(Printer.GREEN, "😎 By: Jonash Marcelino 👌");
        Printer.newline(Printer.PURPLE, "💻 Github: ijrmar7 🤖");
        if (i == 1)
        {
            Printer.newline(Printer.CYAN, "🤔 MAIN MENU 📝");
            Printer.newline(Printer.CYAN, "1. 🕹️ SOLO");
            Printer.newline(Printer.CYAN, "2. 🎮 DUO");
            Printer.newline(Printer.CYAN, "3. 💻 CREDITS");
            Printer.newline(Printer.CYAN, "4. ❎ EXIT");
            
        }
        else if (i == 2)
        {
            System.out.println();
            wordBlock();
            System.out.println();
            Printer.newline(Printer.CYAN, "ℹ️ The game have already randomly selected a 5 letter word. 📖");
            Printer.newline(Printer.CYAN, "ℹ️ Now, try guessing and I will help you in this challenge. 🔍");
            Printer.newline(Printer.CYAN, "ℹ️ I will change the color of the text as a hint! 🪄");
            Printer.newline(Printer.CYAN, "ℹ️ Hint 🤖: " + Printer.WHITE + "Starts with " + TO_GUESS.charAt(0));
        }
        else if (i == 3)
        {
            Printer.newline(Printer.CYAN, "💻 CREDITS");
            Printer.newline(Printer.CYAN, "🧑‍💻 Developer:");
            Printer.newline(Printer.CYAN, "Jonash Marcelino");
            Printer.newline(Printer.CYAN, "🤖 ChatGPT Gang: (Support)");
            Printer.newline(Printer.CYAN, "Anthony James Bargo");
            Printer.newline(Printer.CYAN, "John Gabriel Cuadro");
            Printer.newline(Printer.CYAN, "Vaughn Tinte");
            Printer.newline(Printer.CYAN, "Takeshi Okamoto");
            Printer.newline(Printer.CYAN, "Jhorel Jerard Franco");
        }
    }
}
