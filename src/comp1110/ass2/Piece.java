package comp1110.ass2;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import gittest.A;

import java.util.ArrayList;

public class Piece {
    public static void main(String[] args) {
        System.out.println("lol");
    }
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

    public boolean overlaps(Piece other){
        for(Location star : this.getPieceStars()){
            for(Location otherStar : other.getPieceStars()){
                if(star.equals(otherStar)){
                    return true;
                }
            }
        }
        return false;
    }

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

        if (this.colour == State.YELLOW) {
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

        if (this.colour == State.GREEN) {
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

        if (this.colour == State.BLUE) {
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

    public String toString(){
        return this.colour.toString() + this.orientationLabel + this.firstStar.toString();
    }
}
