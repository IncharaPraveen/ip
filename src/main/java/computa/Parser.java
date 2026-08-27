package computa;

public class Parser {
    public static boolean parse(String command, TaskList taskList, Ui ui, Storage storage) throws Computa.ComputaException {
        if (command.equals("bye")) {
            return true; // Signals the main loop to exit
        }

        if (command.equals("list")) {
            for (int i = 0; i < taskList.getSize(); i++) {
                ui.showMessage((i + 1) + ". " + taskList.getTask(i).getTaskDescription());
            }
            return false;
        }

        if (command.contains("unmark")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            taskList.getTask(number - 1).changeStatusIcon();
            ui.showMessage("ok this task is UNdone neow\n" + taskList.getTask(number - 1).getTaskDescription());
            storage.saveTasks(taskList);
            return false;
        }

        if (command.contains("mark")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            taskList.getTask(number - 1).changeStatusIcon();
            ui.showMessage("ok this task is done neow\n" + taskList.getTask(number - 1).getTaskDescription());
            storage.saveTasks(taskList);
            return false;
        }

        if (command.contains("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            String removedtask = taskList.getTask(number - 1).getTaskDescription();
            taskList.deleteTask(number - 1);
            ui.showMessage("ok this task is removed neow\n" + removedtask);
            storage.saveTasks(taskList);
            return false;
        }

        // Handling creation commands (todo, deadline, event)
        String taskType = command.split(" ")[0];
        String desc = "";
        String dueDate = "";

        switch (taskType) {
            case "todo":
                if (command.split(" ").length == 1) {
                    throw new Computa.ComputaException("no desc?");
                }
                desc = command.substring(command.indexOf(" ") + 1).trim();
                taskList.addTask(new Todo(desc));
                break;
            case "deadline":
                String[] parts = command.split("/");
                if (parts[0].indexOf(" ") == -1) {
                    throw new Computa.ComputaException("no desc?");
                }
                if (parts.length == 1) {
                    throw new Computa.ComputaException("no deadline?");
                }
                desc = parts[0].substring(parts[0].indexOf(" ") + 1).trim();
                dueDate = parts[1].trim();
                taskList.addTask(new Deadline(desc, dueDate));
                break;
            case "event":
                String[] part = command.split("/");
                if (part[0].indexOf(" ") == -1) {
                    throw new Computa.ComputaException("no desc?");
                }
                if (part.length < 3) {
                    throw new Computa.ComputaException("no dates set?");
                }
                desc = part[0].substring(part[0].indexOf(" ") + 1).trim();
                taskList.addTask(new Event(desc, part[1].trim(), part[2].trim()));
                break;
            default:
                throw new Computa.ComputaException("bruh what is u talkin about");
        }

        if (desc.equals("")) {
            throw new Computa.ComputaException("you forgot to desc ur task. lock in bruh");
        }

        ui.showMessage("added: " + taskList.getTask(taskList.getSize() - 1).getTaskDescription());
        ui.showMessage("Now u got " + taskList.getSize() + " numba of tasks in da list ");
        storage.saveTasks(taskList);

        return false;
    }
}