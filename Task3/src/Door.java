public class Door extends MaterialObject {

    private boolean isOpened;
    private int strengthToOpen;

    public void checkStrength(int strengthToOpen){
        if (strengthToOpen>=0) {
            this.strengthToOpen=strengthToOpen;
        } else throw new IllegalStateException("Strength to open should be more or equal to 0");
    }

    public void setIsOpened(boolean isOpened){
        this.isOpened=isOpened;
    }

    public boolean getIsOpened(){
        return this.isOpened;
    }

    public int getStrengthToOpen(){
        return this.strengthToOpen;
    }

    public Door(boolean isOpened, int strengthToOpen, double x, double y, double z){
        super(x,y,z);
        this.isOpened=isOpened;
        checkStrength(strengthToOpen);
    }

    public Door(boolean isOpened, int strengthToOpen, double[] xyz){
        super(xyz);
        this.isOpened=isOpened;
        checkStrength(strengthToOpen);
    }
}