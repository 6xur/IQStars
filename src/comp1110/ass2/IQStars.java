package comp1110.ass2;

import java.util.Set;

public class IQStars {

    /**
     * Determine whether a game string describing either a wizard or a piece
     * is well-formed according to the following criteria:
     * - It consists of exactly three or four characters
     * - If it consists of three characters, it is a well-formed wizard string:
     *      - the first character is a valid colour character (r,o,y,g,b,i,p)
     *      - the second character is a valid column number
     *          - (0 .. 6) if the row number is 0 or 2
     *          - (0 .. 5) otherwise
     *      - the third character is a valid row number (0 .. 3)
     * - If it consists of four characters, it is a well-formed piece string:
     *      - the first character is a valid colour character (r,o,y,g,b,i,p)
     *      - the second character is a valid rotation value
     *          - (0 .. 2) if the colour is r or i
     *          - (0 .. 5) otherwise
     *      - the third character is a valid column number
     *          - (0 .. 6) if the row number is 0 or 2
     *          - (0 .. 5) otherwise
     *      - the fourth character is a valid row number (0 .. 3)
     *      @param gameString A string describing either a piece or a wizard
     *      @return True if the string is well-formed
     */
    static boolean isGameStringWellFormed(String gameString) {
        try {
            char[] text = gameString.toCharArray();
            if (gameString.equals("") || gameString.length() > 4) return false;
            boolean A = (gameString.charAt(0) == 'r' || gameString.charAt(0) == 'o' || gameString.charAt(0) == 'y' || gameString.charAt(0) == 'g' || gameString.charAt(0) == 'b' || gameString.charAt(0) == 'i' || gameString.charAt(0) == 'p');

            // the case of Wizard
            if (gameString.length() == 3 && A && (Integer.parseInt(String.valueOf(text[2])) >= 0 && Integer.parseInt(String.valueOf(text[2])) <= 3)) {
                if ((Integer.parseInt(String.valueOf(text[2])) == 0 || Integer.parseInt(String.valueOf(text[2])) == 2) && Integer.parseInt(String.valueOf(text[1])) >= 0 && Integer.parseInt(String.valueOf(text[1])) <= 6)
                    return true;
                if ((Integer.parseInt(String.valueOf(text[2])) == 1 || Integer.parseInt(String.valueOf(text[2])) == 3) && Integer.parseInt(String.valueOf(text[1])) >= 0 && Integer.parseInt(String.valueOf(text[1])) <= 5)
                    return true;
            }

            // the case of piece
            if (gameString.length() == 4 && A && (Integer.parseInt(String.valueOf(text[3])) >= 0 && Integer.parseInt(String.valueOf(text[3])) <= 3)) {
                boolean B1 = (gameString.charAt(0) == 'r' || gameString.charAt(0) == 'i');
                boolean B2 = (Integer.parseInt(String.valueOf(text[3])) == 0 || Integer.parseInt(String.valueOf(text[3])) == 2);

                if (B1) {
                    if (B2) {
                        if (Integer.parseInt(String.valueOf(text[1])) >= 0 && Integer.parseInt(String.valueOf(text[1])) <= 2 && Integer.parseInt(String.valueOf(text[2])) >= 0 && Integer.parseInt(String.valueOf(text[2])) <= 6)
                            return true;
                    } else {
                        if (Integer.parseInt(String.valueOf(text[1])) >= 0 && Integer.parseInt(String.valueOf(text[1])) <= 2 && Integer.parseInt(String.valueOf(text[2])) >= 0 && Integer.parseInt(String.valueOf(text[2])) <= 5)
                            return true;
                    }
                } else {
                    if (B2) {
                        if (Integer.parseInt(String.valueOf(text[1])) >= 0 && Integer.parseInt(String.valueOf(text[1])) <= 5 && Integer.parseInt(String.valueOf(text[2])) >= 0 && Integer.parseInt(String.valueOf(text[2])) <= 6)
                            return true;
                    } else {
                        if (Integer.parseInt(String.valueOf(text[1])) >= 0 && Integer.parseInt(String.valueOf(text[1])) <= 5 && Integer.parseInt(String.valueOf(text[2])) >= 0 && Integer.parseInt(String.valueOf(text[2])) <= 5)
                            return true;
                    }
                }
            }

        } catch (NumberFormatException e) {
            return false;
        }
        return false; // FIXME Task 3 (P): determine whether a wizard or piece string is well-formed
    }


    /**
     * Determine whether a game state string is well-formed:
     * - The string is of the form [piecePlacement]W[wizardPlacement],
     *      where [piecePlacement] and [wizardPlacement] are replaced by the
     *      corresponding strings below
     * - [piecePlacement] string specification:
     *      - it consists of exactly n four-character piece strings (where n = 0 .. 7);
     *      - each piece string is well-formed
     *      - no piece appears more than once in the string
     *      - the pieces are ordered correctly within the string (r,o,y,g,b,i,p)
     * - [wizardPlacement] string specification:
     *      - it consists of exactly n three-character wizard strings (where n is some non-negative integer)
     *      - each wizard string is well-formed
     *      - the strings are ordered correctly within the string (r,o,y,g,b,i,p)
     *      - if there is more than one wizard of a single colour these wizards are ordered first by
     *          row and then by column in ascending order (note that this does not prevent two
     *          wizards of the same colour being placed in the same location - we will check for this
     *          in a later task).
     * @param gameStateString A string describing a game state
     * @return True if the game state string is well-formed
     */
    public static boolean isGameStateStringWellFormed(String gameStateString) {
        return false; // FIXME Task 4 (P): determine whether a game state string is well-formed
    }

    /**
     * Determine whether a game state is valid.
     *
     * To be valid, the game state must satisfy the following requirements:
     * - string must be well-formed
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     * - wizards must be on the board
     * - each location may have at most one wizard
     * - each piece must cover all wizards of the same colour
     * - each piece must not cover any wizards of a different colour
     *
     * @param gameStateString A game state string
     * @return True if the game state represented by the string is valid
     */
    public static boolean isGameStateValid(String gameStateString) {
        return false;  // FIXME Task 6 (D): determine whether a game state is valid
    }

    /**
     * Given a string describing a game state, and a location
     * that must be covered by the next move, return a set of all
     * possible viable piece strings which cover the location.
     *
     * For a piece string to be viable it must:
     *  - be a well formed piece string
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the given location
     *  - cover all wizards of the same colour
     *  - not cover any wizards of a different colour
     *
     * @param gameStateString A starting game state string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece strings, or null if there are none.
     */
    static Set<String> getViablePieceStrings(String gameStateString, int col, int row) {
        return null;  // FIXME Task 7 (P): determine the set of all viable piece strings given an existing game state
    }

    /**
     * Implement a solver for this game that can return the solution to a
     * particular challenge.
     *
     * This task is at the heart of the assignment and requires you to write
     * solver, similar to the boggle solver presented as part of the J14 lecture.
     *
     * NOTE: Simply looking up the provided answers does not constitute a general
     * solver.  Such an implementation is not a solution to this task, and
     * will not receive marks.
     *
     * @param challenge A game state string describing the starting game state.
     * @return A game state string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        return null;  // FIXME Task 10 (CR): determine the solution to the game, given a particular challenge
    }
}
