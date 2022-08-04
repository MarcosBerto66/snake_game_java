import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Scenario extends JFrame implements KeyListener{

    private Snake snake;
    private Fruit fruit;
    
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
        for (Body element : snake.getBody()) {
            add(element);
        }
        snake.crawl();
        addKeyListener(snake);
        addKeyListener(this);

        fruit = new Fruit(snake.getHead().getWidth(), snake.getHead().getHeight(), 50, 50);
        add(fruit);
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
                        if(fruit.getLocation().equals(snake.getHead().getLocation())){
                            snake.addNewBody();
                        }
                        if(fruit.isHidden()){

                        }
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
}
