public class Animal extends MaterialObject{

    public Animal(Double x, Double y, Double z) {
        super(x, y, z);
    }

    public void move(Double x, Double y, Double z){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
}
