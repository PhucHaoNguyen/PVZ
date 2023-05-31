/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:  This class read and write the current level data to a file. 
    The LevelData class allows the game to store and retrieve the current level information, 
    making it possible to save and load the game progress.
*/
import java.io.*;
import java.util.logging.Logger;

public class LevelData {
    static String Lvl = "1";
   static String [][] level = {{"NormalZombie"},{"NormalZombie","ConeHeadZombie"}};
   static int [][][] levelNum = {{{0,99}},{{0,49},{50,99}}} ;
   public LevelData() {
       try {
           File f = new File("Level.vbhv");
           
           if(!f.exists()) {
               BufferedWriter write = new BufferedWriter(new FileWriter(f));
               write.write("1");
               write.close();
               Lvl = "1";
           } else {
               BufferedReader br = new BufferedReader(new FileReader(f));
               Lvl = br.readLine();
           }
       } catch (Exception ex) {
           
           
       }
   }
   public static void write(String lvl) {
       File f = new File("Level.vbhv");
        try {
            BufferedWriter action = new BufferedWriter(new FileWriter(f));
            action.write(lvl);
            action.close();
            Lvl = lvl;
        } catch (IOException ex) {
            Logger.getLogger(LevelData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
               
   }
}
