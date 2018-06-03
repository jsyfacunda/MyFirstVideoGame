package firstPackage;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 * Created by Ares on 10/16/2016.
 */
public class Game extends Canvas implements Runnable{
    //variables for width and height
    public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();


        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        //If you want multiple enemies
        //for(int i = 0; i < 10; i++)
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy, handler));
        //fast enemy spawn at beginning
        //handler.addObject(new FastEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.FastEnemy, handler));

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //set color of background
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        hud.render(g);


        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max){
        if(var >= max) {
            HUD hudObj = new HUD();
            return var = max;
        } else if(var <= min) {
            HUD hudObj = new HUD();
            return var = min;
        } else
            return var;
    }

    public static int clampHealth(int var, int min, int max){
        if(var >= max) {
            HUD hudObj = new HUD();
            hudObj.lowerHealth(1);
            return var = max;
        } else if(var <= min) {
            HUD hudObj = new HUD();
            hudObj.lowerHealth(1);
            return var = min;
        } else
            return var;
    }

    public static void main(String[] args){
        new Game();
    }
}