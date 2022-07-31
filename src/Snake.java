import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake implements KeyListener{ //Esta classe gerencia o comportamento da cabeça e outros elementos que compõe o corpo
    
    private static int TOP = 38;
    private static int LEFT = 37;
    private static int RIGHT = 39;
    private static int BOTTOM = 40; //Códigos das teclas de movimentação

    private boolean top = false;
    private boolean left = false;
    private boolean right = false;
    private boolean bottom = false; //Status de movimento

    private int speed = 400; //Velocidade de movimentação
    private boolean crawl = true; //Status de inicio de jogo

    private Body head; //Cabeça
    private Body body[]; //Vetor de corpo

    private int width; 
    private int height; //Tamanho da tela/cenário

    private int last_x;
    private int last_y;

    public Snake(int width, int height, int x, int y){
        this.width = width;
        this.height = height;//Definindo o tamanho da cenário/tela

        head = new Body(width, height, x, y);
        body = new Body[7];
        for (int i = 0; i < body.length; i++) {
            body[i] = new Body(width, height, x, y);
        }//Definindo as características da cabeça e do corpo
    }

    public Body getHead(){
        return this.head;
    }

    public Body[] getBody(){
        return this.body;
    }

    private void crawlBody(){
        for(Body element : body){
            int aux_x = element.getX();
            int aux_y = element.getY();
            element.setLocation(last_x, last_y);
            last_x = aux_x;
            last_y = aux_y;
        }
    }

    public void crawl() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while(crawl){
                        if(top && head.getY() > 0){
                            head.setLocation(head.getX(), head.getY() - head.getHeight());
                        }else if(left == true && head.getX() > 0) {
							head.setLocation(head.getX() - head.getWidth(), head.getY());
						}else if(right == true && head.getX() < width) {
							head.setLocation(head.getX() + head.getWidth(), head.getY());
						}else if(bottom == true && head.getY() < height) {
							head.setLocation(head.getX(), head.getY() + head.getHeight());
						}else if(head.getX() != 0 && head.getY() != 0){
							JOptionPane.showMessageDialog(null, "Perdeu!!!");
							crawl = false;
						}
                        last_x = head.getX();
                        last_y = head.getY();
                        Thread.sleep(speed);
                        crawlBody();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {  
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
		if(i == TOP) {
			top = true;
			bottom = false;
			left = false;
			right = false;
		}else if(i == BOTTOM) {
			top = false;
			bottom = true;
			left = false;
			right = false;
		}else if(i == LEFT) {
			top = false;
			bottom = false;
			left = true;
			right = false;
		}else if(i == RIGHT) {
			top = false;
			bottom = false;
			left = false;
			right = true;
		}   
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
