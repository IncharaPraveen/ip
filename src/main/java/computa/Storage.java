package computa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Todo> load() throws Computa.ComputaException {
        List<Todo> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] taskData = scanner.nextLine().split("\\s*\\|\\s*");
                    boolean isDone = Integer.parseInt(taskData[1]) != 0;
                    switch (taskData[0]) {
                        case "T" -> tasks.add(new Todo(taskData[2], isDone));
                        case "D" -> tasks.add(new Deadline(taskData[2], taskData[3], isDone));
                        case "E" -> tasks.add(new Event(taskData[2], taskData[3], taskData[4], isDone));
                        default -> { }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new Computa.ComputaException("no saved tasks found girl, starting fresh!");
        } catch (IOException e) {
            throw new Computa.ComputaException("bruh something went wrong with the file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(TaskList taskList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < taskList.getSize(); i++) {
                writer.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("bruh the file won't save: " + e.getMessage());
        }
    }
}
