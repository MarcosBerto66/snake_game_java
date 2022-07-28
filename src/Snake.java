import javax.swing.JOptionPane;

public class Snake {
    
    private static int TOP = 38;
    private static int LEFT = 37;
    private static int RIGHT = 39;
    private static int BOTTOM = 40;

    private boolean top = false;
    private boolean left = false;
    private boolean right = false;
    private boolean bottom = false;

    private int speed = 400;
    private boolean crawl = true; 

    private Body head;

    public Snake(int width, int height, int x, int y){
        head = new Body(width, height, x, y);
    }

    private void crawl() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while(crawl){
                        if(top && head.getY() > 0){
                            head.setLocation(head.getX(), head.getY() - getHeight());
                        }else if(left == true && head.getX() > 0) {
							head.setLocation(head.getX() - getWidth(), head.getY());
						}else if(right == true && head.getX() < width) {
							head.setLocation(head.getX() + getWidth(), head.getY());
						}else if(bottom == true && head.getY() < height) {
							head.setLocation(head.getX(), head.getY() + getHeight());
						}else if(head.getX() != 0 && head.getY() != 0){
							JOptionPane.showMessageDialog(null, "Perdeu!!!");
							crawl = false;
						}
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
