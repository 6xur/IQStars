package comp1110.ass2;

import org.junit.jupiter.api.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameStringWellFormedTest {

    private void test(String in, boolean expected) {
        boolean out = IQStars.isGameStringWellFormed(in);
        assertTrue(out == expected, "Input was '" + in + "', expected " + expected + " but got " + out);
    }

    @Test
    public void threeOrFourCharacters() {
        test("r32", true);
        test("g1", false);
        test("p00", true);
        test("r32p00", false);
        test("", false);
        test("o141y", false);
        test("r032", true);
        test("p000", true);
        test("r032p000", false);
        test("o1420", false);
    }

    @Test
    public void firstCharacterOKWizard() {
        String goodChars = "roygbip";
        for (int i = 0; i < goodChars.length(); i++) {
            test(goodChars.charAt(i) + "31", true);
            test(goodChars.charAt(i) + "02", true);
        }
        String badChars = "ROYGBIP?!=;XQMfwcds`1n'3";
        for (int i = 0; i < badChars.length(); i++) {
            test(badChars.charAt(i) + "31", false);
            test(badChars.charAt(i) + "02", false);
        }
    }

    @Test
    public void secondAndThirdCharactersOKWizard() {
        String testString = "r01o02y42g31b50i00p30";
        for (int i = 0; i < testString.length() - 3; i += 3) {
            test(testString.substring(i, i + 3), true);
            test(testString.substring(i + 1, i + 4), false);
        }
        for (char row = '0'; row <= '2'; row += 2) {
            for (char col = '0'; col <= '6'; col++) {
                test("r" + col + row, true);
                test("p" + col + row, true);
            }
            test("r7" + row, false);
            test("r!" + row, false);
            test("rr" + row, false);
        }
        for (char row = '1'; row <= '3'; row += 2) {
            for (char col = '0'; col <= '5'; col++) {
                test("r" + col + row, true);
                test("p" + col + row, true);
            }
            test("r6" + row, false);
            test("r7" + row, false);
            test("r!" + row, false);
            test("rr" + row, false);
        }
    }

    @Test
    public void firstCharacterOKPiece() {
        String goodChars = "roygbip";
        for (int i = 0; i < goodChars.length(); i++) {
            test(goodChars.charAt(i) + "231", true);
            test(goodChars.charAt(i) + "102", true);
        }
        String badChars = "ROYGBIP?!=;XQMfwcds`1n'3";
        for (int i = 0; i < badChars.length(); i++) {
            test(badChars.charAt(i) + "231", false);
            test(badChars.charAt(i) + "102", false);
        }
    }

    @Test
    public void secondCharacterOKPiece() {
        String asymChars = "oygbp";
        for (int i = 0; i < asymChars.length(); i++) {
            for (char rot = '0'; rot < '6'; rot++) {
                test("" + asymChars.charAt(i) + rot + "21", true);
                test("" + asymChars.charAt(i) + rot + "33", true);
            }
            test(asymChars.charAt(i) + "!21", false);
            test(asymChars.charAt(i) + "621", false);
        }
        String symChars = "ri";
        for (int i = 0; i < symChars.length(); i++) {
            for (char rot = '0'; rot < '3'; rot++) {
                test("" + symChars.charAt(i) + rot + "21", true);
                test("" + asymChars.charAt(i) + rot + "33", true);
            }
            for (char rot = '3'; rot <= '6'; rot++) {
                test("" + symChars.charAt(i) + rot + "21", false);
                test("" + symChars.charAt(i) + rot + "33", false);
            }
            test(symChars.charAt(i) + "!21", false);
        }
    }

    @Test
    public void thirdAndFourthCharactersOKPiece() {
        String testString = "r001o302y342g031b450i000p430";
        for (int i = 0; i < testString.length() - 4; i += 4) {
            test(testString.substring(i, i + 4), true);
            test(testString.substring(i + 1, i + 5), false);
        }
        for (char row = '0'; row <= '2'; row += 2) {
            for (char col = '0'; col <= '6'; col++) {
                test("r0" + col + row, true);
                test("p4" + col + row, true);
            }
            test("r07" + row, false);
            test("r0!" + row, false);
            test("r0r" + row, false);
        }
        for (char row = '1'; row <= '3'; row += 2) {
            for (char col = '0'; col <= '5'; col++) {
                test("r0" + col + row, true);
                test("p4" + col + row, true);
            }
            test("r06" + row, false);
            test("r07" + row, false);
            test("r0!" + row, false);
            test("r0r" + row, false);
        }
    }
}