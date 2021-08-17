package comp1110.ass2;

public class Piece {

    private Location piece = new Location();

    // if both the color and the upper most star location are stored in
    // class Location, the orientationLabel is used to determine the
    // orientation of the piece
    public Piece(int orientationLabel) { }

    /**
     * An array of length three or four storing the locations of all stars in
     * the piece, ordered from the left-top to the right-bottom of the piece
     * (row by row)
     */
    private Location[] pieceStars;

    public Location getRedLoc()  { return null; }
    public Location getOrangeLoc() { return null; }
    public Location getYellowLoc()  { return null; }
    public Location getGreenLoc()  { return null; }
    public Location getBlueLoc() { return null; }
    public Location getIndigoLoc() { return null; }
    public Location getPinkLoc() { return null; }

    public Location[] getPieceStars() {return null;}

    public void setPiece(Location newPiece) {}

    public void setPieceStars(Location newPieceStar) {}

    /**
     * @return True if all stars of the current piece is placed on board locations,
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
