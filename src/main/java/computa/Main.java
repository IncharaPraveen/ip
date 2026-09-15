package computa;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** JavaFX user interface for the Computa chatbot. */
public class Main extends Application {
    private static final String TASK_FILE_PATH = "./tasks.txt";
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 450;
    private final Computa computa = new Computa(TASK_FILE_PATH);

    @Override
    public void start(Stage stage) {
        VBox messages = new VBox(10);
        messages.setPadding(new Insets(10));
        ScrollPane chat = new ScrollPane(messages);
        chat.setFitToWidth(true);
        addMessage(messages, "haiii i am computa! lmk what u need ehaha", false);

        // Input is the user's command.
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
            addMessage(messages, command, true);
            addMessage(messages, computa.processCommand(command), false);
            chat.setVvalue(1.0);
            input.clear();
            if (command.equals("bye")) {
                stage.close();
            }
        };
        send.setOnAction(event -> submit.run());
        input.setOnAction(event -> submit.run());

        HBox commandBar = new HBox(8, input, send);
        //create horizontally layed out input & sender

        HBox.setHgrow(input, Priority.ALWAYS);
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

    /** Adds one message row containing the sender's avatar, name, and text. */
    private void addMessage(VBox messages, String text, boolean fromUser) {
        String imagePath = fromUser ? "/EvilPlankton.png" : "/Computa.png";
        var imageStream = Main.class.getResourceAsStream(imagePath);
        Node avatar;
        if (imageStream != null) {
            ImageView imageView = new ImageView(new Image(imageStream));
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);
            imageView.setPreserveRatio(true);
            avatar = imageView;
        } else {
            avatar = new Label(fromUser ? "🙂" : "🤖");
        }

        Label sender = new Label(fromUser ? "User" : "Computa");
        Label message = new Label(text);
        message.setWrapText(true);

        VBox messageContent = new VBox(2, sender, message);
        messageContent.setMaxWidth(450);

        HBox row = new HBox(8, avatar, messageContent);
        row.setMaxWidth(Double.MAX_VALUE);
        row.setAlignment(fromUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        messages.getChildren().add(row);
    }
}
