public class Human extends Animal {

    public Human(double x, double y, double z) {
        super(x, y, z);
    }

    public Human(double[] xyz) {
        super(xyz);
    }

    private String name;
    enum EmotionalState {DESPAIR, OK, HYPNOTIZED}
    private int strength;
    private Human humanMoveTogether;
    private EmotionalState emotionalState;

    public void setEmotionalState(EmotionalState emotionalState) {
        this.emotionalState = emotionalState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkDistance(MaterialObject object, MaterialObject object1) throws Exception {
        if (Math.abs(object.getX()-object1.getX())<=1 &&
                Math.abs(object.getY()-object1.getY())<=1 &&
                Math.abs(object.getZ()-object1.getZ())<=1){
            return true;
        }
        else {
            throw new Exception("Distance between objects should be less or equal to 1 in each coordinates");
        }
    }

    public String getName() {
        return name;
    }

    public EmotionalState getEmotionalState() {
        return emotionalState;
    }

    public void setStrength(int strength) {
        if (strength>=0) {
            this.strength = strength;
        } else throw new IllegalStateException("Strength should be more or equal to 0");
    }

    public void setHumanMoveTogether(Human humanMoveTogether) {
        this.humanMoveTogether = humanMoveTogether;
    }

    public Human getHumanMoveTogether() {
        return humanMoveTogether;
    }

    public int getStrength() {
        return strength;
    }

    public void holdHand(Human human) throws Exception {
        if (this.humanMoveTogether==null && human.getHumanMoveTogether()==null && checkDistance(this,human)){
            this.humanMoveTogether=human;
            human.setHumanMoveTogether(this);
        } else throw new IllegalStateException("Human is already holding the hand");
    }

    public void unholdHand(){
        if (this.getHumanMoveTogether()!=null){
            this.getHumanMoveTogether().setHumanMoveTogether(null);
            this.setHumanMoveTogether(null);
        } else throw new IllegalStateException("Human holds nobody's hand");
    }

    @Override
    public void move(double x, double y, double z){
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

    public void openDoor(Door door) throws Exception {
        Integer strength = this.getStrength();
        if (this.getHumanMoveTogether()!=null){
            strength+=this.humanMoveTogether.getStrength();
        }
        if (strength>=door.getStrengthToOpen() && checkDistance(this,door) && !door.getIsOpened()) {
            door.setIsOpened(true);
        } else if(strength<door.getStrengthToOpen()){
            throw new Exception("Too weak to open the door");
        } else throw new IllegalStateException("Door is already opened");
    }
}