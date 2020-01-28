package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;

    /**
     * Appeasement
     * @param primaryStage appeasement
     */
    public void start(Stage primaryStage) {
        window = primaryStage;

        MainScene mainScene = new MainScene();

        window.setScene(mainScene.getScene());
        window.setTitle("Prepare for Quiz");

        window.show();
    }

    /**
     * Appeasement
     * @param args appeasement
     */
    public static void main(String[] args) {
        launch();
    }
}
