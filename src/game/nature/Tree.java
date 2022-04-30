package game.nature;


import game.surfaces.HighGround;

public abstract class Tree extends HighGround {


    private int age;


    public Tree(char displayChar, String name,  int jumpSuccessRate, int jumpDamagePoints) {
        super(displayChar, name, jumpSuccessRate,jumpDamagePoints);
        this.age = 0; // all new trees start with age 0
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
