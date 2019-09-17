import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    public void testSetXYZ(){
        Double xyz[] = new Double[] {1.1,2.22222,3.444};
        MaterialObject materialObject = new MaterialObject(xyz[0],xyz[1],xyz[2]);
        Double testXYZ[] = new Double[]{materialObject.getX(),materialObject.getY(),materialObject.getZ()};
        assertEquals(xyz,testXYZ);
    }
}
