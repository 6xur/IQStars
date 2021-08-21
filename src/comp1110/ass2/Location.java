package comp1110.ass2;

public class Location {
    private int x;
    private int y;
    static final int OUT = -1;

    /**
     * Given an x and y coordinate, construct the corresponding Location.
     *
     * Note that we can access the fields of the current location using the
     * `this' keyword.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct an off board Location
     */
    public Location() {
        this.x = OUT;
        this.y = OUT;
    }

    /**
     * Given an integer from 0-25, construct the corresponding Location
     * defined as follows:
     *
     *             x
     *             0        1       2       3       4       5       6
     *         0   0        1       2       3       4       5       6
     *       y 1        7       8       9       10      11      12
     *         2   13       14      15      16      17      18      19
     *         3        20      21      22      23      24      25
     *
     * Take a look at the previous constructors for Locations to find how to set
     * the relevant fields (x and y coordinates) of this Location.
     *
     * @param position an integer from 0-25
     */

    public Location(int position) {
        // FIXME Task Location(a)
    }

    /**
     * Given a two-character string representing a location on the board,
     * construct the corresponding Location. Recall that the two characters
     * represent the x and y coordinates of the location, respectively, so
     * the string "03" corresponds to the location (0,3).
     *
     * @param loc a String representing a Location on the board.
     *
     */

    public Location(String loc) {
        assert (loc.length()==2);
        // FIXME Task Location(b)
    }

    /**
     * @return The x coordinate of this Location
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of this Location
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set the x coordinate of this Location
     * @param x The x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y coordinate of this Location
     * @param y The y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Return the position corresponding to this instance of Location on the
     * board (see the README.md for mapping specifications).
     * Hoa_Note: README.md to be written.
     *
     * @return The position corresponding to this Location
     *
     */
    public int toPosition() {
       int y = this.y;
        if (y == 0 | y == 1) {
            return y * 7 + this.x;
        } else {
            return y * 7 + this.x + (y - 1);
        }
    }

    /**
     * Returns whether or not this location is on the IQStars board or not
     * That is, whether or not y coordinate is between 0-3;
     * for y = 0 or y =2, x coordinate is between 0-6;
     * for y = 1 or y = 3, x coordinate is between 0-5;
     *
     * Remember that you can access the x and y coordinates of this
     * instance of Location using the this keyword
     * (eg check out the getting and setter methods above).
     *
     * @return True if this location is off the board, False otherwise
     */

    public boolean offBoard() {

        return true; // FIXME Task Location(c)
    }

    /**
     * Returns whether or not this location occupies the same location
     * as a given other location. For this to be true, the x and the y values
     * of this object would have to be the same as the x and y values of
     * the other object.
     *
     * @param other The location to compare to.
     * @retun True if this location occupies the same location as the other
     * location, False otherwise.
     */
    public boolean equals(Location other) {
        return false; // FIXME Task Location(d)
    }

    /**
     * Return the Manhattan distance between this location and a given other
     * locations.
     *
     * @param other The location to calculate from.
     * @return the Manhattan distance between this and other
     */

    public int manhanttanDistance(Location other) {
        return  Math.abs(this.x - other.getX() ) + Math.abs(this.y - other.getY() );
    }

    /**
     * Returns whether or not the current instance of location is adjacent to a
     * given other location.
     *
     * Note that adjacent implies either directly above, below or to the left or
     * right of this location, not diagonally.
     *
     * @param other The location to compare with
     * @return True if this location is adjacent to other, False otherwise
     */

    public boolean isAdjacent(Location other) {
        return false; // FIXME Task Location(e)
    }

    /**
     * @return The string encoding of this Location.
     */
    @Override
    public String toString() {
        return this.x + "" + this.y;
    }


}
