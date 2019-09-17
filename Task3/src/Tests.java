import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    public void testSetXYZ(){
        Double xyz[] = new Double[] {1.1,2.22222,3.444};
        MaterialObject materialObject = new MaterialObject(xyz[0],xyz[1],xyz[2]);
        Double testXYZ[] = new Double[]{materialObject.getX(),materialObject.getY(),materialObject.getZ()};
        assertAll("Checks setting of XYZ to object",
                () -> assertEquals(xyz[0],testXYZ[0]),
                () -> assertEquals(xyz[1],testXYZ[1]),
                () -> assertEquals(xyz[2],testXYZ[2])
        );
    }
}
