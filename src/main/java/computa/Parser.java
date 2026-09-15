package computa;

import java.time.DayOfWeek;


/**
 * Processes and executes user commands for the Computa application.
 */
public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";

    public static boolean parse(String command, TaskList taskList, Ui ui, Storage storage)
            throws Computa.ComputaException {
        assert command != null : "The parser must receive a command";
        assert taskList != null : "The parser must receive a task list";
        assert ui != null : "The parser must receive a UI";
        assert storage != null : "The parser must receive storage";
        String trimmedCommand = command.trim();
        if (trimmedCommand.equals(COMMAND_EXIT)) {
            return true;
        }




            if (trimmedCommand.equals(COMMAND_LIST)) {
                int displayIndex = 1;
                for (Todo task : taskList.getVisibleTasks()) {
                    ui.showMessage(displayIndex++ + ". " + task.getTaskDescription());
                }
                return false;
            }
            String[] commandParts = trimmedCommand.split("\\s+");
            String commandName = commandParts[0];
            if (commandName.equals(COMMAND_MARK) || commandName.equals(COMMAND_UNMARK)) {
                int taskNumber = Integer.parseInt(commandParts[1]);
                Todo task = taskList.getVisibleTask(taskNumber - 1);
                task.changeStatusIcon();
                String status = commandName.equals(COMMAND_MARK) ? "done" : "UNdone";
                ui.showMessage("ok this task is " + status + " neow\n" + task.getTaskDescription());
                storage.saveTasks(taskList);
                return false;
            }
            if (commandName.equals(COMMAND_DELETE)) {
                int taskNumber = Integer.parseInt(commandParts[1]);
                Todo removedTask = taskList.getVisibleTask(taskNumber - 1);
                taskList.deleteTask(findTaskIndex(taskList, removedTask));
                ui.showMessage("ok this task is removed neow\n" + removedTask.getTaskDescription());
                storage.saveTasks(taskList);
                return false;
            }
            //if the command contains "find", parse the searchword,
            // search each task in the tasklist's description for the word, if it contains, then add this task to a new list -> display this new list
            if (commandName.equals(COMMAND_FIND)) {
                ui.showMessage("yoo these r the matching tasks!");
                String searchWord = getArgument(trimmedCommand);
                int displayIndex = 1;
                for (Todo task : taskList.getVisibleTasks()) {

                    if (task.getTaskDescription().contains(searchWord)) {
                        ui.showMessage(displayIndex + ". " + task.getTaskDescription());
                    }
                    displayIndex++;
                }
                return false;
            }


            String description;
            switch (commandName) {
                case "todo" -> {
                    if (commandParts.length == 1) {
                        throw new Computa.ComputaException("no desc?");
                    }
                    description = getArgument(trimmedCommand);
                    taskList.addTask(new Todo(description));
                }
                case "deadline" -> {
                    String[] deadlineParts = command.split("/");
                    if (deadlineParts.length < 2 || !deadlineParts[0].contains(" ")) {
                        throw new Computa.ComputaException("no deadline?");
                    }
                    description = getArgument(deadlineParts[0]);
                    taskList.addTask(new Deadline(description, deadlineParts[1].trim()));
                }
                case "event" -> {
                    String[] eventParts = command.split("/");
                    if (eventParts.length < 3 || !eventParts[0].contains(" ")) {
                        throw new Computa.ComputaException("no dates set?");
                    }
                    description = getArgument(eventParts[0]);
                    taskList.addTask(new Event(description, eventParts[1].trim(), eventParts[2].trim()));
                }
                case "recurring" -> {
                    String[] recurringParts = command.split("/", 2);
                    if (recurringParts.length < 2 || !recurringParts[0].contains(" ")) {
                        throw new Computa.ComputaException("use: recurring <description> / <weekday>");
                    }
                    description = getArgument(recurringParts[0]);
                    try {
                        DayOfWeek day = DayOfWeek.valueOf(recurringParts[1].trim().toUpperCase());
                        taskList.addTask(new RecurringTask(description, day));
                    } catch (IllegalArgumentException e) {
                        throw new Computa.ComputaException("weekday must be Monday to Sunday");
                    }
                }
                default -> throw new Computa.ComputaException("bruh what is u talkin about");
            }
            assert taskList.getSize() > 0 : "Creating a task must leave at least one task";
            if (description.isEmpty()) {
                throw new Computa.ComputaException("you forgot to desc ur task. lock in bruh");
            }
            ui.showMessage("added: " + taskList.getTask(taskList.getSize() - 1).getTaskDescription());
            ui.showMessage("Now u got " + taskList.getSize() + " numba of tasks in da list ");
            storage.saveTasks(taskList);
            return false;
        }

        private static int findTaskIndex(TaskList taskList, Todo target) {
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.getTask(i) == target) {
                    return i;
                }
            }
            throw new IndexOutOfBoundsException();
        }

        /** Returns the text following the command name. */
        private static String getArgument (String command){
            int separator = command.indexOf(' ');
            return separator < 0 ? "" : command.substring(separator + 1).trim();
        }
    }


