public class Animal extends MaterialObject{

    public Animal(Double x, Double y, Double z) {
        super(x, y, z);
    }

    public Animal(Double[] xyz){
        super(xyz);
    }

    public void move(Double x, Double y, Double z){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
    public void moveToObject(MaterialObject object){
        this.setX(object.getX());
        this.setY(object.getY());
        this.setZ(object.getZ());
    }
}
