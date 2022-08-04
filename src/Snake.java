import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Snake implements KeyListener{ //Esta classe gerencia o comportamento da cabeça e outros elementos que compõe o corpo
    
    private static int TOP = 38;
    private static int LEFT = 37;
    private static int RIGHT = 39;
    private static int BOTTOM = 40; //Códigos das teclas de movimentação

    private boolean top = false;
    private boolean left = false;
    private boolean right = false;
    private boolean bottom = false; //Status de movimento

    private int speed = 300; //Velocidade de movimentação
    private boolean crawl = true; //Status de inicio de jogo
    private boolean firstAction = true;

    private Body head; //Cabeça
    private List<Body> body = new ArrayList<>(); //Vetor de corpo

    private int width; 
    private int height; //Tamanho da tela/cenário

    private int last_x;
    private int last_y;

    public Snake(int width, int height, int x, int y){
        this.width = width;
        this.height = height;//Definindo o tamanho da cenário/tela

        head = new Body(width, height, x, y);
        for (int i = 0; i < 4; i++) {
            Body e = new Body(width, height, x, y);
            body.add(e);
        }//Definindo as características da cabeça e do corpo
    }

    public Body getHead(){
        return this.head;
    }

    public List<Body> getBody(){
        return this.body;
    }

    public boolean getCrawl(){
        return this.crawl;
    }

    private void crawlBody(){
        for(Body element : body){
            int aux_x = element.getX();
            int aux_y = element.getY();
            element.setLocation(last_x, last_y);
            last_x = aux_x;
            last_y = aux_y;//Alterando a posição de cada parte do corpo, pela última posição registrada
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
						}else if(right == true && head.getX() < (width - head.getWidth())) {
							head.setLocation(head.getX() + head.getWidth(), head.getY());
						}else if(bottom == true && head.getY() < (height - head.getHeight())) {
							head.setLocation(head.getX(), head.getY() + head.getHeight());
						}else{
                            gameOver();
						}
                        last_x = head.getX();
                        last_y = head.getY();
                        Thread.sleep(speed);
                        if(crawl){
                            crawlBody();
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public void gameOver(){
        if((((head.getX() + head.getWidth()) > width) || ((head.getX() - head.getWidth()) < 0) || ((head.getY() + head.getHeight()) > height) || ((head.getY() - head.getHeight()) < 0)) && firstAction == false){
            JOptionPane.showMessageDialog(null, "Perdeu!!!");
            crawl = false;
        }
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
            if(firstAction == true){
                firstAction = false;
            }
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
            if(firstAction == true){
                firstAction = false;
            }
		}   
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void addNewBody() {
        Body e = new Body(width, height, last_x, last_y);
    }
}
