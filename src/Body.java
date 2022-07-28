import java.awt.Color;

import javax.swing.JLabel;

public class Body extends JLabel{

    public Body(int width, int height, int x, int y){
        setSize(width/20, height/20);
        setLocation(x, y);
        setOpaque(true);
        setBackground(Color.BLACK);
    }

}
