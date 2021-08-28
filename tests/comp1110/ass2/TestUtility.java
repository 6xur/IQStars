package comp1110.ass2;

public class TestUtility {

  // Badly formed pieces
  static final String[] BAD_PIECES = {
          "a231",
          "y621",
          "i311",
          "g261",
          "b070",
          "i22\"",
          "p234"
  };

  // Badly formed wizards
  static final String[] BAD_WIZARDS = {
          "a31",
          "y71",
          "g61",
          "b70",
          "i2\"",
          "p34"
  };

  // Pieces off board
  static final String[] OFF_BOARD = {
          "r070",
          "o154",
          "y160",
          "g302",
          "b151",
          "i212",
          "p462"
  };

  // Overlapping pieces
  static final String[] OVERLAP = {
          "r000y000W",
          "o000g011W",
          "b220i111W",
          "y001i141p040W",
          "r121o302y400g262b441i010p440W"
  };

  // Wizards off board or overlapping
  static final String[] INVALID_WIZARDS = {
          "Wr70",
          "Wo63",
          "Wy24",
          "Wg22b81",
          "Wi12p12",
          "Wr22y13b22i00",
          "Wo42o42"
  };

  // A piece has been placed that doesn't cover the corresponding wizard
  static final String[] UNCOVERED_WIZARDS = {
          "g000Wr40g20b60",
          "i140Wb32i21",
          "o002y230Wo21y31",
          "r250g221i032Wr62g22i42",
          "g231i000p151Wg11g32i00p43",
          "o110g262p030Wo32g31p30",
  };

  // A piece has been placed covering a non-matching wizard
  static final String[] BAD_COVERED_WIZARDS = {
          "i023Wr30y23",
          "p001Wr62g22i42",
          "b030Wy50b30p10",
          "y341Wo52y32",
          "i130Wg11b13p31",
          "o021p522Wr43o42i33",
          "b310Wb32i21",
          "g001p232Wg11g32i00p43",
          "r140o460g120Wr40g20b60"
  };

  public static int toInt(char c) {
    return switch (c) {
      case 'r' -> 0;
      case 'o' -> 1;
      case 'y' -> 2;
      case 'g' -> 3;
      case 'b' -> 4;
      case 'i' -> 5;
      case 'p' -> 6;
      default -> 7;
    };
  }

  public static char toChar(int n) {
    return switch (n) {
      case 0 -> 'r';
      case 1 -> 'o';
      case 2 -> 'y';
      case 3 -> 'g';
      case 4 -> 'b';
      case 5 -> 'i';
      case 6 -> 'p';
      default -> 'W';
    };
  }

  /**
   * For VGSs:
   *    - spot covered by piece matching x/y
   *    - spot covered by piece with greater/smaller x/y
   *    - Edges/corners
   *    - Rotations
   *    - None possible
   *      - Because no piece fits
   *      - Because the piece(s) that would fit are used
   *      - Because space is already covered
   *    - Wizards:
   *      - Not viable because anything would cover the wrong wizard
   *      - Not viable because it cannot cover the right wizard
   */

  //  One piece left in game
  static final ViableGameState[] VGS_LAST = {
          new ViableGameState("r151o241y400g540i023p202W", 1, 0, "b010"),
          new ViableGameState("r022o302y000g511b030p242W", 6, 0, "i260"),
          new ViableGameState("o140y250g262b400i231p202W", 3, 0, "r220"),
          new ViableGameState("r250o401y521b331i020p000W", 3, 3, "g262"),
          new ViableGameState("r010o302g262b231i100p030W", 5, 2, "y250"),
          new ViableGameState("r250y322g262b100i010p340W", 3, 2, "o011"),
          new ViableGameState("r101y250g000b020i013p221W", 5, 3, "o262"),
          new ViableGameState("r002o530y141g001i000p331W", 5, 1, "b450"),
          new ViableGameState("r031o100y322g262b040i010W", 1, 2, "p511")
  };

  // Location in center
  static final ViableGameState[] VGS_CENTRE = {
          new ViableGameState("o022y010b040W", 2, 1, "g021i021"),
          new ViableGameState("g040b032W", 4, 1, "o330i021"),
          new ViableGameState("y200g311i020W", 3, 1, "r021r031r231o021o031o250o331o531b021b031b250b331b431p031p231p531"),
          new ViableGameState("g040b032W", 4, 1, "o330i021"),
          new ViableGameState("o401y010i013W", 1, 2, "g231"),
          new ViableGameState("r002b342i011W", 3, 2, ""),
          new ViableGameState("o401y210g130i040W", 2, 1, ""),
          new ViableGameState("y001g020W", 3, 1, ""),
          new ViableGameState("Wg11b13p31", 3, 1, "p031p021p020p131p130p231p230p220p331p340p421p431p440p540p531p521"),
          new ViableGameState("g460i021p512Wb32i21", 4, 2, ""),
          new ViableGameState("r010y332g331b450Wr10o30i00", 2, 2, ""),
  };

  // Location on edge/corner
  static final ViableGameState[] VGS_SIDES = {
          new ViableGameState("y020i002W", 0, 0, "r000o000o300"),
          new ViableGameState("o520g221W", 0, 0, ""),
          new ViableGameState("y040b000p301W", 3, 0, "r130o130o430g130g330g430i130i230"),
          new ViableGameState("r000b450i021W", 4, 0, ""),
          new ViableGameState("o000y230i042W", 6, 0, "r250"),
          new ViableGameState("i150W", 6, 0, ""),
          new ViableGameState("y042W", 5, 1, "r040r250o040o260o340g260g531b040b340i031p240p540"),
          new ViableGameState("o340W", 5, 1, ""),
          new ViableGameState("b431W", 5, 3, "r151o151o451g460"),
          new ViableGameState("r032o550W", 5, 3, ""),
          new ViableGameState("o101b251Wr30y23", 1, 3, "y332y521g120i221p522"),
          new ViableGameState("y211Wr43o42i33", 3, 3, ""),
          new ViableGameState("Wr40i12", 0, 3, "o222o302o401y322g232g410i211p111p202p301p401"),
          new ViableGameState("o260y012g530p242W", 0, 3, ""),
          new ViableGameState("o031g232b520i030W", 0, 1, "y200"),
          new ViableGameState("g300W", 0, 1, "")
  };
}

class ViableGameState {
  String start;
  int xLoc;
  int yLoc;
  String expected;

  ViableGameState(String iStart,
                  int iX, int iY,
                  String iExpected) {
    start = iStart;
    xLoc = iX;
    yLoc = iY;
    expected = iExpected;
  }
}
