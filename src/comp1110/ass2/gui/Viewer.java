package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A very simple viewer for game states in the IQ-Stars game.
 * <p>
 * NOTE: This class is separate from your main game class. This
 * class does not play a game, it just illustrates various game
 * states.
 */
public class Viewer extends Application {


    /* board layout */
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;
    public static final double SIZE_HEIGHT = 68.5;
    public static final double SIZE_WIDTH = 78.5;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;


    /**
     * Draw a game state in the window, removing any previously drawn one
     *
     * @param gameStateString A valid game state string
     */
    void makeGameState(String gameStateString) {

        Group board = new Group();

        board.getChildren().clear();

        // Display the blank board
        double boardH = VIEWER_HEIGHT*2/3;
        double boardW = boardH*1194/650;
        ImageView blankBoard = new ImageView();
        blankBoard.setImage(new Image("file:assets/blankBoardNumbered.png"));
        blankBoard.setFitWidth(boardW);
        blankBoard.setFitHeight(boardH);
        blankBoard.setLayoutX(60);
        blankBoard.setLayoutY((VIEWER_HEIGHT-boardH)/2);
        board.getChildren().add(blankBoard);
        blankBoard.toBack();

        String[] colors = {"red","orange","yellow","green","blue","indigo","pink"};
        double[][] lengths = {{2,2.5},{2,2.5},{2,3},{3,3},{2,3},{1,3},{3,2.5}}; //first height, second width

       // Translate the positions of each piece
       String[] strings = gameStateString.split("W");
       String piecePlacement = strings[0];
       String[] colorsLetter = {"r","o","y","g","b","i","p"};
        List<String> colorList = new ArrayList<>(Arrays.asList(colorsLetter));
        double startX = 60+320/14;
        double startY = 80+320/14;
        for (int j = 0; j < piecePlacement.length(); j+=4) {
           String piece = piecePlacement.substring(j,j+4);
           int colorIndex = colorList.indexOf(piece.substring(0,1));
           int orientation = Integer.parseInt(piece.substring(1,2));

           ImageView iv = new ImageView();
           iv.setImage(new Image("file:assets/"+colors[colorIndex]+"Piece.png"));
           iv.setFitHeight(SIZE_HEIGHT *lengths[colorIndex][0]);
           iv.setFitWidth(SIZE_WIDTH *lengths[colorIndex][1]);

           int pieceX = Integer.parseInt(piece.substring(2,3));
           int pieceY = Integer.parseInt(piece.substring(3,4));
           double boardX;
           if (pieceY % 2 == 0) {
               boardX = startX + pieceX * SIZE_WIDTH;
           }
           else {
               boardX = startX + (pieceX+0.5) * SIZE_WIDTH;
           }
           double boardY = startY + pieceY * SIZE_HEIGHT;
           iv.setLayoutX(boardX);
           iv.setLayoutY(boardY);

           // Set orientation
           Rotate r = new Rotate();
           if (colorIndex == 0 || colorIndex == 5) {orientation = orientation % 3;}
           r.setPivotX(SIZE_WIDTH /2);
           r.setPivotY(SIZE_HEIGHT /2);
           r.setAngle(orientation * 60);
           iv.getTransforms().add(r);
           double setX = iv.getLayoutX();
           double setY = iv.getLayoutY();
           if (orientation == 2 && (colorIndex == 0 || colorIndex == 2 || colorIndex == 6)) {
               if (colorIndex == 6) { setX += SIZE_WIDTH *2; }
               else {setX += SIZE_WIDTH;}
           }
           switch (colorIndex) {
               case 1:
                   switch (orientation) {
                       case 3 -> {
                           setX += SIZE_WIDTH * 1.5;
                           setY += SIZE_HEIGHT;
                       }
                       case 4 -> setY += SIZE_HEIGHT * 2;
                       case 5 -> {
                           setX -= SIZE_WIDTH * 0.5;
                           setY += SIZE_HEIGHT;
                       }
                   }
                   break;
               case 2:
                   switch (orientation) {
                       case 3 -> {
                           setX += SIZE_WIDTH / 2;
                           setY += SIZE_HEIGHT;
                       }
                       case 4 -> {
                           setX += SIZE_WIDTH;
                           setY += SIZE_HEIGHT * 2;
                       }
                       case 5 -> {
                           setX -= SIZE_WIDTH;
                           setY += SIZE_HEIGHT * 2;
                       }
                   }
                   break;
               case 3:
                   switch (orientation) {
                       case 3 -> {
                           setX += SIZE_WIDTH * 2;
                           setY += SIZE_HEIGHT * 2;
                       }
                       case 4 -> {
                           setX -= SIZE_WIDTH / 2;
                           setY += SIZE_HEIGHT * 3;
                       }
                       case 5 -> {
                           setX -= SIZE_WIDTH / 2;
                           setY += SIZE_HEIGHT;
                       }
                   }
                   break;
               case 4:
                   switch (orientation) {
                       case 3 -> {
                           setX += SIZE_WIDTH * 1.5;
                           setY += SIZE_HEIGHT;
                       }
                       case 4 -> {
                           setX += SIZE_WIDTH;
                           setY += SIZE_HEIGHT * 2;
                       }
                       case 5 -> {
                           setX -= SIZE_WIDTH;
                           setY += SIZE_HEIGHT * 2;
                       }
                   }
                   break;
               case 6:
                   switch (orientation) {
                       case 3 -> {
                           setX += SIZE_WIDTH;
                           setY += SIZE_HEIGHT * 2;
                       }
                       case 4 -> setY += SIZE_HEIGHT * 2;
                       case 5 -> {
                           setX -= SIZE_WIDTH / 2;
                           setY += SIZE_HEIGHT;
                       }
                   }
                   break;

           }
           iv.setLayoutX(setX);
           iv.setLayoutY(setY);
           board.getChildren().add(iv);
           iv.toFront();
       }

       // Wizard strings
        if (strings.length == 2) {
            String wizardPlacement = strings[1];
            for (int j = 0; j < wizardPlacement.length(); j+=3) {
                String wizardPiece = wizardPlacement.substring(j,j+3);
                int colorIndex = colorList.indexOf(wizardPiece.substring(0,1));
                int pieceX = Integer.parseInt(wizardPiece.substring(1,2));
                int pieceY = Integer.parseInt(wizardPiece.substring(2,3));

                ImageView image = new ImageView();
                image.setImage(new Image("file:assets/"+colors[colorIndex]+"Wizard.png"));
                image.setFitHeight(SIZE_HEIGHT);
                image.setFitWidth(SIZE_WIDTH);

                double boardX;
                if (pieceY % 2 == 0) {
                    boardX = startX + pieceX * SIZE_WIDTH;
                }
                else {
                    boardX = startX + (pieceX+0.5) * SIZE_WIDTH;
                }
                double boardY = startY + pieceY * SIZE_HEIGHT;
                image.setLayoutX(boardX);
                image.setLayoutY(boardY);
                board.getChildren().add(image);
                image.toFront();}
            }

        root.getChildren().add(board);

    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Game State:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makeGameState(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ Stars Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }}


