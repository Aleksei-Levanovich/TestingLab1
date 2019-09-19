public class FlyingRodent extends Animal {
    public FlyingRodent(double x, double y, double z) {
        super(x, y, z);
    }

    public FlyingRodent(double[] xyz) {
        super(xyz);
    }

    public void hypnotize(Human human) {
        human.setEmotionalState(Human.EmotionalState.HYPNOTIZED);
    }
}