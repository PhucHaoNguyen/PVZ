/*
    Name: 18
    + Nguyễn Phúc Hào - ITITUN21023
    + Nguyễn Mai Phương - ITDSIU20105
    + Trần Thế Phong - ITCSIU21215
    - Purpose:   This class is an abstract class that serves as a base class 
    for different types of plants in the game. It defines common properties and 
    behaviors that are shared among all plants.
*/
public abstract class Plant {

    public int health = 200;

    public int a;
    public int b;

    public PanelGame gp;


    public Plant(PanelGame first,int a,int b){
        gp = first;
        this.a = a;
        this.b = b;
    }

    public void stop(){}

}
