package luna.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luna.Luna;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Luna luna;
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private final Image lunaImage = new Image(getClass().getResourceAsStream("/images/Luna.png"));


    /**
     * Initializes the GUI components.
     * Binds the scroll pane to automatically scroll down when new messages are added.
     * Sets the send button to trigger user input processing when clicked.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setOnAction(event -> handleUserInput());
    }


    /**
     * Sets the Luna instance and displays the welcome message.
     *
     * @param l The Luna chatbot instance.
     */
    public void setLuna(Luna l) {
        this.luna = l;
        showWelcome(); // Display welcome message on startup
    }

    /**
     * Displays the welcome message.
     */
    private void showWelcome() {
        String welcomeMessage = """
                /\\_/\\
               ( o.o )
                > <3 < \n
                Hello I'm Luna!
                How may I help?
                """;
        dialogContainer.getChildren().add(
                DialogBox.getLunaDialog(welcomeMessage, lunaImage)
        );
    }

    /**
     * Handles user input by creating dialog boxes and updating the UI.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            String response = luna.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLunaDialog(response, lunaImage)
            );
            userInput.clear();

            // Exit app if the command is "bye"
            if (input.equalsIgnoreCase("bye")) {
                closeApplication();
            }
        }
    }

    /**
     * Closes the application.
     */
    private void closeApplication() {
        System.exit(0);
    }
}
