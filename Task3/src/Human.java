import java.util.ArrayList;

public class Human extends Animal {
    public Human(Double x, Double y, Double z) {
        super(x, y, z);
    }

    public Human(Double[] xyz) {
        super(xyz);
    }

    private String name;
    enum emotionalState {DESPAIR, OK, HYPNOTIZED}
    private Integer strength;
    private Human humanMoveTogether;
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

    public void setHumanMoveTogether(Human humanMoveTogether) {
        this.humanMoveTogether = humanMoveTogether;
    }

    public Human getHumanMoveTogether() {
        return humanMoveTogether;
    }

    public Integer getStrength() {
        return strength;
    }

    public void holdHand(Human human){
        if (this.humanMoveTogether==null && human.getHumanMoveTogether()==null){
            this.humanMoveTogether=human;
            human.setHumanMoveTogether(this);
        }
    }

    public void unholdHand(){
        if (this.getHumanMoveTogether()!=null){
            this.getHumanMoveTogether().setHumanMoveTogether(null);
            this.setHumanMoveTogether(null);
        }
    }

    @Override
    public void move(Double x, Double y, Double z){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        if (this.humanMoveTogether!=null){
            this.humanMoveTogether.setX(x);
            this.humanMoveTogether.setY(y);
            this.humanMoveTogether.setZ(z);
        }
    }

    @Override
    public void moveToObject(MaterialObject object){
        this.setX(object.getX());
        this.setY(object.getY());
        this.setZ(object.getZ());
        if (this.humanMoveTogether!=null){
            this.humanMoveTogether.setX(object.getX());
            this.humanMoveTogether.setY(object.getY());
            this.humanMoveTogether.setZ(object.getZ());
        }
    }

    public void openDoor(Door door){
        Integer strength = this.getStrength();
        if (this.getHumanMoveTogether()!=null){
            strength+=this.humanMoveTogether.getStrength();
        }
        if (strength>=door.getStrengthToOpen()) {
            door.setIsOpened(true);
        }
    }
}
