package game.nature;


import game.surfaces.HighGround;

/**
 * Tree parent class to sprout, sapling, mature
 */
public abstract class Tree extends HighGround {

    /**
     * Age of tree, used for tree growth
     */
    private int age;

    /**
     * constructor for tree
     * @param displayChar
     * @param name tree name
     * @param jumpSuccessRate tree jump Success Rate
     * @param jumpDamagePoints  tree jump Success Rate
     */

    public Tree(char displayChar, String name,  int jumpSuccessRate, int jumpDamagePoints) {
        super(displayChar, name, jumpSuccessRate,jumpDamagePoints);
        this.age = 0; // all new trees start with age 0
    }

    /**
     * Getter for tree age
     * @return tree age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for tree age
     * @param age current age of tree
     */
    public void setAge(int age) {
        this.age = age;
    }


}
