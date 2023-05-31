/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:  This class represents a projectile in the game that freezes zombies upon collision.
    It extends the Pea class, initializes the parent GamePanel, lane, and starting X-coordinate
    in the constructor, and overrides the advance() method to handle collision detection, damage calculation,
    and removal of defeated zombies and peas from the game.
*/
import java.awt.*;

public class freezePea extends Pea {

    public freezePea(PanelGame first,int lane,int beginX){
        super(first,lane,beginX);
    }

    @Override
    public void advance(){
        Rectangle pRect = new Rectangle(posiX,130+myLane*120,28,28);
        for (int i = 0; i < gp.laneZombie.get(myLane).size(); i++) {
            Zombie z = gp.laneZombie.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 300;
                z.slow();
                boolean exit = false;
                if(z.health < 0){
                    System.out.println("ZOMBIE DEAD");
                    PanelGame.setProgress(10);
                    gp.laneZombie.get(myLane).remove(i);
                    exit = true;
                }
                gp.lanePea.get(myLane).remove(this);
                if(exit) break;
            }
        }
        posiX += 15;
    }

}
