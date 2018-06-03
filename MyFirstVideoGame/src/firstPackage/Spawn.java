package firstPackage;

import java.util.Random;


/**
 * Created by Ares on 10/18/2016.
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        //how often do you want enemies to spawn
        if(scoreKeep >= 500) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            //spawn new enemy each level
            if(hud.getLevel() < 4)
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            else if(hud.getLevel() >= 4)
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));

        }
    }
}
