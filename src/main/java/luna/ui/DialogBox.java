package luna.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Represents a single message in the chat, either from the user or from Luna.
 * Each message consists of text and an associated profile image.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box with the specified message text and image.
     *
     * @param text The message text to display.
     * @param img  The profile image associated with the message.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        this.setSpacing(10);
        dialog.setWrapText(true);
        dialog.setMaxWidth(250);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);

    }

    /**
     * Flips the dialog box so that messages appear aligned to the left instead of the right.
     * This is used for Luna's responses to differentiate them from user messages.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for user messages.
     *
     * @param s The user message text.
     * @param i The user's profile image.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.setAlignment(Pos.CENTER_RIGHT);
        db.dialog.setStyle(
                "-fx-background-color: #64B5F6; "
                        + "-fx-text-fill: white; "
                        + "-fx-background-radius: 15; "
                        + "-fx-padding: 10; "
                        + "-fx-border-color: #1E88E5; "
                        + "-fx-border-radius: 15;"
        );
        return db;
    }

    /**
     * Creates a dialog box for Luna's responses.
     *
     * @param s The response message text from Luna.
     * @param i The profile image for Luna.
     * @return A DialogBox representing Luna's response, flipped to the left.
     */
    public static DialogBox getLunaDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.setAlignment(Pos.CENTER_LEFT);
        db.dialog.setStyle(
                "-fx-background-color: #E0E0E0; "
                        + "-fx-text-fill: black; "
                        + "-fx-background-radius: 15; "
                        + "-fx-padding: 10; "
                        + "-fx-border-color: #BDBDBD; "
                        + "-fx-border-radius: 15;"
        );
        return db;
    }
}
