package game.nature;

import game.HighGround;

public abstract class Tree extends HighGround  {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */

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
