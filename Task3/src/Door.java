public class Door extends MaterialObject {
    private boolean isOpened;
    private Integer strengthToOpen;
    public void setIsOpened(boolean isOpened){
        this.isOpened=isOpened;
    }
    public boolean getIsOpened(){
        return this.isOpened;
    }
    public Integer getStrengthToOpen(){
        return this.strengthToOpen;
    }
    public Door(boolean isOpened, Integer strengthToOpen, Double x, Double y, Double z){
        super(x,y,z);
        this.isOpened=isOpened;
        this.strengthToOpen=strengthToOpen;
    }
}
