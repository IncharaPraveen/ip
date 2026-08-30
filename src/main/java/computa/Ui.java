package computa;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("haiii i am computa! lmk what u need ehaha");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();
    }
}
