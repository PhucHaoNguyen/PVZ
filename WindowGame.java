/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:  the GameWindow class represents the main game window and handles 
    the initialization of the game components, including the game panel, plant cards, and menu screen.
*/
import javax.swing.*;
import java.awt.event.ActionEvent;

public class WindowGame extends JFrame {

    enum PlantType{
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }
    
    public WindowGame(){
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37,80);
        sun.setSize(60,20);

        PanelGame gp = new PanelGame(sun);
        gp.setLocation(0,0);
        getLayeredPane().add(gp,Integer.valueOf(0));
        
        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110,8);
        sunflower.setAction((ActionEvent e) -> {
            gp.activePlanting = PlantType.Sunflower;
        });
        getLayeredPane().add(sunflower,Integer.valueOf(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175,8);
        peashooter.setAction((ActionEvent e) -> {
            gp.activePlanting = PlantType.Peashooter;
        });
        getLayeredPane().add(peashooter,Integer.valueOf(3));

        PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
        freezepeashooter.setLocation(240,8);
        freezepeashooter.setAction((ActionEvent e) -> {
            gp.activePlanting = PlantType.FreezePeashooter;
        });
        getLayeredPane().add(freezepeashooter,Integer.valueOf(3));



        getLayeredPane().add(sun,Integer.valueOf(2));
        setResizable(false);
        setVisible(true);
    }
    public WindowGame(boolean b) {
        Menu menu = new Menu();
        menu.setLocation(0,0);
        setSize(1000,752);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu,Integer.valueOf(0));
        menu.repaint();
        setResizable(false);
        setVisible(true);
    }
    static WindowGame a;
    public static void begin() {
        a.dispose();
       a = new WindowGame();
    }
    public static void main(String[] args) {
          a = new WindowGame(true);
    }

}
