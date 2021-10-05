package comp1110.ass2;

import java.util.*;



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

        if (gameString.length() > 4 || gameString.length() < 3)  return false;

        char color      = gameString.charAt(0);
        char rot        = gameString.charAt(gameString.length()-3);
        int rotint      = Character.getNumericValue(rot);
        char col        = gameString.charAt(gameString.length()-2);
        int colint      = Character.getNumericValue(col);
        char row        = gameString.charAt(gameString.length()-1);
        int rowint      = Character.getNumericValue(row);

        boolean C1 = ("roygbip".contains(String.valueOf(color)) &&  "0123".contains(String.valueOf(row)));
        boolean C2 = ((( rowint == 0 || rowint == 2 ) &  colint >= 0 && colint <= 6)  || (( rowint == 1 || rowint == 3 ) &  colint >= 0 && colint <= 5));
        boolean C3 = (("ri".contains(String.valueOf(color)) && rotint >= 0 && rotint <=2) || ( "oygbp".contains(String.valueOf(color)) && rotint >= 0 && rotint <=5));

        return ((gameString.length() == 3 && C1 && C2) || (gameString.length() == 4  && C1 && C2 &&  C3) );
        // FIXME Task 3 (P): determine whether a wizard or piece string is well-formed
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

        String sW = gameStateString.substring(gameStateString.indexOf('W') + 1);
        String sP = gameStateString.substring(0, gameStateString.indexOf('W'));
        long countW = gameStateString.chars().filter(ch -> ch == 'W').count();

        if (sW.length() % 3 != 0 || sP.length() > 28 || sP.length() % 4 != 0 || countW != 1) return false;

        for (int i = 0; i < sP.length(); i = i + 4) {
            String sP1 = sP.substring(i, i + 4);
            if (!isGameStringWellFormed(sP1)) return false;
        }

        for (int i = 0; i < sW.length(); i = i + 3) {
            String sW1 = sW.substring(i, i + 3);
            if (!isGameStringWellFormed(sW1)) return false;
        }

        //check duplicates in piece
        char[] list = "roygbip".toCharArray();
        for (char c : list) {
            if (sP.chars().filter(ch -> ch == c).count() > 1 )  return false;
        }

        //check order:
        String sPcolor = sP.replaceAll("[0123456789]|[W]", "");
        sPcolor = sPcolor.replace('r', '1')
                .replace('o', '2')
                .replace('y', '3')
                .replace('g', '4')
                .replace('b', '5')
                .replace('i', '6')
                .replace('p', '7');
        char[] sPArr = sPcolor.toCharArray();
        for (int i = 0; i < sPArr.length; i++) {
            for (int j = i + 1; j < sPArr.length; j++) {
                if (!(sPArr[i] < sPArr[j])) return false;
            }
        }

        if (sW.length() > 0) {
            String sWcolor = sW.replaceAll("[0123456789]|[W]", "");
            sWcolor = sWcolor.replace('r', '1')
                    .replace('o', '2')
                    .replace('y', '3')
                    .replace('g', '4')
                    .replace('b', '5')
                    .replace('i', '6')
                    .replace('p', '7');

            String sW1 = sW.replaceAll("[W]", "");
            char[] sWarrL = sW1.toCharArray();
            char[] sWarrS = sWcolor.toCharArray();

            for (int i = 0; i < sWarrS.length; i++) {
                for (int j = i + 1; j < sWarrS.length; j++) {

                    if (sWarrS[i] > sWarrS[j]) return false;
                    if ((sWarrS[i] ==   sWarrS[j]) && (sWarrL[j*3 + 2] < sWarrL[i*3 + 2])) return false;
                    if ((sWarrS[i] ==   sWarrS[j]) && ((sWarrL[j*3 + 2] == sWarrL[i*3 + 2]) && (sWarrL[j*3 + 1] <  sWarrL[i*3 + 1]) )) return false;
                }
            }
        }
        return true; // FIXME Task 4 (P): determine whether a game state string is well-formed
    }


    /**
     *
     * @param gameStateString A game state string
     * @return An arraylist of placed pieces
     */
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


    /**
     *
     * @param gameStateString A game state string
     * @return An arraylist of placed wizards
     */
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

        ArrayList<Piece> placedPieces = getPlacedPieces(gameStateString);
        for (Piece placedPiece : placedPieces) {
            if (!(placedPiece.onBoard())) {
                return false;
            }
        }

        // checks for piece overlap
        for (int i = 0; i < placedPieces.size(); i++) {
            for (int j = i + 1; j < placedPieces.size(); j++) {
                if ((placedPieces.get(i).overlaps(placedPieces.get(j)))) {
                    return false;
                }
            }
        }


        // Code for wizard
        String wizardPlacement = "";
        if (strings.length == 2) {
            wizardPlacement = strings[1];
        }

        ArrayList<Location> placedWizards = new ArrayList<>();
        for (int i = 0; i < wizardPlacement.length(); i += 3) {
            String wizardString = wizardPlacement.substring(i, i + 3);
            Location wizardLoc = new Location(wizardString.charAt(1) - '0', wizardString.charAt(2) - '0');

            if (wizardLoc.offBoard()) {
                return false;
            }

            // returns false if the wizard color doesn't exist in the piece placement
            if (piecePlacement.length() > 0 && piecePlacement.indexOf(wizardString.charAt(0)) == -1) {
                return false;
            }

            // checks if wizard-covering piece has the same color as the wizard
            for (Piece placedPiece : placedPieces) {
                ArrayList<Character> colorChars = new ArrayList<>(Arrays.asList('r', 'o', 'y', 'g', 'b', 'i', 'p'));
                ArrayList<State> colorStates = new ArrayList<>(Arrays.asList(State.RED, State.ORANGE, State.YELLOW, State.GREEN, State.BLUE, State.INDIGO, State.PINK));
                if (colorStates.indexOf(placedPiece.getColour()) == colorChars.indexOf(wizardString.charAt(0))) {
                    if (!(placedPiece.overlaps(wizardLoc))) {
                        return false;
                    }
                }
            }
            placedWizards.add(wizardLoc);
        }

        // checks for wizard overlap
        for (int i = 0; i < placedWizards.size(); i++) {
            for (int j = i + 1; j < placedWizards.size(); j++) {
                if ((placedWizards.get(i).equals(placedWizards.get(j)))) {
                    return false;
                }
            }
        }

        // checks for uncovered wizards
        int coveredWizards = 0;
        if (placedPieces.size() > 0 && placedWizards.size() > 0) {
            for (Location wizardLoc : placedWizards) {
                for (Piece placedPiece : placedPieces) {
                    if (placedPiece.overlaps(wizardLoc)) {
                        coveredWizards++;
                    }
                }
            }
            return coveredWizards == placedWizards.size();
        }
        return true;
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
                        Piece piece = new Piece(colorStates.get(colorIndex), currOrient);
                        piece.setPiece(new Location(currCol, currRow));
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
       ArrayList<Piece> placedPiece = getPlacedPieces(challenge);
       Set<String> placedPieceString = new HashSet<>();
       Set<State> placedColor = new HashSet<>();
       Set<String> candidates = new HashSet<>();

       // find all the pieces already on board
       for (Piece p : placedPiece) {
           String s = p.toString();
           State c = p.getColour();
           placedPieceString.add(s);
           placedColor.add(c);
       }

       // find all the potential pieces to be placed
        for (int i = 0; i < 26; i ++) {
            Location location = new Location(i);
            Set<String> viableStrings = getViablePieceStrings(challenge, location.getX(), location.getY());
            if (viableStrings != null) {
                candidates.addAll(viableStrings);
            }
        }

        for (String s : candidates) {
            Piece p = new Piece(s);
            // if there is a piece of the same color on board already, we will not consider the candidate
            if (!placedColor.contains(p.getColour())) {
                String currentChallenge = challenge;
                String addPiece = Piece.placePiece(s, challenge);
                if (isGameStateValid(addPiece)) {
                    challenge = addPiece;
                    placedPieceString.add(s);
                    Piece.getSubPart(placedPieceString, challenge, candidates);
                    challenge = currentChallenge;
                    placedPieceString.remove(s);
                }
            }
        }

        return Piece.finalSolution;  // FIXME Task 10 (CR): determine the solution to the game, given a particular challenge
    }

}
