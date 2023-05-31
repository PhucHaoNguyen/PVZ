/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:   the Pea class represents a projectile. It has properties such as 
    posX (horizontal position), gp (reference to the parent GamePanel), and myLane 
    (the lane to which the pea belongs).
*/
import java.awt.*;

public class Pea {

    public int posiX;
    protected PanelGame gp;
    public int myLane;

    public Pea(PanelGame first,int lane,int beginX){
        this.gp = first;
        this.myLane = lane;
        posiX = beginX;
    }

    public void advance(){
        Rectangle pRect = new Rectangle(posiX,130+myLane*120,28,28);
        for (int i = 0; i < gp.laneZombie.get(myLane).size(); i++) {
            Zombie z = gp.laneZombie.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 300;
                boolean exit = false;
                if(z.health < 0){
                    System.out.println("ZOMBIE DEAD");
                    
                    gp.laneZombie.get(myLane).remove(i);
                    PanelGame.setProgress(10);
                    exit = true;
                }
                gp.lanePea.get(myLane).remove(this);
                if(exit) break;
            }
        }
        /*if(posX > 2000){
            gp.lanePeas.get(myLane).remove(this);
        }*/
        posiX += 15;
    }

}
