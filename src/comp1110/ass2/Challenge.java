package comp1110.ass2;

public class Challenge {
    private int challengeNumber;        // The challenge number ranging from 0 to 119
    private Piece[] prePlacedPieces;    // The array of pre-placed puzzle pieces

    /**
     * The Challenge class contains an array of Piece objects, and can be uniquely
     * identified with a challenge number.
     *
     * @param prePlacedPieces  An array containing the initially placed pieces
     * @param challengeNumber  The challenge number, ranges from 0 to 119 inclusive
     */
    public Challenge(Piece[] prePlacedPieces, int challengeNumber){}

    /**
     * Returns a new challenge that's appropriate to the current difficulty.
     *
     * @param difficulty the difficulty of the game (0 - starter, 1 - junior,
     *                   2 - expert, 3 - master, 4 - wizard)
     * @return an challenge at the appropriate difficulty level
     */
    public static Challenge newChallenge(int difficulty){ return null; }

    public Piece[] getInitialPieces() { return null; }
    public int getChallengeNumber() { return 0; }

    public static Challenge getChallenge(int index) { return null; }
    public static Challenge[] getCHALLENGES() { return null; }
}
