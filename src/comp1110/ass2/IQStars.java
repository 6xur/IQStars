package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
        int q = gameStateString.length();
        int qW = gameStateString.indexOf('W');
        String sW = gameStateString.substring(qW + 1, q);
        String sP = gameStateString.substring(0, qW);
        if (sW.length() % 3 != 0 || sP.length() > 28 || sP.length() % 4 != 0) return false;


        //Code for Piece:
        int[] pkc = new int[]{0, 0, 0, 0, 0, 0, 0}; //Piece kind count
        int[] order = new int[]{-1, -1, -1, -1, -1, -1, -1}; //Piece order check
        for (int i = 0; i < sP.length(); i = i + 4) {
            String sP1 = sP.substring(i, i + 4);
            if (!isGameStringWellFormed(sP1)) return false;

            //check duplicates -- a bit too lengthy
            if (sP1.charAt(0) == 'r') {
                if (pkc[0] == 1) return false;
                else {
                    pkc[0] = pkc[1] + 1;
                    order[0] = gameStateString.indexOf('r');
                }
            }
            if (sP1.charAt(0) == 'o') {
                if (pkc[1] == 1) return false;
                else {
                    pkc[1] = pkc[1] + 1;
                    order[1] = gameStateString.indexOf('o');
                }
            }
            if (sP1.charAt(0) == 'y') {
                if (pkc[2] == 1) return false;
                else {
                    pkc[2] = pkc[2] + 1;
                    order[2] = gameStateString.indexOf('y');
                }
            }
            if (sP1.charAt(0) == 'g') {
                if (pkc[3] == 1) return false;
                else {
                    pkc[3] = pkc[3] + 1;
                    order[3] = gameStateString.indexOf('g');
                }
            }
            if (sP1.charAt(0) == 'b') {
                if (pkc[4] == 1) return false;
                else {
                    pkc[4] = pkc[4] + 1;
                    order[4] = gameStateString.indexOf('b');
                }
            }
            if (sP1.charAt(0) == 'i') {
                if (pkc[5] == 1) return false;
                else {
                    pkc[5] = pkc[5] + 1;
                    order[5] = gameStateString.indexOf('i');
                }
            }
            if (sP1.charAt(0) == 'p') {
                if (pkc[6] == 1) return false;
                else {
                    pkc[6] = pkc[6] + 1;
                    order[6] = gameStateString.indexOf('p');
                }
            }
        }

        for (int i = 0; i < order.length; i++) {
            for (int j = i + 1; j < order.length; j++) {
                if (order[i] >= 0 && order[j] >= 0 && order[i] > order[j]) return false;
            }
        }

        //Code for Wizard:
        int[] pkcW = new int[]{0, 0, 0, 0, 0, 0, 0}; //Wizard kind count
        int[] orderW = new int[]{-1, -1, -1, -1, -1, -1, -1}; //Wizard order check
        char[] text = sW.toCharArray();
        for (int i = 0; i < sW.length(); i = i + 3) {
            String sW1 = sW.substring(i, i + 3);
            if (!isGameStringWellFormed(sW1)) return false;

            // check order
            if (sW1.charAt(0) == 'r') {
                if (pkcW[0] > 0) {
                    boolean a = (orderW[0] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[0] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[0] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[0] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[0]++;
                orderW[0] = i;
            }
            if (sW1.charAt(0) == 'o') {
                if (pkcW[1] > 0) {
                    boolean a = (orderW[1] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[1] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[1] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[1] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[1]++;
                orderW[1] = i;
            }
            if (sW1.charAt(0) == 'y') {
                if (pkcW[2] > 0) {
                    boolean a = (orderW[2] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[2] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[2] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[2] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[2]++;
                orderW[2] = i;
            }
            if (sW1.charAt(0) == 'g') {
                if (pkcW[3] > 0) {
                    boolean a = (orderW[3] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[3] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[3] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[3] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[3]++;
                orderW[3] = i;
            }
            if (sW1.charAt(0) == 'b') {
                if (pkcW[4] > 0) {
                    boolean a = (orderW[4] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[4] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[4] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[4] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[4]++;
                orderW[4] = i;
            }
            if (sW1.charAt(0) == 'i') {
                if (pkcW[5] > 0) {
                    boolean a = (orderW[5] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[5] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[5] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[5] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[5]++;
                orderW[5] = i;
            }
            if (sW1.charAt(0) == 'p') {
                if (pkcW[6] > 0) {
                    boolean a = (orderW[6] + 3 == i);
                    boolean b = (Integer.parseInt(String.valueOf(text[i + 2])) > Integer.parseInt(String.valueOf(text[orderW[6] + 2])));
                    boolean b1 = (Integer.parseInt(String.valueOf(text[i + 2])) == Integer.parseInt(String.valueOf(text[orderW[6] + 2])));
                    boolean c = (Integer.parseInt(String.valueOf(text[i + 1])) >= Integer.parseInt(String.valueOf(text[orderW[6] + 1])));
                    if (!(a && b || a && b1 && c)) return false;
                }
                pkcW[6]++;
                orderW[6] = i;
            }
        }

        for (int i = 0; i < orderW.length; i++) {
            for (int j = i + 1; j < orderW.length; j++) {
                if (orderW[i] >= 0 && orderW[j] >= 0 && orderW[i] > orderW[j]) return false;
            }
        }


        return true; // FIXME Task 4 (P): determine whether a game state string is well-formed
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
        if (!(isGameStateStringWellFormed(gameStateString))) {
            return false;
        }

        String[] strings = gameStateString.split("W");
        String piecePlacement = "";
        if (strings.length > 0) {
            piecePlacement = strings[0];
        }

        ArrayList<Location> seenPieces = new ArrayList<>();
        for (int i = 0; i < piecePlacement.length(); i += 4) {
            String pieceString = piecePlacement.substring(i, i + 4);
            ArrayList<Character> colorChars = new ArrayList<>(Arrays.asList('r', 'o', 'y', 'g', 'b', 'i', 'p'));
            ArrayList<State> colorStates = new ArrayList<>(Arrays.asList(State.RED, State.ORANGE, State.YELLOW, State.GREEN, State.BLUE, State.INDIGO, State.PINK));
            State color = colorStates.get(colorChars.indexOf(pieceString.charAt(0)));
            int orientation = pieceString.charAt(1) - '0';
            int column = pieceString.charAt(2) - '0';
            int row = pieceString.charAt(3) - '0';

            Piece piece = new Piece(color, orientation);
            Location pieceLoc = new Location(column, row);
            piece.setPiece(pieceLoc);

            // checks if each piece is entirely on the board
            if (!(piece.onBoard())) {
                return false;
            }

            // concatenates the star locations of the current piece to the arraylist of seen locations
            seenPieces.addAll(new ArrayList<>(Arrays.asList(piece.getPieceStars())));
        }

        // checks for piece overlap
        for (int i = 0; i < seenPieces.size(); i++) {
            for (int j = i + 1; j < seenPieces.size(); j++) {
                if ((seenPieces.get(i).equals(seenPieces.get(j)))) {
                    return false;
                }
            }
        }


        // Code for wizard
        String wizardPlacement = "";
        if (strings.length == 2) {
            wizardPlacement = strings[1];
        }

        ArrayList<Location> seenWizards = new ArrayList<>();
        for (int i = 0; i < wizardPlacement.length(); i += 3) {
            String wizardString = wizardPlacement.substring(i, i + 3);
            int wizardColumn = wizardString.charAt(1) - '0';
            int wizardRow = wizardString.charAt(2) - '0';
            Location wizardLoc = new Location(wizardColumn, wizardRow);

            if (wizardLoc.offBoard()) {
                return false;
            }

            // returns false if the wizard color doesn't exist in the piece placement
            if (piecePlacement.length() > 0 && piecePlacement.indexOf(wizardString.charAt(0)) == -1) {
                return false;
            }

            seenWizards.add(wizardLoc);

            for (int j = 0; j < piecePlacement.length(); j += 4) {
                String pieceString = piecePlacement.substring(j, j + 4);
                Location[] pieceStars;

                // checks if wizard-covering piece has the same color as the wizard
                if (wizardString.charAt(0) == pieceString.charAt(0)) {
                    ArrayList<Character> colorChars = new ArrayList<>(Arrays.asList('r', 'o', 'y', 'g', 'b', 'i', 'p'));
                    ArrayList<State> colorStates = new ArrayList<>(Arrays.asList(State.RED, State.ORANGE, State.YELLOW, State.GREEN, State.BLUE, State.INDIGO, State.PINK));
                    State pieceColor = colorStates.get(colorChars.indexOf(pieceString.charAt(0)));
                    int orientationLabel = pieceString.charAt(1) - '0';
                    int pieceColumn = pieceString.charAt(2) - '0';
                    int pieceRow = pieceString.charAt(3) - '0';
                    Location pieceLoc = new Location(pieceColumn, pieceRow);
                    Piece matchingPiece = new Piece(pieceColor, orientationLabel);
                    matchingPiece.setPiece(pieceLoc);
                    pieceStars = matchingPiece.getPieceStars();

                    boolean found = false;
                    for (Location starLoc : pieceStars) {
                        if (starLoc.equals(wizardLoc)) {
                            found = true;
                        }
                    }
                    if (!found) {
                        return false;
                    }
                }
            }
        }

        // check for wizard overlap
        for (int i = 0; i < seenWizards.size(); i++) {
            for (int j = i + 1; j < seenWizards.size(); j++) {
                if ((seenWizards.get(i).equals(seenWizards.get(j)))) {
                    return false;
                }
            }
        }

        // checks for uncovered wizards
        int coveredCount = 0;
        if (seenPieces.size() > 0 && seenWizards.size() > 0) {
            for (Location wLoc : seenWizards) {
                for (Location pLoc : seenPieces) {
                    if (pLoc.equals(wLoc)) {
                        coveredCount++;
                    }
                }
            }

            if (coveredCount != seenWizards.size()) {
                return false;
            }

        }
        return true;
    }

    static ArrayList<Piece> getPlacedPieces(String gameStateString){
        String[] strings = gameStateString.split("W");
        String piecePlacement = "";
        if (strings.length > 0) {
            piecePlacement = strings[0];
        }

        ArrayList<Character> colorChars = new ArrayList<>(Arrays.asList('r', 'o', 'y', 'g', 'b', 'i', 'p'));
        ArrayList<State> colorStates = new ArrayList<>(Arrays.asList(State.RED, State.ORANGE, State.YELLOW, State.GREEN, State.BLUE, State.INDIGO, State.PINK));
        ArrayList<Piece> placedPieces = new ArrayList<>();
        for (int i = 0; i < piecePlacement.length(); i += 4) {
            String pieceString = piecePlacement.substring(i, i + 4);
            State color = colorStates.get(colorChars.indexOf(pieceString.charAt(0)));
            Piece piece = new Piece(color, pieceString.charAt(1) - '0');
            piece.setPiece(new Location(pieceString.charAt(2) - '0', pieceString.charAt(3) - '0'));
            placedPieces.add(piece);
        }
        return placedPieces;
    }

    static ArrayList<String> getPlacedWizards(String gameStateString){
        String[] strings = gameStateString.split("W");
        String wizardPlacement = "";
        if (strings.length == 2) {
            wizardPlacement = strings[1];
        }

        ArrayList<String> placedWizards = new ArrayList<>();
        for (int i = 0; i < wizardPlacement.length(); i += 3) {
            String wizardString = wizardPlacement.substring(i, i + 3);
            placedWizards.add(wizardString);
        }
        return placedWizards;
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
        ArrayList<Piece> placedPieces = getPlacedPieces(gameStateString);
        ArrayList<String> placedWizards = getPlacedWizards(gameStateString);
        Set<String> viablePieces = new HashSet<>();

        // Generates all possible pieces
        for(int colorIndex = 0; colorIndex < 7; colorIndex++){
            for(int currOrient = 0; currOrient < 6; currOrient++){
                for(int currCol = 0; currCol < 7; currCol++){
                    outerloop:
                    for(int currRow = 0; currRow < 4; currRow++){
                        ArrayList<Character> colorChars = new ArrayList<>(Arrays.asList('r', 'o', 'y', 'g', 'b', 'i', 'p'));
                        ArrayList<State> colorStates = new ArrayList<>(Arrays.asList(State.RED, State.ORANGE, State.YELLOW, State.GREEN, State.BLUE, State.INDIGO, State.PINK));
                        State color = colorStates.get(colorIndex);
                        Piece piece = new Piece(color, currOrient);
                        piece.setPiece(new Location(currCol, currRow));
                        System.out.println(currOrient + "" + currCol + "" + currRow);
                        String pieceString = Character.toString(colorChars.get(colorIndex)) + currOrient + "" + currCol + "" + currRow;

                        if(!(isGameStringWellFormed(pieceString))){
                            continue;
                        }
                        if(!(piece.onBoard())){
                            continue;
                        }
                        if(!(piece.overlaps(new Location(col, row)))){
                            continue;
                        }

                        for(Piece placedPiece : placedPieces){
                            if(piece.overlaps(placedPiece)){
                                continue outerloop;
                            }
                            if(piece.getColour().equals(placedPiece.getColour())){
                                continue outerloop;
                            }
                        }

                        for(String wizard : placedWizards){
                            Location wizardLoc = new Location(wizard.charAt(1) - '0', wizard.charAt(2) - '0');
                            // checks if the current piece covers all wizards of the same color
                            if(pieceString.charAt(0) == wizard.charAt(0)){
                                if(!(piece.overlaps(wizardLoc))){
                                    continue outerloop;
                                }
                            }
                            // checks if the current piece incorrectly covers any wizard of a different color
                            if(pieceString.charAt(0) != wizard.charAt(0)){
                                if(piece.overlaps(wizardLoc)){
                                    continue outerloop;
                                }
                            }
                        }
                        viablePieces.add(pieceString);
                    }
                }
            }
        }

        if(viablePieces.size() == 0){
            return null;
        }

        return viablePieces;  // FIXME Task 7 (P): determine the set of all viable piece strings given an existing game state
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
