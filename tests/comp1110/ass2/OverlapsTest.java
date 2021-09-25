package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OverlapsTest {

    @Test
    public void testLocationOverlap(){
        // top left start overlaps with the test location
        Set<Location> LOCATIONS = new HashSet<>();
        for(int y = 0; y < 4; y++){
            if(y % 2 == 0){
                for(int x = 0; x < 7; x++){
                    LOCATIONS.add(new Location(x, y));
                }
            } else{
                for(int x = 0; x < 6; x++){
                    LOCATIONS.add(new Location(x, y));
                }
            }
        }

        System.out.println("size before: " + LOCATIONS.size());

        Location testLocation = new Location(2,1);
        Piece redPiece = new Piece(State.RED, 0);

        redPiece.setPiece(new Location(2, 1));
        assertTrue(redPiece.overlaps(testLocation));

        Set<Location> redPieceStars = new HashSet<>(Arrays.asList(redPiece.getPieceStars()));
        System.out.println("size of redPieceStars: " + redPieceStars.size());

        LOCATIONS.removeAll(redPieceStars);
        System.out.println("size after: " + LOCATIONS.size());

        redPiece.setPiece(new Location(1, 1));
        assertTrue(redPiece.overlaps(testLocation));

        redPiece.setPiece(new Location(2, 0));
        assertTrue(redPiece.overlaps(testLocation));

        redPiece.setPiece(new Location(1, 0));
        assertTrue(redPiece.overlaps(testLocation));

        // a piece overlaps with all of its stars
        Location[] starLoc = redPiece.getPieceStars();
        for(Location loc:starLoc){
            assertTrue(redPiece.overlaps(loc));
        }
    }

    @Test
    public void testLocationNoOverlap(){
        Piece redPiece = new Piece(State.RED, 0);
        Location testLocation = new Location(2,1);

        redPiece.setPiece(new Location(0 ,1));
        assertFalse(redPiece.overlaps(testLocation));

        redPiece.setPiece(new Location(4, 1));
        assertFalse(redPiece.overlaps(testLocation));
    }

    @Test
    public void testPieceOverlap(){
        Piece orangePiece = new Piece(State.ORANGE, 0);
        orangePiece.setPiece(new Location(1, 1));

        Piece indigoPiece = new Piece(State.INDIGO, 0);
        indigoPiece.setPiece(new Location(1, 1));
        assertTrue(orangePiece.overlaps(indigoPiece));

        indigoPiece.setPiece(new Location(0 ,1));
        assertTrue(orangePiece.overlaps(indigoPiece));

        indigoPiece.setPiece(new Location(2, 2));
        assertTrue(orangePiece.overlaps(indigoPiece));
    }

    @Test
    public void testPieceNoOverlap(){
        Piece orangePiece = new Piece(State.ORANGE, 0);
        Piece indigoPiece = new Piece(State.INDIGO, 0);

        indigoPiece.setPiece(new Location(1, 0));
        assertFalse(orangePiece.overlaps(indigoPiece));

        indigoPiece.setPiece(new Location(1, 3));
        assertFalse(orangePiece.overlaps(indigoPiece));
    }
}
