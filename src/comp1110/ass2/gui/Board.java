package comp1110.ass2.gui;

import comp1110.ass2.Games;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    private final Group root = new Group();

    // FIXME Task 8 (CR): Implement a basic playable IQ Stars game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9 (D): Implement challenges (you may use the set of challenges in the Games class)
    public static void slider(Group group){

        Text text = new Text();
        text.setText("Difficulty:");

        TextField textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("new difficulty");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int difficulty = Integer.parseInt(textField.getText());
                String challenge = Games.newChallenge(difficulty);
                Viewer.makeGameState(challenge);
                textField.clear();
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(text, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(50);

        group.getChildren().add(hb);
    }
    // FIXME Task 11 (HD): Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 12 (HD): Generate interesting challenges (each challenge must have exactly one solution)

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ Stars");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
