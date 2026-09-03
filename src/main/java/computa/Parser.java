package computa;
/**
 * Processes and executes user commands for the Computa application.
 */
public class Parser {
    public static boolean parse(String command, TaskList taskList, Ui ui, Storage storage)
            throws Computa.ComputaException {
        if (command.equals("bye")) {
            return true;
        }
        if (command.equals("list")) {
            for (int i = 0; i < taskList.getSize(); i++) {
                ui.showMessage((i + 1) + ". " + taskList.getTask(i).getTaskDescription());
            }
            return false;
        }
        String[] commandParts = command.split(" ");
        String commandName = commandParts[0];
        if (commandName.equals("mark") || commandName.equals("unmark")) {
            int taskNumber = Integer.parseInt(commandParts[1]);
            Todo task = taskList.getTask(taskNumber - 1);
            task.changeStatusIcon();
            String status = commandName.equals("mark") ? "done" : "UNdone";
            ui.showMessage("ok this task is " + status + " neow\n" + task.getTaskDescription());
            storage.saveTasks(taskList);
            return false;
        }
        if (commandName.equals("delete")) {
            int taskNumber = Integer.parseInt(commandParts[1]);
            Todo removedTask = taskList.deleteTask(taskNumber - 1);
            ui.showMessage("ok this task is removed neow\n" + removedTask.getTaskDescription());
            storage.saveTasks(taskList);
            return false;
        }
        //if the command contains "find", parse the searchword,
        // search each task in the tasklist's description for the word, if it contains, then add this task to a new list -> display this new list
        if (command.contains("find")) {
            ui.showMessage("yoo these r the matching tasks!");
            String searchWord = command.substring(command.indexOf(" ") + 1).trim();
            for (int i = 0; i < taskList.getSize(); i++) {
                Todo task = taskList.getTask(i);

                if (task.getTaskDescription().contains(searchWord)) {
                    ui.showMessage((i + 1) + ". " + task.getTaskDescription());
                }
            }
            return false;
        }


        String description;
        switch (commandName) {
            case "todo" -> {
                if (commandParts.length == 1) {
                    throw new Computa.ComputaException("no desc?");
                }
                description = command.substring(command.indexOf(" ") + 1).trim();
                taskList.addTask(new Todo(description));
            }
            case "deadline" -> {
                String[] deadlineParts = command.split("/");
                if (deadlineParts.length < 2 || !deadlineParts[0].contains(" ")) {
                    throw new Computa.ComputaException("no deadline?");
                }
                description = deadlineParts[0].substring(deadlineParts[0].indexOf(" ") + 1).trim();
                taskList.addTask(new Deadline(description, deadlineParts[1].trim()));
            }
            case "event" -> {
                String[] eventParts = command.split("/");
                if (eventParts.length < 3 || !eventParts[0].contains(" ")) {
                    throw new Computa.ComputaException("no dates set?");
                }
                description = eventParts[0].substring(eventParts[0].indexOf(" ") + 1).trim();
                taskList.addTask(new Event(description, eventParts[1].trim(), eventParts[2].trim()));
            }
            default -> throw new Computa.ComputaException("bruh what is u talkin about");
        }
        if (description.isEmpty()) {
            throw new Computa.ComputaException("you forgot to desc ur task. lock in bruh");
        }
        ui.showMessage("added: " + taskList.getTask(taskList.getSize() - 1).getTaskDescription());
        ui.showMessage("Now u got " + taskList.getSize() + " numba of tasks in da list ");
        storage.saveTasks(taskList);
        return false;
    }
}
