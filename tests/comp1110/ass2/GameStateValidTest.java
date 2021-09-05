package comp1110.ass2;

import org.junit.jupiter.api.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static comp1110.ass2.Games.*;
import static comp1110.ass2.TestUtility.*;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameStateValidTest {

    private void test(String in, String invalid, boolean expected) {
        boolean out = IQStars.isGameStateValid(in);
        assertEquals(expected, out, "Input was '" + in + "', expected " + expected + " but got " + out + (invalid.equals("") ? "" : " (subsequence " + invalid + " is not valid)"));
    }

    @Test
    public void wellFormed() {
        // reuse wellFormed test?
        for (int i = 0; i < 4*24; i++) {
            for (int j = 4; j < ALL_CHALLENGES[i].length(); j += 4) {
                test(ALL_CHALLENGES[i].substring(0, j)+"W", "", true);
                test(ALL_CHALLENGES[i].substring(0, j - 4) + BAD_PIECES[i % BAD_PIECES.length]+"W", BAD_PIECES[i % BAD_PIECES.length],false);
                test(ALL_CHALLENGES[i].substring(0, j - 4) + BAD_PIECES[i % BAD_PIECES.length]+ALL_CHALLENGES[i].substring(j), BAD_PIECES[i % BAD_PIECES.length],false);
            }
        }
    }

    @Test
    public void offBoard() {
        // Test individual pieces
        for (int i = 0; i < OFF_BOARD.length; i++) {
            String goodPiece = ALL_CHALLENGES_SOLUTIONS[i].substring((i * 4), (i + 1) * 4)+"W";
            test(OFF_BOARD[i]+"W", OFF_BOARD[i], false);
            test(goodPiece, "", true);
        }
        // Test pieces as part of game
        for (int i = 0; i < ALL_CHALLENGES.length; i++) {
            String badPiece = OFF_BOARD[i % OFF_BOARD.length];
            String goodGameState = ALL_CHALLENGES[i];
            StringBuilder badGameStateBuilder = new StringBuilder();
            for (int j = 0; j < goodGameState.length(); j += 4) {
                if (goodGameState.charAt(j) == badPiece.charAt(0)) {
                    badGameStateBuilder.append(badPiece);
                    badGameStateBuilder.append(goodGameState.substring(j + 4));
                    break;
                }
                else if (toInt(goodGameState.charAt(j)) > toInt(badPiece.charAt(0))) {
                    badGameStateBuilder.append(badPiece);
                    badGameStateBuilder.append(goodGameState.substring(j));
                    break;
                }
                else {
                    badGameStateBuilder.append(goodGameState, j, j + 4);
                }
            }
            String badGameState = badGameStateBuilder.toString();
            test(badGameState, badPiece, false);
            test(goodGameState, "", true);
        }
    }

    @Test
    public void overlap() {
        for (String overlap : OVERLAP) {
            test(overlap, "", false);
        }
        for (String sol : ALL_CHALLENGES_SOLUTIONS) {
            test(sol, "", true);
            for (int i = 0; i < 7; i++) {
                // Rotate each piece
                int rotation = Character.getNumericValue(sol.charAt(i*4+1));
                rotation = (rotation + 1) % 6;
                if (i == 0 || i == 5) {
                    rotation = rotation % 3;
                }
                String badGameState = sol.substring(0, i*4+1) + rotation + sol.substring(i*4+2);
                test(badGameState, badGameState.substring(i*4, ((i+1)*4)), false);

                // Translate each piece once to right
                int x = Character.getNumericValue(sol.charAt(i*4+2));
                x += 1;
                badGameState = sol.substring(0, i*4+2) + x + sol.substring(i*4+3);
                test(badGameState, badGameState.substring(i*4, ((i+1)*4)), false);
            }
        }
    }

    @Test
    public void wizards() {
        for (int i = 96; i < 120; i++) {
            String good = ALL_CHALLENGES[i];
            test(good, "", true);
        }
        for (String bad : INVALID_WIZARDS) {
            test(bad, "", false);
        }
    }

    @Test
    public void wizardsUncovered() {
        for (int i = 96; i < 120; i++) {
            String good = ALL_CHALLENGES_SOLUTIONS[i];
            test(good, "", true);
        }
        for (String bad : UNCOVERED_WIZARDS) {
            test(bad, "", false);
        }
        for (int i = 0; i < 7; i++) {
            test(toChar(i) + "000W"+toChar(i)+"00","", true);
            test(toChar(i) + "010W"+toChar(i)+"00","", false);
        }
    }

    @Test
    public void wizardsCovered() {
        for (int i = 96; i < 120; i++) {
            String good = ALL_CHALLENGES_SOLUTIONS[i];
            test(good, "", true);
        }
        for (String bad : BAD_COVERED_WIZARDS) {
            test(bad, "", false);
        }
        for (int i = 0; i < 7; i++) {
            test(toChar(i) + "000W"+toChar(i)+"00","", true);
            test(toChar((i+1)%7) + "000W"+toChar(i%7)+"00","", false);
        }
    }
}
