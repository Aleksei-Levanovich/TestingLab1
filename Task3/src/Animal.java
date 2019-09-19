public class Animal extends MaterialObject{

    public Animal(double x, double y, double z) {
        super(x, y, z);
    }

    public Animal(double[] xyz){
        super(xyz);
    }

    public void move(double x, double y, double z){
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