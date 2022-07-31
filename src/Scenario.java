import javax.swing.JFrame;

public class Scenario extends JFrame{

    private Snake snake;
    
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
        snake = new Snake(getWidth(), getHeight(), 0, 0);
        add(snake.getHead());
        for (Body element : snake.getBody()) {
            add(element);
        }
        snake.crawl();
        addKeyListener(snake);
    }
}
