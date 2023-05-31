/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:   The Peashooter class extends the Plant class and represents a 
    specific type of plant in the game. It has a shootTimer object of type Timer 
    that controls the shooting behavior of the peashooter.
*/
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Peashooter extends Plant {

    public Timer shootTimer;


    public Peashooter(PanelGame first,int a,int b) {
        super(first,a,b);
        shootTimer = new Timer(2100,(ActionEvent e) -> {
            if(gp.laneZombie.get(b).size() > 0) {
                gp.lanePea.get(b).add(new Pea(gp, b, 103 + this.a * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}
