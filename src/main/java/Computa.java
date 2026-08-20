import java.util.Scanner;
import java.util.ArrayList;
public class Computa {
    public static void main(String[] args) {
        String banner = "haiii i am computa! lmk what u need ehaha";
        System.out.println(banner);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        ArrayList<Task> todo = new ArrayList<>();// array of Tasks
        while(!command.equals("bye")){
            if(command.equals("list")){
                int i=1;
                for(Task task : todo){
                    System.out.println(i + ". " + task.getTaskDescription());
                    i++;
                }
                command = scanner.nextLine();
                continue;
            }
            if(command.contains("unmark")){
                int number = ((int) command.charAt(7)) - 48;
                todo.get(number-1).changeStatusIcon();
                System.out.println("ok this task is UNdone neow\n" + todo.get(number).getTaskDescription());
                command = scanner.nextLine();
                continue;
            }
                //if command starts with 'mark'
            if(command.contains("mark")){
                    int number = ((int) command.charAt(5)) - 48;
                    //get task that they wanna mark

                    todo.get(number-1).changeStatusIcon();
                    //mark the task at that index of the todo
                    System.out.println("ok this task is done neow\n" + todo.get(number).getTaskDescription());
                    command = scanner.nextLine();
                    continue;

                }


            //regular command made

            System.out.println("added: " + command);
           Task task = new Task(command);
            todo.add(task);//pass task with undone
            command = scanner.nextLine();
        }
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();

    }

}
