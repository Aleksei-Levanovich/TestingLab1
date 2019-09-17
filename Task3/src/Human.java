import java.util.ArrayList;

public class Human extends Animal {
    public Human(Double x, Double y, Double z) {
        super(x, y, z);
    }
    private String name;
    enum emotionalState {DESPAIR, OK, HYPNOTIZED}
    private Integer strength;
    private ArrayList<Human> handHoldBy;
    private emotionalState EmotionalState;

    public void setEmotionalState(emotionalState emotionalState) {
        this.EmotionalState = emotionalState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public void setHandHoldBy(ArrayList<Human> handHoldBy) {
        this.handHoldBy = handHoldBy;
    }
}
