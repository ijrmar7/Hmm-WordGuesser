package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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
        newGame();
    }
    
    void newGame() {
        TO_GUESS = chooseWord().toUpperCase();
        NO_OF_ATTEMPTS = 0;
        for (int i = 0; i < ATTEMPTS; i++) {
            for (int j = 0; j < LENGTH; j++) {
                guessBox[i][j] = '_';
            }
        }
        for (int i = 0; i < ATTEMPTS; i++) {
            for (int j = 0; j < LENGTH; j++) {
                scores[i][j] = WRONG;
            }
        }
    }

    void printResult(Boolean b, String mode, int winner) {
        if (mode == "SOLO") {
            writeHistory(mode);
        } else {
            writeHistory(mode + ": P" + winner);
        }
        if (b)
        {
            int points = 60 - (NO_OF_ATTEMPTS * 10);
            Printer.newline(Printer.CYAN, "🎊 CONGRATULATIONS! 🎉");
            delay(2);
            if (mode == "SOLO") {
                Printer.newline(Printer.CYAN, "🥳 YOU ARE CORRECT AND YOU WON! 🙌");
            } else {
                Printer.newline(Printer.CYAN, "🥳 PLAYER " + winner + " IS CORRECT AND YOU WON! 🙌");
            }
            delay(2);
            Printer.newline(Printer.CYAN, "🤔 WORD TO GUESS 📝: " + Printer.WHITE + TO_GUESS);
            delay(2);
            Printer.newline(Printer.CYAN, "🔎 NO. OF ATTEMPTS 👌: " + Printer.WHITE + NO_OF_ATTEMPTS);
            delay(2);
            Printer.newline(Printer.CYAN, "💯 POINTS 🤘: " + Printer.WHITE + points);
            delay(2);
        }
        else
        {
            Printer.newline(Printer.CYAN, "😔 VERY SAD! 💔");
            delay(2);
            if (mode == "SOLO") {
                Printer.newline(Printer.CYAN, "🥲 YOU ARE OUT OF ATTEMPTS AND YOU LOSE. 0️⃣");
            } else {
                Printer.newline(Printer.CYAN, "🥲 BOTH OF THE PLAYERS ARE OUT OF ATTEMPTS AND YOU BOTH LOSE. 0️⃣");
            }
            delay(2);
            Printer.newline(Printer.CYAN, "🤔 WORD TO GUESS 📝: " + Printer.WHITE + TO_GUESS);
            delay(2);

        }
    }

    void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

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

    void wordBlock() {
        System.out.println();
        for (int i = 0; i < ATTEMPTS; i++)
        {
            for (int j = 0; j < LENGTH; j++) {
                if (guessBox[i][j] == '_') {
                    System.out.print(Printer.BLACK);
                    Printer.inline(Printer.WHITE_BG, " " + guessBox[i][j] + " ");
                } else if (scores[i][j] == CORRECT) {
                    if (i == NO_OF_ATTEMPTS - 1) {
                        delay(1);
                    }
                    System.out.print(Printer.WHITE);
                    Printer.inline(Printer.GREEN_BG, " " + guessBox[i][j] + " ");
                } else if (scores[i][j] == CLOSE) {
                    if (i == NO_OF_ATTEMPTS - 1) {
                        delay(1);
                    }
                    System.out.print(Printer.WHITE);
                    Printer.inline(Printer.YELLOW_BG, " " + guessBox[i][j] + " ");
                } else {
                    if (i == NO_OF_ATTEMPTS - 1) {
                        delay(1);
                    }
                    System.out.print(Printer.WHITE);
                    Printer.inline(Printer.RED_BG, " " + guessBox[i][j] + " ");
                }
                System.out.print(Printer.RESET_ALL);
            }
            System.out.println();
        }
        System.out.println();
    }

    Boolean checkWord(String guess) {
        int sum = 0;
        for (int i = 0; i < LENGTH; i++)
        {
            for (int j = 0; j < LENGTH; j++) {
                if (guessBox[NO_OF_ATTEMPTS][i] == TO_GUESS.charAt(j)) {
                    if (i == j) {
                        scores[NO_OF_ATTEMPTS][i] = CORRECT;
                        sum += CORRECT;
                        break;
                    } else {
                        scores[NO_OF_ATTEMPTS][i] = CLOSE;
                        sum += CLOSE;
                    }
                }
            }
        }
        if (sum >= 10 || TO_GUESS.equals(guess)) {
            return true;
        }
        return false;
    }

    void getGuess(String guess) {
        for (int i = 0; i < LENGTH; i++)
        {
            guessBox[NO_OF_ATTEMPTS][i] = guess.charAt(i);
        }
    }

    void writeHistory(String mode) {
        int points = 60 - (NO_OF_ATTEMPTS * 10);
        LocalDateTime date = LocalDateTime.now();
        try {
            FileWriter fileWriter = new FileWriter(new File("history.csv"), true);
            fileWriter.write(mode + "," + TO_GUESS + "," + NO_OF_ATTEMPTS  + "," + points + "," + date + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

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
        Printer.newline(Printer.PURPLE, "💻 Github: w3nash 🤖");
        System.out.println();
        if (i == 1)
        {
            Printer.newline(Printer.CYAN, "🤔 MAIN MENU 📝");
            Printer.newline(Printer.CYAN, "1. 🕹️ SOLO");
            Printer.newline(Printer.CYAN, "2. 🎮 DUO");
            Printer.newline(Printer.CYAN, "3. 💻 CREDITS");
            Printer.newline(Printer.CYAN, "4. 📖 HISTORY");
            Printer.newline(Printer.CYAN, "5. ❎ EXIT");
            System.out.println();
        }
        else if (i == 2)
        {
            Printer.newline(Printer.CYAN, "ℹ️ The game have already randomly selected a 5 letter word. 📖");
            Printer.newline(Printer.CYAN, "ℹ️ Now, try guessing and I will help you in this challenge. 🔍");
            Printer.newline(Printer.CYAN, "ℹ️ I will change the background color of the text as a hint! 🪄");
            Printer.inline(Printer.CYAN, "ℹ️ ");
            Printer.inline(Printer.RED_BG, " X ");
            Printer.newline(Printer.CYAN, " means wrong letter.");
            Printer.inline(Printer.CYAN, "ℹ️ ");
            Printer.inline(Printer.YELLOW_BG, " M ");
            Printer.newline(Printer.CYAN, " means correct letter but at wrong position.");
            Printer.inline(Printer.CYAN, "ℹ️ ");
            Printer.inline(Printer.GREEN_BG, " G ");
            Printer.newline(Printer.CYAN, " means correct letter and correct position.");
            Printer.newline(Printer.CYAN, "ℹ️ Hint 🤖: " + Printer.WHITE + "Starts with " + TO_GUESS.charAt(0));
        }
        else if (i == 3)
        {
            Printer.newline(Printer.CYAN, "💗 CREDITS");
            Printer.newline(Printer.PURPLE, "💻 Developer:");
            Printer.newline(Printer.GREEN, "Jonash Marcelino");
            System.out.println();
            Printer.newline(Printer.CYAN, "🤖 ChatGPT Gang: (Support)");
            Printer.newline(Printer.PURPLE, "Anthony James Bargo");
            Printer.newline(Printer.BLUE, "John Gabriel Cuadro");
            Printer.newline(Printer.RED, "Vaughn Tinte");
            Printer.newline(Printer.GREEN, "Takeshi Okamoto");
            Printer.newline(Printer.YELLOW, "Jhorel Jerard Franco");
            System.out.println();
        }
        else if (i == 4)
        {
            Printer.newline(Printer.CYAN, "📖 Game History: ");
            int n = 0;
            try {
                Scanner csvScanner = new Scanner(new File("history.csv"));
                csvScanner.nextLine();
                while(csvScanner.hasNext()) {
                    n++;
                    String[] history = csvScanner.nextLine().split(",");
                    Printer.newline(Printer.CYAN_BG, "                                       ");
                    System.out.print(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    Printer.inline(Printer.GREEN, "MODE: ");
                    Printer.inline(Printer.WHITE, history[0]);
                    if (history[0].equals("SOLO")) {
                        for (int j = 0; j < 24; j++)
                            System.out.print(" ");
                    } else {
                        for (int j = 0; j < 21; j++)
                            System.out.print(" ");
                    }
                    System.out.println(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    System.out.print(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    Printer.inline(Printer.CYAN, "WORD: ");
                    Printer.inline(Printer.WHITE, history[1]);
                    for (int j = 0; j < 23; j++)
                        System.out.print(" ");
                    System.out.println(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    System.out.print(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    Printer.inline(Printer.YELLOW, "ATTEMPT(S): ");
                    Printer.inline(Printer.WHITE, history[2]);
                    for (int j = 0; j < 21; j++)
                        System.out.print(" ");
                    System.out.println(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    System.out.print(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    Printer.inline(Printer.PURPLE, "POINTS: ");
                    Printer.inline(Printer.WHITE, history[3]);
                    if (Integer.parseInt(history[3]) >= 10) {
                        for (int j = 0; j < 24; j++)
                            System.out.print(" ");
                    } else {
                        for (int j = 0; j < 25; j++)
                            System.out.print(" ");
                    }
                    System.out.println(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    System.out.print(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    Printer.inline(Printer.BLUE, "DATE: ");
                    Printer.inline(Printer.WHITE, history[4]);
                    System.out.println("  " + Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                    Printer.newline(Printer.CYAN_BG, "                                       ");
                    System.out.println();
                }
                csvScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }
            if(n == 0) {
                Printer.newline(Printer.CYAN_BG, "                                       ");
                System.out.print(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                Printer.inline(Printer.WHITE, "No game history found.            ");
                System.out.println(Printer.CYAN_BG + "  " + Printer.RESET_ALL + " ");
                Printer.newline(Printer.CYAN_BG, "                                       ");
            }
            System.out.println();
        }
    }
}
