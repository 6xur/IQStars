package comp1110.ass2;

import org.junit.jupiter.api.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static comp1110.ass2.Games.*;
import static comp1110.ass2.TestUtility.BAD_PIECES;
import static comp1110.ass2.TestUtility.BAD_WIZARDS;

@Timeout(value = 500, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameStateStringWellFormedTest {

    private void test(String in, boolean expected) {
        boolean out = IQStars.isGameStateStringWellFormed(in);
        assertEquals(expected, out, "Incorrect result for input '"+in+"'");
    }

    @Test
    public void pieceStringLength() {
        test("W", true);
        test("yW", false);
        test("y0W", false);
        test("y03W", false);
        test("y032W", true);
        test("y032gW", false);
        test("g231i003W", true);
        test("g231i003pW", false);
        test("r010o222g130b100i040W", true);
        test("r010o222g130b100i040p01W", false);
        test("r001o302y531g530b260i000p242W", true);
        test("r001o302y531g530b260i000p242p242W", false);
    }

    @Test
    public void wizardStringLength() {
        test("W", true);
        test("Wy", false);
        test("Wy3", false);
        test("Wy32", true);
        test("Wy032", false);
        test("Wy32g", false);
        test("Wg31i03", true);
        test("Wg31i03p", false);
        test("Wr10o22g30b00", true);
        test("Wr10o22g30b00i40p0", false);
        test("Wr01o02y31g30b60i00p42", true);
        test("Wr10r01r11o02y31g30b60i00p42p43", true);
    }

    @Test
    public void piecesWellFormed() {
        for (int i = 0; i < 4*24; i++) {
            for (int j = 4; j < ALL_CHALLENGES[i].length(); j += 4) {
                test(ALL_CHALLENGES[i].substring(0, j)+"W", true);
                test(ALL_CHALLENGES[i].substring(0, j - 4) + BAD_PIECES[i % BAD_PIECES.length]+"W", false);
                test(ALL_CHALLENGES[i].substring(0, j - 4) + BAD_PIECES[i % BAD_PIECES.length]+ALL_CHALLENGES[i].substring(j), false);
            }
        }
    }

    @Test
    public void wizardsWellFormed() {
        for (int i = 24*4; i < 24*5; i++) {
            for (int j = 4; j < ALL_CHALLENGES[i].length(); j += 3) {
                test(ALL_CHALLENGES[i].substring(0, j), true);
                test(ALL_CHALLENGES[i].substring(0, j - 3) + BAD_WIZARDS[i % BAD_WIZARDS.length], false);
            }
        }
    }

    @Test
    public void correctOrder() {
        for (String solution : ALL_CHALLENGES_SOLUTIONS) {
            test(solution, true);

            int index = -1;
            for (int i = 0; i < solution.length(); i++) {
                if (solution.charAt(i) == 'W') {
                    index = i;
                    break;
                }
            }
            String piecePlacement = solution.substring(0, index);
            String wizardPlacement = "";
            if (index != solution.length() - 1) {
                wizardPlacement = solution.substring(index + 1);
            }

            if (piecePlacement.length() > 4) {
                int n = piecePlacement.length() / 2;
                if ((piecePlacement.length() / 4) % 2 == 1) {
                    n -= 2;
                }
                String wrong = piecePlacement.substring(n);
                wrong += piecePlacement.substring(0, n) + "W" + wizardPlacement;
                test(wrong, false);
            }

            if (wizardPlacement.length() > 3) {
                int n = wizardPlacement.length();
                if (n % 2 == 1) {
                    n = (n-3) / 2;
                }
                else {
                    n = n/2;
                }
                String wrong = piecePlacement + "W" + wizardPlacement.substring(n) + wizardPlacement.substring(0,n);
                test(wrong, false);
            }
        }
        for (String challenge : ALL_CHALLENGES) {
            test(challenge, true);
            int index = -1;
            for (int i = 0; i < challenge.length(); i++) {
                if (challenge.charAt(i) == 'W') {
                    index = i;
                    break;
                }
            }
            String piecePlacement = challenge.substring(0, index);
            String wizardPlacement = "";
            if (index != challenge.length() - 1) {
                wizardPlacement = challenge.substring(index + 1);
            }

            if (piecePlacement.length() > 4) {
                int n = piecePlacement.length() / 2;
                if ((piecePlacement.length() / 4) % 2 == 1) {
                    n -= 2;
                }
                String wrong = piecePlacement.substring(n);
                wrong += piecePlacement.substring(0, n) + "W" + wizardPlacement;
                test(wrong, false);
            }

            if (wizardPlacement.length() > 3) {
                int n = wizardPlacement.length();
                if (n % 2 == 1) {
                    n = (n-3) / 2;
                }
                else {
                    n = n/2;
                }
                String wrong = piecePlacement + "W" + wizardPlacement.substring(n) + wizardPlacement.substring(0,n);
                test(wrong, false);
            }
        }
        test("W", true);
        test("r000W", true);
        test("r000o010W", true);
        test("o010r000W", false);
        test("y232i023W", true);
        test("i023y232W", false);

        test("Wr00", true);
        test("Wr00o10", true);
        test("Wo10r00", false);
        test("Wy32i23", true);
        test("Wi23y32", false);
    }

    @Test
    public void wizardOrder() {
        String goodChars = "roygbip";
        for (int i = 0; i < goodChars.length(); i++) {
            for (int row1 = 0; row1 < 4; row1++) {
                int colMax1 = 6;
                if (row1 % 2 == 0) {
                    colMax1 = 7;
                }
                for (int col1 = 0; col1 < colMax1; col1++) {
                    for (int row2 = row1; row2 < 4; row2++) {
                        int colMax2 = 6;
                        if (row2 % 2 == 0) {
                            colMax2 = 7;
                        }
                        for (int col2 = col1+1; col2 < colMax2; col2++) {
                            String wizard1 = ""+goodChars.charAt(i) + col1 + row1;
                            String wizard2 = ""+goodChars.charAt(i) + col2 + row2;
                            test("W"+wizard1+wizard2, true);
                            test("W"+wizard2+wizard1, false);
                        }
                    }
                }
            }
        }

        test("Wb10b11", true);
        test("Wb11b10", false);
        test("Wg11g21", true);
        test("Wg21g11", false);
        test("Wg11g11", true);
        test("Wp10p01", true);
        test("Wp01p10", false);
        test("Wp01p01", true);
    }

    @Test
    public void duplicates() {
        for (String challenge : ALL_CHALLENGES) {
            test(challenge, true);
            int index = -1;
            for (int i = 0; i < challenge.length(); i++) {
                if (challenge.charAt(i) == 'W') {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < index; i += 4) {
                    test(challenge.substring(0,i+4)+challenge.substring(i,i+4) + challenge.substring(i+4), false);
                    test(challenge.substring(0,i+4)+challenge.charAt(i)+"000" + challenge.substring(i+4), false);
            }
        }
        for (String solution : ALL_CHALLENGES_SOLUTIONS) {
            test(solution, true);
            int index = -1;
            for (int i = 0; i < solution.length(); i++) {
                if (solution.charAt(i) == 'W') {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < index; i += 4) {
                if (i == index-4) {
                    test(solution.substring(0,i+4)+solution.substring(i,i+4) + solution.substring(i+4), false);
                    test(solution.substring(0,i+4)+solution.charAt(i)+"000" + solution.substring(i+4), false);
                }
                else {
                    test(solution.substring(0,i+4)+solution.substring(i,i+4)+solution.substring(i+8), false);
                    test(solution.substring(0,i+4)+solution.charAt(i)+"000"+solution.substring(i+8), false);
                }
            }
        }
    }
}