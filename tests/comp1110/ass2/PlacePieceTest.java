package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlacePieceTest {

    private static final String errorMessageInvalid = "invalid input";

    private void test(String newPieceString, String currentStateString, String updatedStateString) {
        String output = Piece.placePiece(newPieceString,currentStateString);

        assertEquals(updatedStateString, output, "Expected output to be " + updatedStateString +
                " for new piece to be added '" + newPieceString +
                "', and current state string '" + currentStateString +
                "' but got " + output + ".");
    }

    @Test
    public void testSimple() {

        // Invalid new piece string
        test("", "y322g262b100i010p340W", errorMessageInvalid);
        test("r1", "y322g262b100i010p340W", errorMessageInvalid);
        test("r12", "y322g262b100i010p340W", errorMessageInvalid);
        test("r32y", "y322g262b100i010p340W", errorMessageInvalid);

        // Invalid current state string
        test("r252", "rW", errorMessageInvalid);
        test("r252", "r230oW", errorMessageInvalid);
        test("r252", "Wr120", errorMessageInvalid);
        test("r252", "y200g510W", errorMessageInvalid);

    }

    @Test
    public void testConflictsPieceAndState() {

        // The new piece cannot be added to the current state
        test("r252","y322g262b100i010p340W", errorMessageInvalid);
        test("y321","y322g262b100i010p340W", errorMessageInvalid);
        test("y321","r041o100g540b342i010p511Wy13b43i30", errorMessageInvalid);

    }

    @Test
    public void testPlacePiece() {

        test("p151","r020o302y040g311b400i032W","r020o302y040g311b400i032p151W");
        test("g262","r010o030y250b531i100p202W","r010o030y250g262b531i100p202W");

        // The new piece already exists on board
        test("o100","r252o100y511g130b040i241p010W","r252o100y511g130b040i241p010W");
        test("r030", "r030o010y400g532b450i033p202W",  "r030o010y400g532b450i033p202W");

        // The current state includes wizard on board
        test("i121","r252o541y200g540b312p420Wr43o42i33","r252o541y200g540b312i121p420Wr43o42i33");
        test("y040","r151o401g120b130i023p000Wy50b30p10","r151o401y040g120b130i023p000Wy50b30p10");
    }

    @Test
    public void testRandom() {
        String[] allSolutions = Games.ALL_CHALLENGES_SOLUTIONS;
        Random r = new Random();
        String solution = allSolutions[r.nextInt(120)];
        int pieceIndex = r.nextInt(7);
        String piece = solution.substring(pieceIndex * 4,(pieceIndex+1) * 4);
        String currentState = solution.replaceAll(piece,"");
        test(piece,currentState,solution);
    }

}
