package firstPackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Ares on 10/18/2016.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler){
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;

    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W) {
                    tempObject.setSpeedY(-5);
                    keyDown[0] = true;
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setSpeedY(5);
                    keyDown[1] = true;
                } else if (key == KeyEvent.VK_A){
                    tempObject.setSpeedX(-5);
                    keyDown[2] = true;
                } else if(key == KeyEvent.VK_D) {
                tempObject.setSpeedX(5);
                    keyDown[3] = true;
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){
                //key events for player 1
                if(key == KeyEvent.VK_W)
                    keyDown[0] = false;
                    //tempObject.setSpeedY(0);
                else if(key == KeyEvent.VK_S)
                    keyDown[1] = false;
                    //tempObject.setSpeedY(0);
                else if(key == KeyEvent.VK_A)
                    keyDown[2] = false;
                    //tempObject.setSpeedX(0);
                else if(key == KeyEvent.VK_D)
                    keyDown[3] = false;
                    //tempObject.setSpeedX(0);
//vertical movement
                if(!keyDown[0] && !keyDown[1])
                    tempObject.setSpeedY(0);
//horizontal movement
                if(!keyDown[2] && !keyDown[3])
                    tempObject.setSpeedX(0);
            }
        }
    }

}
