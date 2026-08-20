import java.util.Scanner;
import java.util.ArrayList;
public class Computa {
    public static void main(String[] args) {
        String banner = "haiii i am computa! lmk what u need ehaha";
        System.out.println(banner);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        ArrayList<Todo> todo = new ArrayList<>();// array of Tasks
        while(!command.equals("bye")){
            if(command.equals("list")){
                int i=1;
                for(Todo task : todo){
                    System.out.println(i + ". " + task.getTaskDescription());
                    i++;
                }
                command = scanner.nextLine();
                continue;
            }
            if(command.contains("unmark")){
                int number = Integer.parseInt(command.split(" ")[1]);
                todo.get(number-1).changeStatusIcon();
                System.out.println("ok this task is UNdone neow\n" + todo.get(number-1).getTaskDescription());
                command = scanner.nextLine();
                continue;
            }
                //if command starts with 'mark'
            if(command.contains("mark")){
                int number = Integer.parseInt(command.split(" ")[1]);
                    //get task that they wanna mark

                    todo.get(number-1).changeStatusIcon();
                    //mark the task at that index of the todo
                    System.out.println("ok this task is done neow\n" + todo.get(number-1).getTaskDescription());
                    command = scanner.nextLine();
                    continue;

                }


            //regular command made
            //now need to parse and find out what kind of command is made, add respective task type to list of tasks
            String taskType = command.split(" ")[0];
            switch(taskType){
                case "todo":
                    Todo task = new Todo(command);
                    todo.add(task);//pass task with undone
                    break;
                case "deadline":
                   String parts[] =  command.split("/");
                   //part 0: deadline desc, 1: duedate
                   String desc = parts[0].substring(parts[0].indexOf(" ")+1);
                   String dueDate = parts[1];
                    Deadline deadline = new Deadline(desc,dueDate);
                    todo.add(deadline);
                    break;
                case "event":
                    String part[] = command.split("/");
                    //event desc/dueDATE/ start date
                    String descr =  part[0].substring(part[0].indexOf(" ")+1);
                    Event event = new Event(descr, part[1], part[2]);
                    todo.add(event);
                    break;
            }
            System.out.println("added: " + todo.get(todo.toArray().length-1).getTaskDescription());
            System.out.println("Now u got " + todo.toArray().length + " numba of tasks in da list ");

            command = scanner.nextLine();
        }
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();

    }

}
