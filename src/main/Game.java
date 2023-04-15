package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Game {
    final int ATTEMPTS = 6;
    final int LENGTH = 5;
    String TO_GUESS = choose_word();
    
    // Choose Word
    static String choose_word() {
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
}
