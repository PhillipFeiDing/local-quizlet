package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Vector;

public class MainScene {

    public static final int SCENE_WIDTH = 600;
    public static final int SCENE_HEIGHT = 400;
    private Scene mainScene;
    private VBox recordBoard;
    private ImageView imgView;
    private String stuName;
    private boolean isCorrect;
    private Label feedback, prompt, scoreLabel;
    private StudentData stuData;
    private Button button;
    private int round, curScore, curAttempt;


    /**
     * Appeasement
     */
    public MainScene() {
        buildMainScene();
    }

    /**
     * Appeasement
     */
    public void buildMainScene() {
        stuData = new StudentData();

        prompt = new Label("What is the name of this person?");

        imgView = new ImageView();

        TextField input = new TextField();
        input.setMaxWidth(200);
        feedback = new Label("Not Responded");
        scoreLabel = new Label("Score: "
                + curScore + "/" + stuData.getSize());

        button = new Button("Confirm");
        button.setOnAction(e -> {
            curAttempt++;
            String response = input.getText();
            if (response.equalsIgnoreCase(stuName)) {
                isCorrect = true;
                feedback.setText("Correct");
                feedback.setTextFill(Color.LIGHTGREEN);
            } else {
                isCorrect = false;
                feedback.setText("Incorrect");
                feedback.setTextFill(Color.RED);
            }
            input.clear();
            if (isCorrect) {
                if (curAttempt == 1) {
                    curScore++;
                    scoreLabel.setText("Score: "
                            + curScore + "/" + stuData.getSize());
                }
                setNew();
            }
        });


        VBox left = new VBox();
        left.getChildren().addAll(prompt, imgView, input,
                button, feedback, scoreLabel);
        left.setAlignment(Pos.CENTER);
        left.setSpacing(10);
        left.setPadding(new Insets(20,20,20,20));

        recordBoard = new VBox();
        recordBoard.setAlignment(Pos.TOP_LEFT);
        recordBoard.getChildren().addAll(new Label("Record"));

        recordBoard.setPadding(new Insets(20,20,20,20));

        HBox layout = new HBox();
        layout.getChildren().addAll(left, recordBoard);

        mainScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        mainScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                button.fire();
            }
        });

        round = 0;

        setNew();
    }

    /**
     * Appeasement
     * @return appeasement
     */
    public Scene getScene() {
        return mainScene;
    }

    /**
     * Apeasement
     * @param name appeasement
     * @param image appeasement
     */
    public void setNew() {
        if (round % stuData.getSize() == 0) {
            if (round != 0) {
                recordBoard.getChildren().add(new Label("Trial" +
                        (round / stuData.getSize()) + " done with score of " +
                        curScore + "/" + stuData.getSize() + "."));
            }
            stuData.shuffle();
            curScore = 0;
            scoreLabel.setText("Score: "
                    + curScore + "/" + stuData.getSize());
        }
        int idx = round % stuData.getSize();
        stuName = stuData.getName(idx);
        imgView.setImage(stuData.getImage(idx));
        prompt.setText("Question " + (round + 1)
                + ": " + "What is this?");
        feedback.setText("Not Responded");
        feedback.setTextFill(Color.LIGHTGREEN);
        isCorrect = false;
        curAttempt = 0;
        round++;
    }
}
