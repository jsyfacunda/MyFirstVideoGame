package firstPackage;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ares on 10/18/2016.
 */
//HUD = heads up display
public class HUD {

    public static int HEALTH = 100;
    private int greenValue = 255;
    private int score = 0;
    private int level = 1;

    public void tick(){
        
        HEALTH = Game.clampHealth(HEALTH, 0 , 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = HEALTH*2;

        score++;
    }

    public void render(Graphics g){
        //color and bounds of health bar background
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        //color and bounds of health
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, HEALTH*2 + 2, 32);
        //color of bounds and bounds of health bar
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        //display score and level
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);

    }

    public static void lowerHealth(int x){
        HEALTH = HEALTH - x;
        if(HEALTH <= 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            }catch (Exception e){}
            System.exit(0);
        }
    }

    public int getScore(){return score;}
    public int getLevel(){return level;}
    public void setScore(int score){this.score = score;}
    public void setLevel(int level) {this.level = level;}


}
