package com.company;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
    public static final int DEFAULT_MIN_WIDTH = 200;
    public static final int DEFAULT_MIN_Height = 120;
    public static final String DEFAULT_CONFIRM = "Close";
    /**
     * create a new alert message box with designated title, message, and desired height and width. Height and width
     * will be automatically adjusted according to contents displayed. The box means to block the functionality of
     * other windows until a response has been made.
     * @param title a title that will be displayed on the top bar
     * @param message main content message
     * @param confirm confirm message shown on the close button
     * @param minHeight the minimum height of the message box
     * @param minWidth the minimum width of the message box
     */
    public static void display(String title, String message, String confirm, int minWidth, int minHeight) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button(confirm);

        closeButton.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * create a new alert message box with designated title, message, and default height and width. Height and width
     * will be automatically adjusted according to contents displayed. The box means to block the functionality of
     * other windows until a response has been made.
     * @param title a title that will be displayed on the top bar
     * @param message main content message
     */
    public static void display(String title, String message) {
        AlertBox.display(title, message,
                AlertBox.DEFAULT_CONFIRM,
                AlertBox.DEFAULT_MIN_WIDTH,
                AlertBox.DEFAULT_MIN_Height);
    }

    /**
     * create a new alert message box with designated title, message, confirm button message , and default height and
     * width. Height and width will be automatically adjusted according to contents displayed. The box means to block
     * the functionality of other windows until a response has been made.
     * @param title a title that will be displayed on the top bar
     * @param message main content message
     * @param confirm text shown on the confirm button
     */
    public static void display(String title, String message, String confirm) {
        AlertBox.display(title, message, confirm, AlertBox.DEFAULT_MIN_WIDTH, AlertBox.DEFAULT_MIN_Height);
    }
}
