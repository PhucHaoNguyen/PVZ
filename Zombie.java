/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:   This class represents a generic zombie. It creates 
    the movement, collision detection, and interaction with plants. It also provides 
    a method to create specific types of zombies based on the given type parameter.
*/
import javax.swing.*;

public class Zombie {

    public int health = 1000;
    public int speed = 1;

    private PanelGame gp;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;

    public Zombie(PanelGame first,int lane){
        this.gp = first;
        myLane = lane;
    }

    public void advance(){
        if(isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp.colliders[i].assignPlant != null && gp.colliders[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.colliders[i];
                }
            }
            if (!isCollides) {
                if(slowInt>0){
                    if(slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                }else {
                    posX -= 1;
                }
            } else {
                collided.assignPlant.health -= 10;
                if (collided.assignPlant.health < 0) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp,"ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                WindowGame.a.dispose();
                WindowGame.a = new WindowGame();
            }
        }
    }

    int slowInt = 0;
    public void slow(){
        slowInt = 1000;
    }
    public static Zombie getZombie(String type,PanelGame parent, int lane) {
        Zombie z = new Zombie(parent,lane);
       switch(type) {
           case "NormalZombie" : z = new NormalZom(parent,lane);
                                 break;
           case "ConeHeadZombie" : z = new ConeHeadZom(parent,lane);
                                 break;
    }
       return z;
    }

}
