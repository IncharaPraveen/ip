
package computa;
import computa.Parser;
import computa.Storage;
import computa.TaskList;
import computa.Ui;

/*import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Computa {
    public static void main(String[] args) {
        String banner = "haiii i am computa! lmk what u need ehaha";
        System.out.println(banner);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        ArrayList<computa.Todo> todo = new ArrayList<>();// array of Tasks
        //here, load the array of tasks into this arraylist, import from tasks.txt
        //every time we make a change, tasks array is updated (remove /add elem) -> update the tasks.txt file too
        //function to update tasks.txt file, function to load the tasks into the array at the beginning
        //func for each task to parse and
// when we start, call everyt from tasks.txt
        loadTasks(todo);
        while(!command.equals("bye")){
            try {
                if (command.equals("list")) {
                    int i = 1;
                    for (computa.Todo task : todo) {
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
                        saveTasks(todo);
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
                        saveTasks(todo);
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
                                throw new Computa.ComputaException("no desc?");
                            }
                                desc = command.split(" ")[1];
                            computa.Todo task = new computa.Todo(desc);
                            todo.add(task);//pass task with undone
                            break;
                        case "deadline":
                            String parts[] = command.split("/");
                            if(parts[0].indexOf(" ") == -1){
                                throw new Computa.ComputaException("no desc?");
                            }
                            if(parts.length == 1){
                                //means no /,
                                throw new Computa.ComputaException("no deadline?");

                            }
                            //part 0: deadline desc, 1: duedate

                            desc = parts[0].substring(parts[0].indexOf(" ") + 1);
                            dueDate = parts[1].trim();
                            computa.Deadline deadline = new computa.Deadline(desc, dueDate);
                            todo.add(deadline);

                            break;
                        case "event":
                            String part[] = command.split("/");
                            if(part[0].indexOf(" ") == -1){
                                throw new Computa.ComputaException("no desc?");
                            }

                            if(part.length < 3){
                                //means no /,
                                throw new Computa.ComputaException("no dates set?");

                            }
                            //event desc/dueDATE/ start date

                            desc = part[0].substring(part[0].indexOf(" ") + 1);
                            computa.Event event = new computa.Event(desc, part[1].trim(), part[2].trim());
                            todo.add(event);
//make sure u trim the string before passing it to constructor -> to be converted to date/time

                            break;
                        default:
                            throw new Computa.ComputaException("bruh what is u talkin about");

                    }
                    if(desc.equals("")){
                        throw new Computa.ComputaException("you forgot to desc ur task. lock in bruh");
                    }
                    System.out.println("added: " + todo.get(todo.toArray().length - 1).getTaskDescription());
                    System.out.println("Now u got " + todo.toArray().length + " numba of tasks in da list ");
                }

            }
            catch(IndexOutOfBoundsException e){
                System.out.println("that task number doesnt exist girl");
            }
            catch(Computa.ComputaException e){
                System.out.println(e.getMessage());
            }
            //aft each update, make sure u save d update in tasks.txt
            saveTasks(todo);
            command = scanner.nextLine();
        }
        System.out.println("Bai Bai! How much wood could a wood chuck chuck..");
        scanner.close();

    }
    //loadTasks will load from file -> todo array
    private static void loadTasks(ArrayList<computa.Todo> todo) {

        try {
            java.io.File f = new java.io.File("./src/main/java/tasks.txt");
            if (!f.exists()) {

                f.createNewFile();
                return;
            }

            Scanner s = new Scanner(f); //take file contents as input
            while (s.hasNext()) {
                String line = s.nextLine();
                //each task from the tasks.txt will be in format
                //T | 1 | desc
                //E | 1 | desc |from | to
                //D | 1 | desc | deadline
                //shldnt do this storage of tasks in the same way as task desc task desc is TOO HARD TO PARSE
               //get each line from the file, put it to the todolist
                String[] result = line.split("\\s*\\|\\s*");

                //result is an array of strings
                String type = result[0];
                switch (type){
                    case "T":
System.out.println("here");
                       computa.Todo td=  new computa.Todo(result[2], Integer.parseInt(result[1]) != 0);
                        todo.add(td);

                        break;
                    case "D":
                        computa.Deadline dl= new computa.Deadline(result[2], result[3],Integer.parseInt(result[1]) != 0 );
                        todo.add(dl);
                        break;
                    case "E":
                        computa.Event e = new computa.Event(result[2], result[3], result[4], Integer.parseInt(result[1]) != 0 );
                        todo.add(e);
                       break;
                }

            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("no saved tasks found girl, starting fresh!");
        }
        catch (java.io.IOException e) {
            // Note: we changed FileNotFoundException to IOException because
            // createNewFile() can throw an IOException!
            System.out.println("bruh something went wrong with the file: " + e.getMessage());
        }
    }
    private static void saveTasks(ArrayList<computa.Todo> todo) {
        try {

            java.io.FileWriter fw = new java.io.FileWriter("./src/main/java/tasks.txt");
            for (computa.Todo task : todo) {
                fw.write(task.toFileFormat() + System.lineSeparator());
                //write each task in todolist into the file tasks.txt, in the correct format

            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println("bruh the file won't save: " + e.getMessage());
        }
    }


}
*/

public class Computa {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Computa(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {

            tasks = new TaskList(storage.load());
        } catch (ComputaException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {

                String fullCommand = ui.readCommand();


                isExit = Parser.parse(fullCommand, tasks, ui, storage);

            } catch (IndexOutOfBoundsException e) {
                ui.showError("that task number doesnt exist girl");
            } catch (ComputaException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("bruh put a real number");
            }
        }
        ui.showGoodbye();
    }
    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {

        new Computa("./tasks.txt").run();
    }

    public static class ComputaException extends Exception{
        public ComputaException(String msg) {
            super(msg);
        }
    }
}