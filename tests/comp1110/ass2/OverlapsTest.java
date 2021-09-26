package comp1110.ass2;

import java.util.*;

import org.junit.jupiter.api.Test;

import static comp1110.ass2.State.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverlapsTest {

    private void test(Piece piece, Location other, boolean expected) {
        boolean out = piece.overlaps(other);
        assertEquals(expected, out, "Expected " + expected + " for piece " + piece + " and location " + other + ", but got " + out);
    }

    private void test(Piece piece, Piece other, boolean expected) {
        boolean out = piece.overlaps(other);
        assertEquals(expected, out, "Expected " + expected + " for piece " + piece + " and piece " + other + ", but got " + out);
    }

    @Test
    public void testLocationOverlap() {
        Piece redPiece = new Piece(State.RED, 0);
        redPiece.setPiece(new Location(2, 1));

        // Red piece overlaps with these locations
        test(redPiece, new Location(2, 1), true);
        test(redPiece, new Location(3, 1), true);
        test(redPiece, new Location(3, 2), true);
        test(redPiece, new Location(4, 2), true);

        // Red piece doesn't overlap with these locations
        test(redPiece, new Location(0, 0), false);
        test(redPiece, new Location(3, 0), false);
        test(redPiece, new Location(4, 1), false);
        test(redPiece, new Location(5, 2), false);

        // A piece always overlaps with its top left star
        redPiece.setPiece(new Location(0, 0));
        test(redPiece, new Location(0, 0), true);
        redPiece.setPiece(new Location(4, 2));
        test(redPiece, new Location(4, 2), true);
    }

    @Test
    public void testPieceOverlap() {
        Piece orangePiece = new Piece(State.ORANGE, 0);
        orangePiece.setPiece(new Location(0, 0));
        Piece indigoPiece = new Piece(State.INDIGO, 0);
        indigoPiece.setPiece(new Location(0, 1));

        // A piece overlaps with itself
        test(orangePiece, orangePiece, true);
        test(indigoPiece, indigoPiece, true);

        // Piece A overlaps with piece B if and only if B overlaps with A
        test(orangePiece, indigoPiece, true);
        test(indigoPiece, orangePiece, true);

        // A piece overlaps with another piece regardless of their orientation or colour if their top left stars occupy the same location
        indigoPiece.setOrientationLabel(1);
        indigoPiece.setPiece(new Location(0, 0));
        test(orangePiece, indigoPiece, true);

        // Piece A doesn't overlap with piece B if and only if B doesn't overlap with A
        indigoPiece.setPiece(new Location(0, 1));
        test(orangePiece, indigoPiece, false);
        test(indigoPiece, orangePiece, false);
    }

    @Test
    public void testRandom() {
        Random rand = new Random();
        State[] colours = {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, PINK};
        Set<Location> allLocations = new HashSet<>();
        for(int y = 0; y < 4; y++){
            if(y % 2 == 0){
                for(int x = 0; x < 7; x++){
                    allLocations.add(new Location(x, y));
                }
            } else{
                for(int x = 0; x < 6; x++){
                    allLocations.add(new Location(x, y));
                }
            }
        }

        // A piece only overlaps with the locations that are occupied by its stars
        State colour = colours[rand.nextInt(colours.length)];
        Piece testPiece = new Piece(colour, 0);
        testPiece.setPiece(new Location(2, 1));
        for (Location star : testPiece.getPieceStars()) {
            test(testPiece, star, true);
        }
        allLocations.removeAll(new HashSet<>(Arrays.asList(testPiece.getPieceStars())));
        for(Location location : allLocations){
            test(testPiece, location, false);
        }

        // A piece doesn't overlap with any other piece that is in the solution string
        String solution = Games.ALL_CHALLENGES_SOLUTIONS[rand.nextInt(120)];
        ArrayList<Piece> placedPieces = IQStars.getPlacedPieces(solution);
        for (int i = 0; i < placedPieces.size(); i++) {
            for (int j = i + 1; j < placedPieces.size(); j++) {
                test(placedPieces.get(i), placedPieces.get(j), false);
            }
        }
    }
}