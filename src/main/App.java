package main;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanf = new Scanner(System.in);
        Game Hmm = new Game();
        int option = 0;
        do
        {
            Hmm.menu(1);
            Printer.inline(Printer.BLUE, "📝 Select: ");

            try {
                option = scanf.nextInt();
            } catch (Exception e) {
                Printer.inline(Printer.YELLOW, "⚠️ Please enter valid input.");
                Hmm.delay(1);
                scanf.next();
            }

            if (option == 5)
            {
                break;
            }
            else if (option == 4)
            {
                Hmm.menu(4);
                Printer.inline(Printer.BLUE, "📝 Press enter to continue... ");
                System.in.read();
            }
            else if (option == 3)
            {
                Hmm.menu(3);
                Printer.inline(Printer.BLUE, "📝 Press enter to continue... ");
                System.in.read();
            }
            else if (option == 2)
            {
                int winner = 0;
                Hmm.newGame();
                Boolean isWinner = false;
                Game Hmm2 = new Game();
                Hmm2.TO_GUESS = Hmm.TO_GUESS;
                do
                {
                    Hmm.menu(2);
                    Printer.inline(Printer.CYAN, "\n🎮 PLAYER 1");
                    Hmm.wordBlock();
                    Printer.inline(Printer.CYAN, "🎮 PLAYER 2");
                    Hmm2.wordBlock();
                    String P1Guess = "";
                    do
                    {
                        Printer.inline(Printer.CYAN, "🤔 Enter Player 1 guess 📝: ");
                        P1Guess = scanf.next().toUpperCase();
                        if (P1Guess.length() != Hmm.LENGTH)
                        {
                            Printer.newline(Printer.YELLOW, "⚠️ You are supposed to enter a " + Hmm.LENGTH + " letter word.");
                            Hmm.delay(1);
                        }
                    }
                    while(P1Guess.length() != Hmm.LENGTH);
                    Hmm.getGuess(P1Guess);
                    if (Hmm.checkWord(P1Guess))
                    {
                        isWinner = true;
                        Hmm.NO_OF_ATTEMPTS++;
                        winner = 1;
                        break;
                    }
                    Hmm.NO_OF_ATTEMPTS++;

                    String P2Guess = "";
                    do
                    {
                        Printer.inline(Printer.CYAN, "🤔 Enter Player 2 guess 📝: ");
                        P2Guess = scanf.next().toUpperCase();
                        if (P2Guess.length() != Hmm2.LENGTH)
                        {
                            Printer.newline(Printer.YELLOW, "⚠️ You are supposed to enter a " + Hmm2.LENGTH + " letter word.");
                            Hmm2.delay(1);
                        }
                    }
                    while(P2Guess.length() != Hmm2.LENGTH);
                    Hmm2.getGuess(P2Guess);
                    if (Hmm2.checkWord(P2Guess))
                    {
                        isWinner = true;
                        Hmm2.NO_OF_ATTEMPTS++;
                        winner = 2;
                        break;
                    }
                    Hmm2.NO_OF_ATTEMPTS++;
                }
                while(Hmm.NO_OF_ATTEMPTS + Hmm2.NO_OF_ATTEMPTS != 12);
                Hmm.menu(2);
                if (winner == 1) {
                    Hmm.wordBlock();
                    Hmm.printResult(isWinner, "DUO", winner);
                } else if (winner == 2) {
                    Hmm2.wordBlock();
                    Hmm2.printResult(isWinner, "DUO", winner);
                } else {
                    Printer.inline(Printer.CYAN, "\n🎮 PLAYER 1");
                    Hmm.wordBlock();
                    Printer.inline(Printer.CYAN, "🎮 PLAYER 2");
                    Hmm2.wordBlock();
                    Hmm.printResult(isWinner, "DUO", winner);
                }
                Printer.inline(Printer.BLUE, "\n📝 Press enter to continue... ");
                System.in.read();
            }
            else if (option == 1)
            {
                Hmm.newGame();
                Boolean isWinner = false;
                do
                {
                    Hmm.menu(2);
                    String guess = "";
                    Hmm.wordBlock();
                    do
                    {
                        Printer.inline(Printer.CYAN, "🤔 Enter your guess 📝: ");
                        guess = scanf.next().toUpperCase();
                        if (guess.length() != Hmm.LENGTH)
                        {
                            Printer.newline(Printer.YELLOW, "⚠️ You are supposed to enter a " + Hmm.LENGTH + " letter word.");
                            Hmm.delay(1);
                        }
                    }
                    while(guess.length() != Hmm.LENGTH);
                    Hmm.getGuess(guess);
                    isWinner = Hmm.checkWord(guess);
                    if (isWinner)
                    {
                        Hmm.NO_OF_ATTEMPTS++;
                        break;
                    }
                    Hmm.NO_OF_ATTEMPTS++;
                }
                while(Hmm.NO_OF_ATTEMPTS != Hmm.ATTEMPTS);
                Hmm.menu(2);
                Hmm.wordBlock();
                Hmm.printResult(isWinner, "SOLO", 0);
                Printer.inline(Printer.BLUE, "\n📝 Press enter to continue... ");
                System.in.read();
            }
            else
            {
                Printer.inline(Printer.YELLOW, "⚠️ Please enter valid option.");
                Hmm.delay(2);
            }
        }
        while(true);
        scanf.close();
    }
}
