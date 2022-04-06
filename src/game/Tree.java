package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Trees have 3 stages
 * Tress grow into new stage every 10 turns
 *
 */

public abstract class Tree extends Ground {



    /**
     * indicates age of Tree
     */
    private int age;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
    }

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar, int age) {
        super(displayChar);
        this.age = age;
    }

    /**
     * Getter for age
     * @return overall age of Tree
     */
    public int getAge() {
        return age;
    }

    /**
     * ticks Trees and check age for any growth
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;
        checkAction();

    }

    /**
     * Checks all action for each subclasses of Tree as specified
     */
    abstract public void checkAction();

    /**
     * Checks if subclasses of Tree will grow or wither(for Mature only)
     */
    abstract public void checkGrowth();


}
