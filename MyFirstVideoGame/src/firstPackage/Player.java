package firstPackage;
import java.awt.*;
import java.util.Random;
/**
 * Created by Ares on 10/17/2016.
 */
public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

    }

    //size of player
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }

    public void tick(){
        x += speedX;
        y += speedY;

        x = Game.clampHealth(x, 0, Game.WIDTH - 35);
        y = Game.clampHealth(y, 0, Game.HEIGHT - 60);

        //gives player a trail
        //handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 32, 32, 0.09f, handler));


        collision();

    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.BasicEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.lowerHealth(4);
                }
            }

            if(tempObject.getID() == ID.FastEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.lowerHealth(3);
                }
            }

            if(tempObject.getID() == ID.SmartEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.lowerHealth(4);
                }
            }
        }
    }

    public void render(Graphics g){

        //This makes the rect around player that shows collisions
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(getBounds());

        //gives player color (don't need with ^^)
        //g.setColor(Color.green);
        //g.fillRect(x, y, 32, 32);
    }



}
