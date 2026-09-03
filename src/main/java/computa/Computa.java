package computa;
import computa.Parser;
import computa.Storage;
import computa.TaskList;
import computa.Ui;

public class Computa {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                shouldExit = Parser.parse(ui.readCommand(), tasks, ui, storage);
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
     * Processes one command and returns the text that should be shown to a GUI.
     *
     * @param command the command entered by the user
     * @return the chatbot response, including any error message
     */
    public String processCommand(String command) {
        try {
            Parser.parse(command, tasks, ui, storage);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("that task number doesnt exist girl");
        } catch (ComputaException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("bruh put a real number");
        }
        return ui.collectOutput();
    }
    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {
        new Computa("./tasks.txt").run();
    }

    public static class ComputaException extends Exception {
        public ComputaException(String message) {
            super(message);
        }
    }
}
