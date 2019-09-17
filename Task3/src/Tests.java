import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    Double xyz[] = new Double[] {1.1,2.22222,3.444};
    Double xyz2[] = new Double[] {8.6,66.22,366.4};
    @Test
    public void testSetXYZ(){
        MaterialObject materialObject = new MaterialObject(xyz[0],xyz[1],xyz[2]);
        Double testXYZ[] = new Double[]{materialObject.getX(),materialObject.getY(),materialObject.getZ()};
        assertAll("Checks setting of XYZ to object",
                () -> assertEquals(xyz[0],testXYZ[0]),
                () -> assertEquals(xyz[1],testXYZ[1]),
                () -> assertEquals(xyz[2],testXYZ[2])
        );
    }

    @Test
    public void testAnimalMove(){
        Animal animal = new Animal(xyz[0],xyz[1],xyz[2]);
        Door door = new Door(false,100,xyz2);
        animal.moveToObject(door);
        Double testXYZ[] = new Double[]{animal.getX(),animal.getY(),animal.getZ()};
        assertAll("Checks movability of animal to object",
                () -> assertEquals(xyz2[0],testXYZ[0]),
                () -> assertEquals(xyz2[1],testXYZ[1]),
                () -> assertEquals(xyz2[2],testXYZ[2])
        );
    }
}
