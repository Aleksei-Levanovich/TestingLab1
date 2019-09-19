import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests {
    private double xyz[] = new double[]{1.1, 2.22222, 3.444};
    private double xyz1[] = new double[]{1.0, 2.0, 3.0};
    private double xyz2[] = new double[]{8.6, 66.22, 366.4};

    private Human human, human1, human2, human3;
    private Door door, door1;
    private Animal animal;
    private FlyingRodent flyingRodent;
    private MaterialObject materialObject, materialObject1;

    public void setUp() {
        human = new Human(xyz);
        human1 = new Human(xyz1);
        human2 = new Human(1.1, 2.22222, 3.444);
        human3 = new Human(xyz2);
        door = new Door(false, 100, xyz2);
        door1 = new Door(false, 0, 1.2,2.3,3.0);
        animal = new Animal(xyz);
        flyingRodent = new FlyingRodent(xyz1);
        materialObject = new MaterialObject(xyz);
        materialObject1 = new MaterialObject(xyz2);
    }

    @BeforeAll
    public void setStates() {
        setUp();
    }

    @AfterEach
    public void returnStates() {
        setUp();
    }

    @Test
    public void testSetXYZ() {
        Double testXYZ[] = new Double[]{materialObject.getX(), materialObject.getY(), materialObject.getZ()};
        assertAll("Checks setting of XYZ to object",
                () -> assertEquals(xyz[0], testXYZ[0]),
                () -> assertEquals(xyz[1], testXYZ[1]),
                () -> assertEquals(xyz[2], testXYZ[2])
        );
    }

    @Test
    public void testAnimalMoveToObject() {
        animal.moveToObject(door1);
        assertAll("Checks mobility of animal to object",
                () -> assertEquals(door1.getX(), animal.getX()),
                () -> assertEquals(door1.getY(), animal.getY()),
                () -> assertEquals(door1.getZ(), animal.getZ())
        );
    }

    @Test
    public void testAnimalMove() {
        animal.move(2.0, 2.0, 2.0);
        assertAll("Checks mobility of animal",
                () -> assertEquals(2.0, animal.getX()),
                () -> assertEquals(2.0, animal.getY()),
                () -> assertEquals(2.0, animal.getZ())
        );
    }

    @Test
    public void testName(){
        human.setName("Alex");
        assertEquals("Alex", human.getName());
    }

    @Test
    public void testHoldHand() throws Exception {
        human.holdHand(human1);
        assertEquals(human1, human.getHumanMoveTogether());
    }

    @Test
    public void testUnholdHand() throws Exception {
        human.holdHand(human1);
        human1.unholdHand();
        assertAll("Checks unhold hand",
                () -> assertEquals(null, human.getHumanMoveTogether()),
                () -> assertEquals(null, human1.getHumanMoveTogether())
        );
    }

    @Test
    public void testMoveTogether() throws Exception {
        human.holdHand(human1);
        human.moveToObject(materialObject1);
        assertAll("Checks XYZ of humans",
                () -> assertEquals(materialObject1.getX(), human.getX()),
                () -> assertEquals(human.getX(), human1.getX()),
                () -> assertEquals(materialObject1.getY(), human.getY()),
                () -> assertEquals(human.getY(), human1.getY()),
                () -> assertEquals(materialObject1.getZ(), human.getZ()),
                () -> assertEquals(human.getZ(), human1.getZ())
        );
    }

    @Test
    public void openDoor() throws Exception {
        human.setStrength(99);
        human.moveToObject(door);
        human3.setStrength(1);
        human3.holdHand(human);
        human.openDoor(door);
    }

    @Test
    public void testHypnotize() {
        human.setEmotionalState(Human.EmotionalState.OK);
        flyingRodent.hypnotize(human);
        assertEquals(human.getEmotionalState(), Human.EmotionalState.HYPNOTIZED);
    }
}