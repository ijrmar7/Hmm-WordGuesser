package main;

public class App {
    public static void main(String[] args) throws Exception {
        Printer p = new Printer();
        Game g = new Game();

        p.println(p.BLACK, "Hello!");
        p.println(p.BLUE, g.TO_GUESS);
    }
}
