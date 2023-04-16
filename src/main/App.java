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

            if (option == 4)
            {
                break;
            }
            else if (option == 3)
            {
                Hmm.menu(3);
                Hmm.delay(5);
            }
            else if (option == 2)
            {
                Printer.inline(Printer.PURPLE, "🛠️ This feature is currently not available.");
                Hmm.delay(5);
            }
            else if (option == 1)
            {
                Boolean isWinner = false;
                int ATTEMPT = Hmm.NO_OF_ATTEMPTS;
                do
                {
                    Hmm.menu(2);
                    String guess = "";
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
                    isWinner = Hmm.checkWord();
                    if (isWinner)
                    {
                        Hmm.NO_OF_ATTEMPTS++;
                        Hmm.menu(2);
                        break;
                    }
                    Hmm.NO_OF_ATTEMPTS++;
                }
                while(ATTEMPT != Hmm.ATTEMPTS);
                Hmm.menu(2);
                Hmm.printResult(isWinner);
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
