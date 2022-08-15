import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Scenario extends JFrame implements KeyListener{

    private static Snake snake;
    private static Fruit fruit;
    
    public Scenario() {
        setSize(500,500);
        setLocationRelativeTo(null);
        setTitle("Snake");
        setUndecorated(true);
        setResizable(true);
        setLayout(null);
        init();
        setVisible(true);
    }

    private void init() {
        //Inserindo a cobra
        snake = new Snake(getWidth(), getHeight(), 0, 0);
        add(snake.getHead());
        addBodySnake();

        fruit = new Fruit(getWidth(), getHeight(), 50, 50);
        add(fruit);
        snake.setFruit(fruit.getX(), fruit.getY());

        snake.crawl();
        addKeyListener(snake);
        addKeyListener(this);

        
    }

    public void addBodySnake(){
        for (Body element : snake.getBody()) {
            add(element);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {  
        //Gatilho para a inserção da fruta
        new Thread(new Runnable() {
            public void run() {
                try {
                    while(snake.getCrawl()){
                        addBodySnake();
                        if(fruit.isHidden()){

                        }
                        Thread.sleep(snake.getSpeed());
                    }  
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void keyReleased(KeyEvent e) {  
    }

    public static void hiddenFruit(){
        fruit.setVisible(false);
    }

}
