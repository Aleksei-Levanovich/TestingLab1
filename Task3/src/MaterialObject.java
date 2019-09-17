public class MaterialObject {
    private Double x;
    private Double y;
    private Double z;
    public MaterialObject(Double x, Double y, Double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public MaterialObject(Double[] xyz){
        if (xyz.length==3){
            this.x=xyz[0];
            this.y=xyz[1];
            this.z=xyz[2];
        }
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
