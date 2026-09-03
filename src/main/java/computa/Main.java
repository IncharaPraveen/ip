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
    private final Computa computa = new Computa("./tasks.txt");

    @Override
    public void start(Stage stage) {
        TextArea chat = new TextArea();
        chat.setEditable(false);
        chat.setWrapText(true);
        chat.setText("haiii i am computa! lmk what u need ehaha\n");

        TextField input = new TextField();
        input.setPromptText("Enter a command, e.g. list or todo study");
        Button send = new Button("Send");

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
        HBox.setHgrow(input, javafx.scene.layout.Priority.ALWAYS);
        commandBar.setPadding(new Insets(10));

        BorderPane root = new BorderPane(chat);
        root.setBottom(commandBar);
        BorderPane.setMargin(chat, new Insets(10));
        stage.setTitle("Computa");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }
}
