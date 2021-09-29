package comp1110.ass2;

import java.util.*;

import static comp1110.ass2.State.*;

public class Piece {

    private State colour;
    private int orientationLabel;

    /**
     * The location of star in the top left corner
     */
    private Location firstStar = new Location();

    /**
     * Each piece is defined by the location of the top-left star of the piece,
     * the colour of the piece and the orientation number of the piece.
     *
     * @param orientationLabel an integer ranged from 0 to 5 which describes the orientation of the piece
     */
    public Piece(State colour, int orientationLabel) {
        this.colour = colour;
        this.orientationLabel = orientationLabel;
    }


    public Piece(String pieceString) {
        State[] colorInOrder = {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, PINK};
        ArrayList<String> colorString = new ArrayList<>();
        colorString.addAll(Arrays.asList("r", "o","y","g","b","i","p"));
        this.colour = colorInOrder[colorString.indexOf(pieceString.substring(0,1))];
        this.orientationLabel = Integer.parseInt(pieceString.substring(1,2));
        this.firstStar = new Location(pieceString.substring(2,4));
    }


    /**
     * An array of length three or four storing the locations of all stars in
     * the piece, ordered from the left-top to the right-bottom of the piece
     * (row by row)
     */
    private ArrayList<Location> pieceStars = new ArrayList<>();

    public State getColour() {
        return this.colour;
    }

    public Location getPiece() {
        return this.firstStar;
    }

    public Location[] getPieceStars() {
        return (this.pieceStars.toArray(new Location[0]));
    }

    public void setColour(State colour){
        this.colour = colour;
    }

    public void setOrientationLabel(int orientationLabel){
        this.orientationLabel = orientationLabel;
    }

