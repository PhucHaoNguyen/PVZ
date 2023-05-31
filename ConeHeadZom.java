/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:  The ConeHeadZombie class is a subclass of the Zombie class,
    representing a specific type of zombie with increased health. It initializes
    the parent GamePanel and lane using the superclass constructor and sets the health value to 1800.
*/

public class ConeHeadZom extends Zombie {

    public ConeHeadZom(PanelGame first,int lane){
        super(first,lane);
        health = 1900;
    }
}
