import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
        assertAll("Checks setting of XYZ to object",
                () -> assertEquals(xyz[0], materialObject.getX()),
                () -> assertEquals(xyz[1], materialObject.getY()),
                () -> assertEquals(xyz[2], materialObject.getZ())
        );
    }

    @Test
    public void testwrongXYZ(){
        double[] arr = new double[]{1.4,3.3,2.45,2.0};
        assertThrows(IllegalArgumentException.class, () -> {
            human = new Human(arr);
        },"Wrong amount of coords");
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

    @ParameterizedTest
    @CsvSource({
            "2.0, 2.0, 2.0",
            "2.3333, 32.333, 5565.3333",
            "-1.222, -2.33, -3.333",
            "-2, -4, -5"
    })
    public void testAnimalMove(double x, double y, double z) {
        animal.move(x,y,z);
        assertAll("Checks mobility of animal",
                () -> assertEquals(x, animal.getX()),
                () -> assertEquals(y, animal.getY()),
                () -> assertEquals(z, animal.getZ())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Alex","Roman"})
    public void testName(String name){
        human.setName(name);
        assertEquals(name, human.getName());
    }

    @Test
    public void testNullName(){
        assertThrows(NullPointerException.class, () -> {
            human.setName(null);
        },"Name couldn't be null");
    }

    @Test
    public void testHoldHand() throws Exception {
        human.holdHand(human1);
        assertEquals(human1, human.getHumanMoveTogether());
    }

    @Test
    public void testWrongHoldHand() throws Exception {
        human.holdHand(human1);
        assertThrows(IllegalStateException.class, () -> {
            human.holdHand(human1);
        });
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
    public void testWrongUnholdHand() {
        assertThrows(IllegalStateException.class, () -> {
            human.unholdHand();
        });
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

    @ParameterizedTest
    @CsvSource({
            "99, 1",
            "100, 20",
            "10,99,",
            "100,1"
    })
    public void testOpenDoorTogether(int a, int b) throws Exception {
        human.setStrength(a);
        human.moveToObject(door);
        human3.setStrength(b);
        human3.holdHand(human);
        human.openDoor(door);
        assertEquals(true, door.getIsOpened());
    }

    @ParameterizedTest
    @CsvSource({
            "9, 1",
            "10, 20",
            "1,9,",
            "10,1"
    })
    public void testNotOpenDoorTogether(int a, int b) throws Exception {
        human.setStrength(a);
        human.moveToObject(door);
        human3.setStrength(b);
        human3.holdHand(human);
        assertThrows(Exception.class, () -> {
                human.openDoor(door);
            },"To weak to open it together");
    }

    @Test
    public void testDoorClosed(){
        assertEquals(door.getIsOpened(), false);
    }

    @Test
    public void testOpenedDoorWeak(){
        human.setStrength(10);
        human.moveToObject(door);
        assertThrows(Exception.class, () -> {
                human.openDoor(door);
            });
    }

    @Test
    public void testOpenedDoorAlready() throws Exception {
        human.setStrength(100);
        human.moveToObject(door);
        human.openDoor(door);
        assertThrows(IllegalStateException.class, () -> {
            human.openDoor(door);
        });
    }

    @Test
    public void testWrongOpenedDoorFarAway() {
        human.setStrength(100);
        assertThrows(Exception.class, () -> {
            human.openDoor(door);
        });
    }

    @Test
    public void testWrongHoldHandFarAway() {
        assertThrows(Exception.class, () -> {
            human.holdHand(human3);
        });
    }

    @Test
    public void testHypnotize() {
        human.setEmotionalState(Human.EmotionalState.OK);
        flyingRodent.hypnotize(human);
        assertEquals(human.getEmotionalState(), Human.EmotionalState.HYPNOTIZED);
    }

    @Test
    public void testNotHypnotized(){
        human.setEmotionalState(Human.EmotionalState.OK);
        assertNotEquals(Human.EmotionalState.HYPNOTIZED,human.getEmotionalState());
    }
}