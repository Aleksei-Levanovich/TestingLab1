import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests {
    private Double xyz[] = new Double[] {1.1,2.22222,3.444};
    private Double xyz1[] = new Double[] {1.0,2.0,3.0};
    private Double xyz2[] = new Double[] {8.6,66.22,366.4};

    private Human human, human1, human2, human3;
    private Door door;
    private Animal animal;
    private FlyingRodent flyingRodent;
    private MaterialObject materialObject, materialObject1;

    @BeforeAll
    public void setUp(){
        human = new Human(xyz);
        human1 = new Human(xyz1);
        human2 = new Human(xyz);
        human3 = new Human(xyz2);
        door = new Door(false,100,xyz2);
        animal = new Animal(xyz);
        flyingRodent = new FlyingRodent(xyz1);
        materialObject = new MaterialObject(xyz);
        materialObject1 = new MaterialObject(xyz2);
    }

    @AfterEach
    public void returnStates(){
        setUp();
    }

    @Test
    public void testSetXYZ(){
        Double testXYZ[] = new Double[]{materialObject.getX(),materialObject.getY(),materialObject.getZ()};
        assertAll("Checks setting of XYZ to object",
                () -> assertEquals(xyz[0],testXYZ[0]),
                () -> assertEquals(xyz[1],testXYZ[1]),
                () -> assertEquals(xyz[2],testXYZ[2])
        );
    }

    @Test
    public void testAnimalMove(){
        animal.moveToObject(door);
        Double testXYZ[] = new Double[]{animal.getX(),animal.getY(),animal.getZ()};
        assertAll("Checks mobility of animal to object",
                () -> assertEquals(xyz2[0],testXYZ[0]),
                () -> assertEquals(xyz2[1],testXYZ[1]),
                () -> assertEquals(xyz2[2],testXYZ[2])
        );
    }

    @Test
    public void testHoldHand(){
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
        human.holdHand(human1);
        human1.unholdHand();
        assertAll("Checks unhold hand",
                () -> assertEquals(null, human.getHumanMoveTogether()),
                () -> assertEquals(null, human1.getHumanMoveTogether())
        );
    }

    @Test
    public void testMoveTogether(){
        human.holdHand(human1);
        human.moveToObject(materialObject1);
        assertAll("Checks XYZ of humans",
                () -> assertEquals(materialObject1.getX(),human.getX()),
                () -> assertEquals(human.getX(),human1.getX()),
                () -> assertEquals(materialObject1.getY(),human.getY()),
                () -> assertEquals(human.getY(),human1.getY()),
                () -> assertEquals(materialObject1.getZ(),human.getZ()),
                () -> assertEquals(human.getZ(),human1.getZ())
        );
    }

    @Test
    public void openDoor(){
        human.setStrength(99);
        human.openDoor(door);
        assertEquals(false,door.getIsOpened());
        human.moveToObject(door);
        human.openDoor(door);
        assertEquals(false,door.getIsOpened());
        human3.setStrength(1);
        human3.holdHand(human);
        human.openDoor(door);
        assertEquals(true, door.getIsOpened());
    }

    @Test
    public void testHypnotize(){
        human.setEmotionalState(Human.emotionalState.OK);
        flyingRodent.hypnotize(human);
        assertEquals(human.getEmotionalState(), Human.emotionalState.HYPNOTIZED);
    }
}