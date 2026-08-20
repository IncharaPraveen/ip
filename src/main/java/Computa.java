import java.util.Scanner;
public class Computa {
    public static void main(String[] args) {
        String banner = "haiii i am computa! lmk what u need ehaha";
        System.out.println(banner);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while(!command.equals("bye")){
            System.out.println(command);
            command = scanner.nextLine();
        }
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();

    }
}
