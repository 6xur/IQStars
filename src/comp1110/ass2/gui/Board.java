package comp1110.ass2.gui;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import comp1110.ass2.Games;
import comp1110.ass2.IQStars;
import comp1110.ass2.Location;
import comp1110.ass2.Piece;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.*;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int MARGIN = 10;
    private static final int STAR_WIDTH = 85;
    private static final int STAR_HEIGHT = 75;
    private double BLANK_BOARD_HEIGHT = BOARD_HEIGHT / 2;
    double BLANK_BOARD_WIDTH = BLANK_BOARD_HEIGHT * 1194 / 650;
    private static Set<GUIPiece> placedPiece = new HashSet<>();

    private final Group root = new Group();

    // FIXME Task 8 (CR): Implement a basic playable IQ Stars game in JavaFX that only allows pieces to be placed in valid places
    class GUIPiece extends ImageView {
        private double mouseX;
        private double mouseY;
        private double homeX;
        private double homeY;
        private int colorIndex;
        private int orientation;
        private double originalX;
        private double originalY;
        private double setX;
        private double setY;
        String[] colors = {"red", "orange", "yellow", "green", "blue", "indigo", "pink"};

        GUIPiece(int colorIndex, double homeX, double homeY) {
            this.orientation = 0;
            this.colorIndex = colorIndex;
            this.homeX = homeX;
            this.homeY = homeY;
            this.originalX = homeX;
            this.originalY = homeY;

            double[][] starNumber = {{2, 2.5}, {2, 2.5}, {2, 3}, {3, 3}, {2, 3}, {1, 3}, {3, 2.5}};
            String color = colors[colorIndex];

            // set the piece image
            Image image = new Image("file:assets/" + color + "Piece.png");
            setImage(image);
            setFitHeight(STAR_HEIGHT * starNumber[colorIndex][0]);
            setFitWidth(STAR_WIDTH * starNumber[colorIndex][1]);
            setLayoutX(homeX);
            setLayoutY(homeY);

            // make the piece draggable
            this.setOnMousePressed(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                toFront();
            });
            this.setOnMouseDragged(event -> {
                double dX = event.getSceneX() - mouseX;
                double dY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + dX);
                setLayoutY(getLayoutY() + dY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                toFront();
            });
            this.setOnMouseReleased(event -> {
                double[] star = findNearestStar(getLayoutX(), getLayoutY());
                setLayoutX(star[0]);
                setLayoutY(star[1]);
                placedPiece.add(this);

                // if the current state is not valid, the piece cannot be put on board
                if (!isStateValid()) {
                    this.setLayoutX(originalX);
                    this.setLayoutY(originalY);
                    placedPiece.remove(this);
                }
            });

            // rotate the piece by scrolling the mouse
            setOnScroll(event -> {
                if (event.getDeltaY() != 0.0) {
                    orientation += Math.abs((int) (event.getDeltaY()));
                    orientation = orientation % 6;
                    System.out.println(orientation);
                    rotate();
                }
            });

        }

        public void setOrientation(int orientation){
            this.orientation = orientation;
        }

        public int getColorIndex(){
            return this.colorIndex;
        }

        /**
         * the method will check if the current state on board is valid or not
         *
         * @return true if the current state is valid, and false otherwise
         */
        private boolean isStateValid() {
            String stateString = "W";
            System.out.println(placedPiece);
            for (GUIPiece p : placedPiece) {
                String pieceString = p.toString();
                if (stateString == "W") {
                    stateString = pieceString + "W";
                } else {
                        String test = Piece.placePiece(pieceString, stateString);
                    System.out.println(p);
                    System.out.println("current state: " + test);
                    if (test == "invalid input") {
                        return false;
                    }
                    if (test == "WW") {
                        return false;
                    }
                    if (test != "invalid input" && test != "WW") {
                        stateString = test;
                    }
                }
            }
            return IQStars.isGameStateValid(stateString);
        }


        /**
         * @param starX  the x coordinate of the star
         * @param starY  the y coordinate of the star
         * @param pieceX the x coordinate of the first star in the piece
         * @param pieceY the y coordinate of the first star in the piece
         * @return the distance between the star and the first star of the piece
         */
        private double distance(double starX, double starY, double pieceX, double pieceY) {
            double centreStarX = starX + STAR_WIDTH / 2;
            double centreStarY = starY + STAR_HEIGHT / 2;
            double centrePieceX = pieceX + STAR_WIDTH / 2;
            double centrePieceY = pieceY + STAR_HEIGHT / 2;
            double d = Math.sqrt(Math.pow(centreStarX - centrePieceX, 2) + Math.pow(centreStarY - centrePieceY, 2));
            return d;
        }

        /**
         * @param x the x coordinate of the target point
         * @param y the y coordinate of the target point
         * @return the coordinate of the nearest star to the target point
         */
        double[] findNearestStar(double x, double y) {
            double distance = 10000000;
            double[] min = {MARGIN * 3.5, (BOARD_HEIGHT - BLANK_BOARD_HEIGHT) / 2 + STAR_HEIGHT / 3};

            // find the location of each star
            ArrayList<double[]> stars = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                Location l = new Location(i);
                int starX = l.getX();
                int starY = l.getY();
                double[] star = new double[2];
                if (starY % 2 == 1) {
                    star[0] = min[0] + (starX + 0.5) * STAR_WIDTH;
                } else {
                    star[0] = min[0] + starX * STAR_WIDTH;
                }
                star[1] = min[1] + starY * STAR_HEIGHT;
                stars.add(star);
            }

            // find star with the smallest distance to the target piece
            for (double[] star : stars) {
                if (distance > distance(star[0], star[1], x, y)) {
                    distance = distance(star[0], star[1], x, y);
                    min = star;
                }
            }
            return min;
        }

        /**
         * The method rotates the piece given its orientation number
         */
        public void rotate() {
            Rotate r = new Rotate();
            r.setPivotX(STAR_WIDTH / 2);
            r.setPivotY(STAR_HEIGHT / 2);
            r.setAngle(60);
            this.getTransforms().add(r);
        }

        public void updateLocation() {
            switch (colorIndex) {
                case 0:
                    switch (orientation) {
                        case 1 -> {
                            //setX -= STAR_WIDTH;
                            break;
                        }
                        case 2 -> {
                            setX -= STAR_WIDTH;
                            //setY += STAR_HEIGHT;
                            break;
                        }
                        case 3 -> {
                            setX -= STAR_WIDTH * 1.5;
                            setY -= STAR_HEIGHT;
                            break;
                        }
                        case 4 -> {
                            setY -= STAR_HEIGHT * 2;
                            break;
                        }
                        case 5 -> {
                            setX += STAR_WIDTH * 0.5;
                            setY -= STAR_HEIGHT;
                            break;
                        }
                    }
                    break;
                case 1:
                    switch (orientation) {
                        case 3 -> {
                            setX -= 1.5 * STAR_WIDTH;
                            setY -= STAR_HEIGHT;
                            break;
                        }
                        case 4 -> {
                            setY -= 2 * STAR_HEIGHT;
                            break;
                        }
                        case 5 -> {
                            setX += 0.5 * STAR_WIDTH;
                            setY -= STAR_HEIGHT;
                            break;
                        }
                    }
                    break;
                case 2:
                    switch (orientation) {
                        case 2 -> {
                            setX -= STAR_WIDTH;
                            //setY += STAR_HEIGHT;
                            break;
                        }
                        case 3 -> {
                            //setX += 0.5 * STAR_WIDTH;
                            setY -= STAR_HEIGHT;
                            break;
                        }
                        case 4 -> {
                            setX -= STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                            break;
                        }
                        case 5 -> {
                            setX += STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                            break;
                        }
                    }
                    break;

                case 3:
                    switch (orientation) {
                        case 3 -> {
                            setX -= 2 * STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                            break;
                        }
                        case 4 -> {
                            setX += 0.5 * STAR_WIDTH;
                            setY -= 3 * STAR_HEIGHT;
                            break;
                        }
                        case 5 -> {
                            setX += 0.5 * STAR_WIDTH;
                            setY -= STAR_HEIGHT;
                            break;
                        }
                    }
                    break;
                case 4:
                    switch (orientation) {
                        case 3 -> {
                            setX -= 1.5 * STAR_WIDTH;
                            setY -= STAR_HEIGHT;
                        }
                        case 4 -> {
                            setX -= STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                        }
                        case 5 -> {
                            setX += STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                        }
                    }
                    break;
                case 5:
                    switch (orientation) {
                        case 3 -> {
                            setX -= 2 * STAR_WIDTH;
                            //setY += STAR_HEIGHT;
                        }
                        case 4 -> {
                            setX -= STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                        }
                        case 5 -> {
                            setX += STAR_WIDTH;
                            setY -= 2 * STAR_HEIGHT;
                        }
                    }
                    break;

                case 6:
                    switch (orientation) {
                        case 2 -> {
                            setX -= 2 * STAR_WIDTH;
                        }
                    case 3 -> {
                        setX -=  STAR_WIDTH;
                        setY -= 2 * STAR_HEIGHT;
                    }
                    case 4 -> {
                        //setX += STAR_WIDTH;
                        setY -= 2 * STAR_HEIGHT;
                    }
                    case 5 -> {
                        setX += 0.5 * STAR_WIDTH;
                        setY -= STAR_HEIGHT;
                    }
            }
            break;

            }
        }




        @Override
        public String toString() {
            // update location of the piece
            setX = getLayoutX();
            setY = getLayoutY();
            System.out.println("setX; "+setX + " setY: "+setY);
            updateLocation();

            // the character identifying the color of the piece
            String c = colors[colorIndex].substring(0,1);

            // the third and fourth characters identifying the location of the piece
            int y = (int) ((setY-200)/75);
            int x;
            if (y % 2 ==0) {
                x = (int) ((setX-35)/85);
            }
            else {
                x = (int) ((setX-77.5)/85);
            }
            String location = x + Integer.toString(y);
            String pieceString = "";
            if (colorIndex == 0 || colorIndex == 5) {
                pieceString = c + (orientation%3) + location;
            }
            else{
                pieceString = c + orientation + location;}
            return pieceString;
        }
}

    /**
     * Display all the pieces
     */
    public void displayPiece() {
        for (int i = 0; i < 7; i++) {
            double homeX = 0;
            double homeY = 0;
            switch (i) {
                case 0:
                    homeX = MARGIN + STAR_WIDTH * 6;
                    homeY = MARGIN;
                    break;
                case 1:
                    homeX = MARGIN + STAR_WIDTH * 8;
                    homeY = MARGIN;
                    break;
                case 2:
                    homeX = MARGIN + BLANK_BOARD_WIDTH;
                    homeY = MARGIN + STAR_HEIGHT * 2;
                    break;
                case 3:
                    homeX = MARGIN + BLANK_BOARD_WIDTH;
                    homeY = MARGIN + STAR_HEIGHT * 4;
                    break;
                case 4:
                    homeX = MARGIN + STAR_WIDTH * 3;
                    homeY = MARGIN;
                    break;
                case 5:
                    homeX = MARGIN;
                    homeY = MARGIN;
                    break;
                case 6:
                    homeX = MARGIN + BLANK_BOARD_WIDTH;
                    homeY = MARGIN + STAR_HEIGHT * 6;
                    break;
            }

            ArrayList<Integer> placedColourIndex = new ArrayList<>();
            for(GUIPiece p:placedPiece){
                placedColourIndex.add(p.getColorIndex());
            }

            if(!(placedColourIndex.contains(i))){
                GUIPiece piece = new GUIPiece(i, homeX, homeY);
                root.getChildren().add(piece);
            }
        }
    }

    /**
     * Display the blank board
     */
    public void makeBoard() {
            Group board = new Group();
            board.getChildren().clear();
            // Display the blank board
            ImageView blankBoard = new ImageView();
            blankBoard.setImage(new Image("file:assets/blankBoard.png"));
            blankBoard.setFitWidth(BLANK_BOARD_WIDTH);
            blankBoard.setFitHeight(BLANK_BOARD_HEIGHT);
            blankBoard.setLayoutX(MARGIN);
            blankBoard.setLayoutY((BOARD_HEIGHT - BLANK_BOARD_HEIGHT) / 2);
            board.getChildren().add(blankBoard);
            blankBoard.toBack();
            root.getChildren().add(board);
        }
        // FIXME Task 9 (D): Implement challenges (you may use the set of challenges in the Games class)
        public void makeGameState(String gameStateString){
            root.getChildren().clear();
            placedPiece.clear();
            makeBoard();
            slider(root);

            String pieceString = gameStateString.substring(0, gameStateString.indexOf('W'));
            String wizardString = gameStateString.substring(gameStateString.indexOf('W') + 1);

            String[] colorsLetter = {"r","o","y","g","b","i","p"};
            List<String> colorList = new ArrayList<>(Arrays.asList(colorsLetter));
            double startX = 12.5 + 320 / 14;
            int startY = 180 + 320 / 14;

            for(int i = 0; i < pieceString.length(); i += 4){
                String piece = pieceString.substring(i, i + 4);
                int colorIndex = colorList.indexOf(piece.substring(0, 1));
                int orientation = Integer.parseInt(piece.substring(1, 2));
                int pieceX = Integer.parseInt(piece.substring(2,3));
                int pieceY = Integer.parseInt(piece.substring(3,4));
                GUIPiece guiPiece = new GUIPiece(colorIndex, 70, 500);
                guiPiece.setOrientation(orientation);
                root.getChildren().add(guiPiece);

                for(int o = 0; o < orientation; o++){
                    guiPiece.rotate();
                }

                // Translate the pieces to the board
                double boardX;
                if (pieceY % 2 == 0) {
                    boardX = startX + pieceX * STAR_WIDTH;
                }
                else {
                    boardX = startX + (pieceX+0.5) * STAR_WIDTH;
                }
                double boardY = startY + pieceY * STAR_HEIGHT;
                guiPiece.setLayoutX(boardX);
                guiPiece.setLayoutY(boardY);

                double setX = guiPiece.getLayoutX();
                double setY = guiPiece.getLayoutY();
                switch (colorIndex) {
                    case 0:
                        switch (orientation){
                            case 2 -> {
                                setX += STAR_WIDTH;
                            }
                        }
                        break;
                    case 1:
                        switch (orientation) {
                            case 3 -> {
                                setX += STAR_WIDTH * 1.5;
                                setY += STAR_HEIGHT;
                            }
                            case 4 -> setY += STAR_HEIGHT * 2;
                            case 5 -> {
                                setX -= STAR_WIDTH * 0.5;
                                setY += STAR_HEIGHT;
                            }
                        }
                        break;
                    case 2:
                        switch (orientation) {
                            case 2 -> {
                                setX += STAR_WIDTH;
                            }

                            case 3 -> {
                                setX += STAR_WIDTH / 2;
                                setY += STAR_HEIGHT;
                            }
                            case 4 -> {
                                setX += STAR_WIDTH;
                                setY += STAR_HEIGHT * 2;
                            }
                            case 5 -> {
                                setX -= STAR_WIDTH;
                                setY += STAR_HEIGHT * 2;
                            }
                        }
                        break;
                    case 3:
                        switch (orientation) {
                            case 3 -> {
                                setX += STAR_WIDTH * 2;
                                setY += STAR_HEIGHT * 2;
                            }
                            case 4 -> {
                                setX -= STAR_WIDTH / 2;
                                setY += STAR_HEIGHT * 3;
                            }
                            case 5 -> {
                                setX -= STAR_WIDTH / 2;
                                setY += STAR_HEIGHT;
                            }
                        }
                        break;
                    case 4:
                        switch (orientation) {
                            case 3 -> {
                                setX += STAR_WIDTH * 1.5;
                                setY += STAR_HEIGHT;
                            }
                            case 4 -> {
                                setX += STAR_WIDTH;
                                setY += STAR_HEIGHT * 2;
                            }
                            case 5 -> {
                                setX -= STAR_WIDTH;
                                setY += STAR_HEIGHT * 2;
                            }
                        }
                        break;
                    case 6:
                        switch (orientation) {
                            case 2 -> {
                                setX += STAR_WIDTH * 2;
                            }
                            case 3 -> {
                                setX += STAR_WIDTH;
                                setY += STAR_HEIGHT * 2;
                            }
                            case 4 -> setY += STAR_HEIGHT * 2;
                            case 5 -> {
                                setX -= STAR_WIDTH / 2;
                                setY += STAR_HEIGHT;
                            }
                        }
                        break;
                }
                guiPiece.setLayoutX(setX);
                guiPiece.setLayoutY(setY);

                double[] star = guiPiece.findNearestStar(guiPiece.getLayoutX(), guiPiece.getLayoutY());
                guiPiece.setLayoutX(star[0]);
                guiPiece.setLayoutY(star[1]);

                placedPiece.add(guiPiece);
            }

            displayPiece();

            // wizard string
            for(int j = 0; j < wizardString.length(); j += 3){
                String wizard = wizardString.substring(j, j + 3);
                int colorIndex = colorList.indexOf(wizard.substring(0, 1));
                int wizardX = Integer.parseInt(wizard.substring(1, 2));
                int wizardY = Integer.parseInt(wizard.substring(2, 3));

                ImageView image = new ImageView();
                String[] colors = {"red", "orange", "yellow", "green", "blue", "indigo", "pink"};
                image.setImage(new Image("file:assets/" + colors[colorIndex] + "Wizard.png"));
                image.setFitHeight(STAR_HEIGHT);
                image.setFitWidth(STAR_WIDTH);
                image.setOpacity(0.25);

                double boardX;
                if(wizardY % 2 == 0){
                    boardX = startX + wizardX * STAR_WIDTH;
                } else{
                    boardX = startX + (wizardX + 0.5) * STAR_WIDTH;
                }
                double boardY = startY + wizardY * STAR_HEIGHT;
                image.setLayoutX(boardX);
                image.setLayoutY(boardY);
                root.getChildren().add(image);
                image.toFront();
            }

            System.out.println("piece string: " + pieceString);
            System.out.println("wizard String: " + wizardString + "\n");
        }


        public void slider(Group group){
            Text text = new Text("Difficulty");
            TextField textField = new TextField();
            textField.setPrefWidth(50);
            Button button = new Button("Click me");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int difficulty;
                    String challenge = "";
                    try{
                        difficulty = Integer.parseInt(textField.getText());
                        challenge = Games.newGame(difficulty);
                    } catch(Exception a){
                        System.out.println("Difficulty not valid");
                    }

                    if(!(challenge.equals(""))){
                        makeGameState(challenge);
                    }

                    textField.clear();
                }
            });

            HBox hb = new HBox();
            hb.getChildren().addAll(text, textField, button);
            hb.setSpacing(10);
            hb.setLayoutX(50);
            hb.setLayoutY(125);

            group.getChildren().add(hb);
        }
        // FIXME Task 11 (HD): Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

        // FIXME Task 12 (HD): Generate interesting challenges (each challenge must have exactly one solution)

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("IQ Stars");
            Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

            makeBoard();
            displayPiece();
            primaryStage.setScene(scene);
            primaryStage.show();

            slider(root);
        }

    }

