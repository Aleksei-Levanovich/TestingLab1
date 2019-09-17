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
    public Door(boolean isOpened, Integer strengthToOpen){
        this.isOpened=isOpened;
        this.strengthToOpen=strengthToOpen;
    }
}
