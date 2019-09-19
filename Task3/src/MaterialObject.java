public class MaterialObject {
    private double x;
    private double y;
    private double z;

    public MaterialObject(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public MaterialObject(double[] xyz){
        if (xyz.length==3){
            this.x=xyz[0];
            this.y=xyz[1];
            this.z=xyz[2];
        } else throw new IllegalArgumentException("Array should be 3 elements length");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}