package computa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Todo> load() throws Computa.ComputaException {
        ArrayList<Todo> todo = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) {

                f.createNewFile();
                return todo;
            }

            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] result = line.split("\\s*\\|\\s*");
                String type = result[0];

                switch (type) {
                    case "T":
                        todo.add(new Todo(result[2], Integer.parseInt(result[1]) != 0));
                        break;
                    case "D":
                        todo.add(new Deadline(result[2], result[3], Integer.parseInt(result[1]) != 0));
                        break;
                    case "E":
                        todo.add(new Event(result[2], result[3], result[4], Integer.parseInt(result[1]) != 0));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new Computa.ComputaException("no saved tasks found girl, starting fresh!");
        } catch (IOException e) {
            throw new Computa.ComputaException("bruh something went wrong with the file: " + e.getMessage());
        }
        return todo;
    }

    public void saveTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("bruh the file won't save: " + e.getMessage());
        }
    }
}