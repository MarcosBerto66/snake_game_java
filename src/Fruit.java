public class Fruit extends Body{

    private boolean hidden = false;
    
    public Fruit(){
        super();
    }

    public Fruit(int width, int height, int x, int y){
        super(width, height, x, y);
    }

    public boolean isHidden() {
        return this.hidden;
    }
}
