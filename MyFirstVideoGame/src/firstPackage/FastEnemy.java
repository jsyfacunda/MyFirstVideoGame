package firstPackage;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * Created by Ares on 10/18/2016.
 */
public class FastEnemy extends GameObject{

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        speedX = 10;
        speedY = 10;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }

    public void tick(){
        x += speedX;
        y += speedY;

        if(y <= 0 || y >= Game.HEIGHT - 32)
            speedY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16)
            speedX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(x, y, 16, 16);
    }
}

