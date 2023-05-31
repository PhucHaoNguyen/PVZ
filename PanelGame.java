/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:  The GamePanel class represents the core functionality of the game, 
    including rendering graphics, managing game elements, and handling user interactions.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class PanelGame extends JLayeredPane implements MouseMotionListener {



    Image normalZombieImage;
    Image coneHeadZombieImage;
    Collider[] colliders;
    


    Timer redrawTime;
    Timer advancerTime;
    Timer sunProduce;
    Timer zombieProduce;
    JLabel sunboard;
    Image bgImage;
    Image peashooterImage;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;
    ArrayList<ArrayList<Zombie>> laneZombie;
    ArrayList<ArrayList<Pea>> lanePea;
    ArrayList<Sun> activeSun;

    WindowGame.PlantType activePlanting = WindowGame.PlantType.None;

    int mouseX , mouseY;

    private int sunScore;

    public int getSun() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunboard.setText(String.valueOf(sunScore));
    }

    public PanelGame(JLabel sunScoreboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunboard = sunScoreboard;
        setSunScore(150);  //pool avalie

        bgImage  = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peashooterImage = new ImageIcon(this.getClass().getResource("images/plants/peashooter.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("images/plants/freezepeashooter.gif")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();

        normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie1.png")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie2.png")).getImage();

        laneZombie = new ArrayList<>();// 5 row
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());

        lanePea = new ArrayList<>();// 5 row
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>()); 
        lanePea.add(new ArrayList<>());

        //display and manage Collider objects within game's graphical user interface,
        //setting up initial layout and behavior
        colliders = new Collider[45];
        for (int b = 0; b < 45; b++) {
            Collider a = new Collider();
            a.setLocation(44 + (b%9)*100,109 + (b/9)*120);//used to position the colliders in a grid-like pattern
            a.setAction(new PlantActionListener((b%9),(b/9)));
            colliders[b] = a;
            add(a,0);// adds object to UI component with a specific layout or arrangement
        }

        activeSun = new ArrayList<>();
        //redrawn graphical component, updated the screen
        redrawTime = new Timer(25,(ActionEvent e) -> {
            repaint();
        });
        redrawTime.start();

        advancerTime = new Timer(60,(ActionEvent e) -> advance());
        advancerTime.start();
        // time delay to spawn sun
        sunProduce = new Timer(5000,(ActionEvent e) -> {
            Random rnd = new Random();
            // generate random x-coordinate from 100-900 pixels, random time for sun to stay on screen 200-500 mili
            Sun sta = new Sun(this,rnd.nextInt(800)+100,0,rnd.nextInt(300)+200);
            activeSun.add(sta);
            // add to the game panel with layer 1 to display above some other component
            add(sta,1);
        });
        sunProduce.start();
        // time delay to spawn zombies and random position
        zombieProduce = new Timer(7000,(ActionEvent e) -> {
            Random rnd = new Random();
            LevelData lvl = new LevelData();
            String [] Level = lvl.level[Integer.parseInt(lvl.Lvl)-1];
            int [][] LevelValue = lvl.levelNum[Integer.parseInt(lvl.Lvl)-1];
            int l = rnd.nextInt(5);//0-4 land zombie
            int t = rnd.nextInt(100);// determine the type of zombie
            Zombie z = null;
            for(int i = 0;i<LevelValue.length;i++) {
                if(t>=LevelValue[i][0]&&t<=LevelValue[i][1]) {
                    z = Zombie.getZombie(Level[i],PanelGame.this,l);
                }
            }
            laneZombie.get(l).add(z);
        });
        zombieProduce.start();

    }

    // recursion: update the position and actions of zombies, peas, and suns in game
    // processing the game state forward
    private void advance(){
        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombie.get(i)){
                z.advance();
            }

            for (int j = 0; j < lanePea.get(i).size(); j++) {
                Pea p = lanePea.get(i).get(j);
                p.advance();
            }

        }

        for (int i = 0; i < activeSun.size() ; i++) {
            activeSun.get(i).advance();
        }
    }

    // handles the visual rendering of the game panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,null);

        //Draw Plants
        for (int k = 0; k < 45; k++) {
            Collider c = colliders[k];
            if(c.assignPlant != null){
                Plant p = c.assignPlant;
                if(p instanceof Peashooter){
                    g.drawImage(peashooterImage,60 + (k%9)*100,129 + (k/9)*120,null);
                }
                if(p instanceof freezePeashooter){
                    g.drawImage(freezePeashooterImage,60 + (k%9)*100,129 + (k/9)*120,null);
                }
                if(p instanceof Sunflower){
                    g.drawImage(sunflowerImage,60 + (k%9)*100,129 + (k/9)*120,null);
                }
            }
        }

        for (int k = 0; k < 5 ; k++) {
            for(Zombie z : laneZombie.get(k)){
                if(z instanceof NormalZom){
                    g.drawImage(normalZombieImage,z.posX,109+(k*120),null);
                }else if(z instanceof ConeHeadZom){
                    g.drawImage(coneHeadZombieImage,z.posX,109+(k*120),null);
                }
            }

            for (int j = 0; j < lanePea.get(k).size(); j++) {
                Pea p = lanePea.get(k).get(j);
                if(p instanceof freezePea){
                    g.drawImage(freezePeaImage, p.posiX, 130 + (k * 120), null);
                }else {
                    g.drawImage(peaImage, p.posiX, 130 + (k * 120), null);
                }
            }

        }
    }

    //plant placement, check current selected plant and place
    // condition: enough sun score
    class PlantActionListener implements ActionListener {

        int x,y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(activePlanting == WindowGame.PlantType.Sunflower){
                if(getSun()>=50) {
                    colliders[x + y * 9].setPlant(new Sunflower(PanelGame.this, x, y));
                    setSunScore(getSun()-50);
                }
            }
            if(activePlanting == WindowGame.PlantType.Peashooter){
                if(getSun() >= 100) {
                    colliders[x + y * 9].setPlant(new Peashooter(PanelGame.this, x, y));
                    setSunScore(getSun()-100);
                }
            }

            if(activePlanting == WindowGame.PlantType.FreezePeashooter){
                if(getSun() >= 175) {
                    colliders[x + y * 9].setPlant(new freezePeashooter(PanelGame.this, x, y));
                    setSunScore(getSun()-175);
                }
            }
            activePlanting = WindowGame.PlantType.None;
        }
    }

    // monitor the mouse
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    //check current level and when to go to higher level
    static int progress = 0;
    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if(progress>=150) {
           if("1".equals(LevelData.Lvl)) {
            JOptionPane.showMessageDialog(null,"Level 1 Completed !" + '\n' + "Press OK to start next Level");
            WindowGame.a.dispose();// disposes current level 1
            LevelData.write("2");
            WindowGame.a = new WindowGame();
            }  else {
               JOptionPane.showMessageDialog(null,"Level 2 Completed !" + '\n');
               LevelData.write("1");
               System.exit(0);
           }
           progress = 0;
        }
    }
}
