package computa;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/** JavaFX user interface for the Computa chatbot. */
public class Main extends Application {
    private static final String TASK_FILE_PATH = "./tasks.txt";
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 450;
    private final Computa computa = new Computa(TASK_FILE_PATH);

    @Override
    public void start(Stage stage) {
        TextArea chat = new TextArea();
        chat.setEditable(false);
        chat.setWrapText(true);
        chat.setText("haiii i am computa! lmk what u need ehaha\n");
//input is user's input,
        TextField input = new TextField();
        input.setPromptText("Enter a command, e.g. list or todo study");
        Button send = new Button("Send");
//create a send button, setOnAction , call eventHandler submit -> we will send the input to Parser.java
        //
        Runnable submit = () -> {
            String command = input.getText().trim();
            if (command.isEmpty()) {
                return;
            }
            chat.appendText("> " + command + "\n" + computa.processCommand(command));
            input.clear();
            if (command.equals("bye")) {
                stage.close();
            }
        };
        send.setOnAction(event -> submit.run());
        input.setOnAction(event -> submit.run());

        HBox commandBar = new HBox(8, input, send);
        //create horizontally layed out input & sender

        HBox.setHgrow(input, javafx.scene.layout.Priority.ALWAYS);
        commandBar.setPadding(new Insets(10));

        BorderPane root = new BorderPane(chat);
        root.setBottom(commandBar);
        BorderPane.setMargin(chat, new Insets(10));
        stage.setTitle("Computa");
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.show();
        //stage is whatever is on comp screen, attatch a scene to a stage ( scene contains all layout + ui elems)
        //scene starts w root elem -> this is BoderPane, containing the chat
    }
}