    /**
     * Returns whether the current piece overlaps with another piece
     */
    public boolean overlaps(Piece other){
        if(other == null){
            return false;
        }
        for(Location star : this.getPieceStars()){
            for(Location otherStar : other.getPieceStars()){
                if(star.equals(otherStar)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns whether the current piece overlaps with another location
     */
    public boolean overlaps(Location location){
        for(Location star : this.getPieceStars()){
            if(star.equals(location)){
                return true;
            }
        }
        return false;
    }

    /**
     * set the location of a piece by setting the location of its top-left star
     */
    public void setPiece(Location newPiece) {
        pieceStars.clear();
        this.firstStar = newPiece;
        int column = newPiece.getX();
        int row = newPiece.getY();

        pieceStars.add(new Location(column, row));
        if (this.colour == State.RED) {
            switch (this.orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 1), (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 1:
                    pieceStars.add(new Location(column, (row + 1)));
                    pieceStars.add(new Location(column, (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 2:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location(column, (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    }
                    break;
            }
        }

        if (this.colour == State.ORANGE) {
            switch (this.orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 1:
                    pieceStars.add(new Location(column, (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 2:
                    pieceStars.add(new Location((column - 1), (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 2), (row + 1)));
                    }
                    break;
                case 3:
                    pieceStars.add(new Location((column + 1), (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 4:
                    pieceStars.add(new Location(column, (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 5:
                    pieceStars.add(new Location((column + 1), row));
                    if (row == 1) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    }
                    break;
            }
        }

        if (this.colour == YELLOW) {
            switch (this.orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 2), row));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 1:
                    pieceStars.add(new Location(column, (row + 1)));
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 2:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location(column, (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));

                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 3:
                    pieceStars.add(new Location((column - 1), (row + 1)));
                    pieceStars.add(new Location(column, (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 2), (row + 1)));
                    }
                    break;
                case 4:
                    pieceStars.add(new Location((column + 1), (row + 1)));
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    }
                    break;
                case 5:
                    pieceStars.add(new Location((column - 1), (row + 2)));
                    pieceStars.add(new Location(column, (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
            }
        }

        if (this.colour == GREEN) {
            switch (this.orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 2), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    }
                    break;
                case 1:
                    pieceStars.add(new Location(column, (row + 1)));
                    pieceStars.add(new Location(column, (row + 2)));
                    pieceStars.add(new Location((column - 1), (row + 3)));
                    break;
                case 2:
                    pieceStars.add(new Location((column - 1), (row + 1)));
                    pieceStars.add(new Location((column - 2), (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 3), (row + 1)));
                    }
                    break;
                case 3:
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    pieceStars.add(new Location((column + 2), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 4:
                    pieceStars.add(new Location((column - 1), (row + 1)));
                    pieceStars.add(new Location((column - 1), (row + 2)));
                    pieceStars.add(new Location((column - 1), (row + 3)));
                    break;
                case 5:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 2), row));
                    if (row == 1) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    }
                    break;
            }
        }

        if (this.colour == BLUE) {
            switch (this.orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 2), row));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 1:
                    pieceStars.add(new Location(column, (row + 2)));
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 2:
                    pieceStars.add(new Location((column - 1), (row + 1)));
                    pieceStars.add(new Location((column - 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 3:
                    pieceStars.add(new Location(column, (row + 1)));
                    pieceStars.add(new Location((column + 1), (row + 1)));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    }
                    break;
                case 4:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 5:
                    pieceStars.add(new Location(column, (row + 1)));
                    pieceStars.add(new Location((column - 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
            }
        }

        if (this.colour == State.INDIGO) {
            switch (orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 2), row));
                    break;
                case 1:
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 2:
                    pieceStars.add(new Location((column - 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
            }
        }

        if (this.colour == State.PINK) {
            switch (orientationLabel) {
                case 0:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    }
                    break;
                case 1:
                    pieceStars.add(new Location(column, (row + 2)));
                    pieceStars.add(new Location((column - 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location(column, (row + 1)));
                    } else {
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
                case 2:
                    pieceStars.add(new Location((column + 1), (row + 1)));
                    pieceStars.add(new Location((column + 2), row));
                    if (row == 1) {
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 3:
                    pieceStars.add(new Location(column, (row + 2)));
                    pieceStars.add(new Location((column + 1), (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 4:
                    pieceStars.add(new Location((column + 1), row));
                    pieceStars.add(new Location(column, (row + 2)));
                    if (row == 0) {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                    } else {
                        pieceStars.add(new Location(column, (row + 1)));
                    }
                    break;
                case 5:
                    pieceStars.add(new Location((column + 1), row));
                    if (row == 1) {
                        pieceStars.add(new Location(column, (row + 1)));
                        pieceStars.add(new Location((column + 2), (row + 1)));
                    } else {
                        pieceStars.add(new Location((column - 1), (row + 1)));
                        pieceStars.add(new Location((column + 1), (row + 1)));
                    }
                    break;
            }
        }
    }


    /**
     * @return True if all the stars of the current piece are placed on board locations,
     * and False otherwise
     */
    public boolean onBoard() {
        for (Location star : this.pieceStars) {
            if (star.offBoard()) {
                return false;
            }
        }
        return true;
    }

    /**
     * update the location of the current piece
     */
    public void placePiece() {
    }

    /**
     * remove the current piece from the board
     */
    public void removePiece() {
    }


    /**
     * Add a new piece to the current board state and adjust the order of piece strings.
     * @param newPieceString the string of the piece to be added (eg. "r012")
     * @param currentStateString the string of the current state
     * @return the ordered string of the state after the new piece is added; return the message "invalid input" if the new
     * piece string is not well-formed or the current state string is not well-formed; return "WW" if the new piece cannot be added to the current state
     */
    public static String placePiece (String newPieceString, String currentStateString) {
        if (newPieceString.length()!= 4 || !IQStars.isGameStringWellFormed(newPieceString) || !IQStars.isGameStateStringWellFormed(currentStateString)) {return "invalid input";}
        String[] strings = currentStateString.split("W");
        String placedPieceString = strings[0];

        // store all the pieces in the current state and map their color to each piece
        ArrayList<Piece> placedPieces= IQStars.getPlacedPieces(placedPieceString);
        Map<State, Piece> allPieces = new HashMap<>();
        for (Piece p : placedPieces) {
            State color = p.getColour();
            allPieces.put(color,p);
        }

        // create the new piece using its string and add the new piece to the board
        State[] colorInOrder = {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, PINK};
        Piece newPiece = new Piece(newPieceString);
        allPieces.put(newPiece.colour, newPiece);

        // reorder the pieces in the state string
        String updatedStateString = "";
        for (State c : colorInOrder) {
            if (allPieces.containsKey(c)) {
                updatedStateString += allPieces.get(c).toString();}
        }
        updatedStateString += "W";

        // return "WW" if the piece cannot be added to the current state
        if (!IQStars.isGameStateValid(updatedStateString)) {return "WW";}

        if (strings.length == 2) {
            updatedStateString += strings[1];
        }
        return updatedStateString;
    }

    static String finalSolution = new String();

    /**
     * Recursive method to find the solution given a challenge
     *
     * @param placedPieceString the set of placed piece strings on board
     * @param challenge the string of current challenge state (i.e. the current state string)
     * @param candidates a set of strings containing strings of piece candidates
     */
    public static void getSubPart(Set<String> placedPieceString, String challenge, Set<String> candidates) {

        // if the challenge string is valid and the piece placement includes exactly 7 pieces, we find the solution and
        // store it in the field finalSolution.
        String[] strings = challenge.split("W");
        if (strings[0].length() == 28 && IQStars.isGameStateValid(challenge)) {
            finalSolution = challenge;
        }

        for (String s : candidates) {
            if (!placedPieceString.contains(s)) {
                String addPiece = Piece.placePiece(s, challenge);
                if (IQStars.isGameStateValid(addPiece)) {
                    String currentChallenge = challenge;
                    challenge = addPiece;
                    placedPieceString.add(s);
                    getSubPart(placedPieceString, challenge, candidates);
                    challenge = currentChallenge;
                    placedPieceString.remove(s);
                }
            }
        }
    }

    @Override
    public String toString(){
        String firstColorLetter = this.colour.toString().substring(0,1).toLowerCase();
        return firstColorLetter + this.orientationLabel + this.firstStar.toString();
    }
}

