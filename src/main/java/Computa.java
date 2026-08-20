import java.util.Scanner;
import java.util.ArrayList;
public class Computa {
    public static void main(String[] args) {
        String banner = "haiii i am computa! lmk what u need ehaha";
        System.out.println(banner);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        ArrayList<String> todo = new ArrayList<>();
        while(!command.equals("bye")){
            if(command.equals("list")){
                int i=1;
                for(String item : todo){
                    System.out.println(i+". "+ item+ "\n");
                    i++;
                }
                command = scanner.nextLine();
                continue;
            }
            System.out.println("added: " + command);
            todo.add(command);
            command = scanner.nextLine();
        }
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();

    }
}
