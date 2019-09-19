import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    Double xyz[] = new Double[] {1.1,2.22222,3.444};
    Double xyz1[] = new Double[] {1.0,2.0,3.0};
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
        Animal animal = new Animal(xyz);
        Door door = new Door(false,100,xyz2);
        animal.moveToObject(door);
        Double testXYZ[] = new Double[]{animal.getX(),animal.getY(),animal.getZ()};
        assertAll("Checks movability of animal to object",
                () -> assertEquals(xyz2[0],testXYZ[0]),
                () -> assertEquals(xyz2[1],testXYZ[1]),
                () -> assertEquals(xyz2[2],testXYZ[2])
        );
    }

    @Test
    public void testHoldHand(){
        Human human = new Human(xyz);
        Human human1 = new Human(xyz1);
        Human human2 = new Human(xyz);
        Human human3 = new Human(xyz2);
        human.holdHand(human1);
        human2.holdHand(human3);
        human.holdHand(human3);
        assertAll("Checks human hold hand",
                () -> assertEquals(human1, human.getHumanMoveTogether()),
                () -> assertEquals(human, human1.getHumanMoveTogether()),
                () -> assertEquals(null, human2.getHumanMoveTogether()),
                () -> assertEquals(null, human3.getHumanMoveTogether())
        );
    }

    @Test
    public void testUnholdHand(){
        Human human = new Human(xyz);
        Human human1 = new Human(xyz1);
        human.holdHand(human1);
        human1.unholdHand();
        assertAll("Checks unhold hand",
                () -> assertEquals(null, human.getHumanMoveTogether()),
                () -> assertEquals(null, human1.getHumanMoveTogether())
        );
    }

    @Test
    public void testMoveTogether(){
        Human human = new Human(xyz);
        Human human1 = new Human(xyz1);
        human.holdHand(human1);
        MaterialObject object = new MaterialObject(xyz2);
        human.moveToObject(object);
        assertAll("Checks XYZ of humans",
                () -> assertEquals(object.getX(),human.getX()),
                () -> assertEquals(human.getX(),human1.getX()),
                () -> assertEquals(object.getY(),human.getY()),
                () -> assertEquals(human.getY(),human1.getY()),
                () -> assertEquals(object.getZ(),human.getZ()),
                () -> assertEquals(human.getZ(),human1.getZ())
        );
    }

    @Test
    public void openDoor(){
        Human human = new Human(xyz);
        Door door = new Door(false,100,xyz2);
        human.setStrength(99);
        human.openDoor(door);
        assertEquals(false,door.getIsOpened());
        human.moveToObject(door);
        human.openDoor(door);
        assertEquals(false,door.getIsOpened());
        Human human1 = new Human(xyz2);
        human1.setStrength(1);
        human1.holdHand(human);
        human.openDoor(door);
        assertEquals(true, door.getIsOpened());
    }

    @Test
    public void testHypnotize(){
        Human human = new Human(xyz);
        human.setEmotionalState(Human.emotionalState.OK);
        FlyingRodent flyingRodent = new FlyingRodent(xyz1);
        flyingRodent.hypnotize(human);
        assertEquals(human.getEmotionalState(), Human.emotionalState.HYPNOTIZED);
    }
}
