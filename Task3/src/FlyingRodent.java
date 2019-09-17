public class FlyingRodent extends Animal {
    public FlyingRodent(Double x, Double y, Double z) {
        super(x, y, z);
    }
    public void hypnotize(Human human){
        human.setEmotionalState(Human.emotionalState.HYPNOTIZED);
    }
}