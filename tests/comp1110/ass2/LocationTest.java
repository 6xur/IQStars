package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationTest {

        @Test
        public void testLocFromPos() {
            for (int i = 0; i <= 25 ; i++) {
                Location res = new Location( i);
                assertTrue (res.getY() >=0 && res.getY() <=3, "y must be >=0 and <= 3");
                assertTrue(res.getX() >= 0 && res.getX() <= 6, "x must be >=0 and <=3" );
            }

            for (int i = 0; i <= 6 ; i++) {
                Location res = new Location( i);
                assertEquals(0, res.getY(), "y must be 0 for x in {0, 6}");
            }

            for (int i : new int[]{6, 19}) {
                Location res = new Location( i);
                assertEquals( 6,  res.getX(), "x must be 6 for positions 6 and 19");
            }

        }

}


