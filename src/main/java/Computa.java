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
            try {
                if (command.equals("list")) {
                    int i = 1;
                    for (Todo task : todo) {
                        System.out.println(i + ". " + task.getTaskDescription());
                        i++;
                    }
                    command = scanner.nextLine();
                    continue;
                }
                if (command.contains("unmark")) {

                        int number = Integer.parseInt(command.split(" ")[1]);

                        todo.get(number - 1).changeStatusIcon();
                        System.out.println("ok this task is UNdone neow\n" + todo.get(number - 1).getTaskDescription());

                        continue;

                }
                //if command starts with 'mark'
                if (command.contains("mark")) {

                        int number = Integer.parseInt(command.split(" ")[1]);
                        //get task that they wanna mark

                        todo.get(number - 1).changeStatusIcon();
                        //mark the task at that index of the todo
                        System.out.println("ok this task is done neow\n" + todo.get(number - 1).getTaskDescription());
                    command = scanner.nextLine();
                        continue;

                }
                if(command.contains("delete")){

                        int number = Integer.parseInt(command.split(" ")[1]);
                        //get task that they wanna delete
                        String removedtask = todo.get(number - 1).getTaskDescription();
                        todo.remove(number - 1);
                        //mark the task at that index of the todo
                        System.out.println("ok this task is removed neow\n" + removedtask);
                    command = scanner.nextLine();
                        continue;


                }else {
                    //regular command made
                    //now need to parse and find out what kind of command is made, add respective task type to list of tasks
                    String taskType = command.split(" ")[0];
                    String desc="";
                    String dueDate="";
                    String start="";

                    switch (taskType) {
                        case "todo":

                            if(command.split(" ").length == 1){
                                throw new ComputaException("no desc?");
                            }
                                desc = command.split(" ")[1];
                            Todo task = new Todo(desc);
                            todo.add(task);//pass task with undone
                            break;
                        case "deadline":
                            String parts[] = command.split("/");
                            if(parts[0].indexOf(" ") == -1){
                                throw new ComputaException("no desc?");
                            }
                            if(parts.length == 1){
                                //means no /,
                                throw new ComputaException("no deadline?");

                            }
                            //part 0: deadline desc, 1: duedate

                            desc = parts[0].substring(parts[0].indexOf(" ") + 1);
                            dueDate = parts[1];
                            Deadline deadline = new Deadline(desc, dueDate);
                            todo.add(deadline);

                            break;
                        case "event":
                            String part[] = command.split("/");
                            if(part[0].indexOf(" ") == -1){
                                throw new ComputaException("no desc?");
                            }

                            if(part.length < 3){
                                //means no /,
                                throw new ComputaException("no dates set?");

                            }
                            //event desc/dueDATE/ start date

                            desc = part[0].substring(part[0].indexOf(" ") + 1);
                            Event event = new Event(desc, part[1], part[2]);
                            todo.add(event);

                            break;
                        default:
                            throw new ComputaException("bruh what is u talkin about");

                    }
                    if(desc.equals("")){
                        throw new ComputaException("you forgot to desc ur task. lock in bruh");
                    }
                    System.out.println("added: " + todo.get(todo.toArray().length - 1).getTaskDescription());
                    System.out.println("Now u got " + todo.toArray().length + " numba of tasks in da list ");
                }

            }
            catch(IndexOutOfBoundsException e){
                System.out.println("that task number doesnt exist girl");
            }
            catch(ComputaException e){
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();

    }

}
