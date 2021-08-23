package comp1110.ass2;

public class Piece {

    
    /** The location of star in the top left corner */
    private Location firstStar = new Location();

    /** Each piece is defined by the location of the top-left star of the piece,
     * the colour of the piece and the orientation number of the piece.
     * @param orientationLabel an integer ranged from 0 to 5 which describes the orientation of the piece
     */
    public Piece(State colour, int orientationLabel) {
    }

    /**
     * An array of length three or four storing the locations of all stars in
     * the piece, ordered from the left-top to the right-bottom of the piece
     * (row by row)
     */
    private Location[] pieceStars;

    public Location getLoc() {return null;}
    public Location[] getPieceStars() {return null;}

    /**
     * set the location of a piece by setting the location of its top-left star
     */
    public void setPiece(Location newPiece) {}

    /**
     * set the locations of all the stars of a piece
     */
    public void setPieceStars(Location newPieceStar) {}

    /**
     * @return True if all the stars of the current piece are placed on board locations,
     * and False otherwise
     */
    public boolean onBoard() { return true;}

    /**
     * update the location of the current piece
     */
    public void placePiece() {}

    /**
     * remove the current piece from the board
     */
    public void removePiece() {}

}
