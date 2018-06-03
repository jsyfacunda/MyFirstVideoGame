package firstPackage;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Created by Ares on 10/16/2016.
 */


public class Window extends Canvas{

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        //sets sizes- max and min
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //makes exit button work
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //makes not able to resize window
        frame.setResizable(false);
        //Window would start at top left without
        frame.setLocationRelativeTo(null);
        //adding game class into frame
        frame.add(game);
        //letting you see class
        frame.setVisible(true);
        //run start method
        game.start();
    }
}