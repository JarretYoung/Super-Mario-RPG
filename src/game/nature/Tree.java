package game.nature;


import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;
import game.Status;
import game.surfaces.Dirt;
import game.surfaces.HighGround;

import java.util.Random;

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
     * @param displayChar is the character to be displayed on the map
     * @param name tree name
     * @param jumpSuccessRate tree jump Success Rate
     * @param jumpDamagePoints  tree jump Success Rate
     */

    public Tree(char displayChar, String name,  int jumpSuccessRate, int jumpDamagePoints) {
        super(displayChar, name, jumpSuccessRate,jumpDamagePoints);
        this.age = 0; // all new trees start with age 0

        this.registerInstance();
    }

    @Override
    public void tick(Location location) {
        Random rand = new Random();
        super.tick(location);
        // If reset is queued then convert this instance of Tree into an instance of Dirt, with 50% chance
        if((this.hasCapability(Status.RESET_QUEUED)) && ( rand.nextInt(100) < 50 ) )  {
            location.setGround(new Dirt());
        }
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

//    /**
//     * Method to queue a reset for all children extending the Tree class
//     */
//    @Override
//    public void resetInstance() {
//        this.addCapability(Status.RESET_QUEUED);
//    }
}
