/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:   This class provides a custom panel with an image representing a plant card. 
    It allows setting an action listener that can be triggered when the card is clicked or activated. 
    The specific functionality associated with the action listener is defined externally when using this class.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlantCard extends JPanel implements MouseListener {

    Image img;
    ActionListener actions;

    public PlantCard(Image img){
        setSize(64,90);
        this.img = img;
        addMouseListener(this);
    }

    public void setAction(ActionListener action1){
        this.actions = action1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(actions != null){
            actions.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
