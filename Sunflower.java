/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:   the Sunflower class represents a sunflower plant in the game. It extends 
    the Plant class and has a timer that periodically produces suns. When the timer fires, 
    it creates a new Sun object and adds it to the list of active suns in the game panel, 
    making it available for collection by the player.
*/
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Sunflower extends Plant {

    Timer sunProduceTimer;

    public Sunflower(PanelGame first,int a,int b) {
        super(first, a, b);
        sunProduceTimer = new Timer(15000,(ActionEvent e) -> {
            Sun sta = new Sun(gp,60 + a*100,110 + b*120,130 + b*120);
            gp.activeSun.add(sta);
            gp.add(sta,Integer.valueOf(1));
        });
        sunProduceTimer.start();
    }

}
