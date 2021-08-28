package comp1110.ass2;

import org.junit.jupiter.api.*;

import static comp1110.ass2.TestUtility.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeSet;

@Timeout(value = 500, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViablePieceStringsTest {

    private void test(String start, int col, int row, Set<String> expected) {
        Set<String> outSet = IQStars.getViablePieceStrings(start, col, row);
        if (expected == null) {
            if (outSet != null)
                assertTrue(outSet == null, "Expected null for game state " + start +
                        " at (" + col + ", " + row + ")," +
                        " but got " + outSet);
        } else {
            String expstr = expected.toString();
            assertTrue(outSet != null, "Got null for game state " + start + " at (" + col + ", " + row + "), but expected " + expstr);
            TreeSet<String> out = new TreeSet<>();
            out.addAll(outSet);
            String outstr = out.toString();
            assertTrue(expstr.equals(outstr), "Incorrect viable piece strings for input " + start +
                    " at (" + col + ", " + row + ")," +
                    ".  Expected " + expstr + ", but got " + outstr);
        }
    }

    @Test
    public void test_lastPiece() {
        for (ViableGameState viableGameState : VGS_LAST) {
            if (viableGameState.expected.length() == 0) {
                test(viableGameState.start, viableGameState.xLoc, viableGameState.yLoc, null);
            } else {
                Set<String> expected = new TreeSet<>();
                for (int j = 0; j < viableGameState.expected.length(); j += 4) {
                    expected.add(viableGameState.expected.substring(j, j + 4));
                }
                test(viableGameState.start, viableGameState.xLoc, viableGameState.yLoc, expected);
            }
        }
    }

    @Test
    public void test_centreP() {
        for (ViableGameState viableGameState : VGS_CENTRE) {
            if (viableGameState.expected.length() == 0) {
                test(viableGameState.start, viableGameState.xLoc, viableGameState.yLoc, null);
            } else {
                Set<String> expected = new TreeSet<>();
                for (int j = 0; j < viableGameState.expected.length(); j += 4) {
                    expected.add(viableGameState.expected.substring(j, j + 4));
                }
                test(viableGameState.start, viableGameState.xLoc, viableGameState.yLoc, expected);
            }
        }
    }

    @Test
    public void test_sidesP() {
        for (ViableGameState vgsSide : VGS_SIDES) {
            if (vgsSide.expected.length() == 0) {
                test(vgsSide.start, vgsSide.xLoc, vgsSide.yLoc, null);
            } else {
                Set<String> expected = new TreeSet<>();
                for (int j = 0; j < vgsSide.expected.length(); j += 4) {
                    expected.add(vgsSide.expected.substring(j, j + 4));
                }
                test(vgsSide.start, vgsSide.xLoc, vgsSide.yLoc, expected);
            }
        }
    }
}