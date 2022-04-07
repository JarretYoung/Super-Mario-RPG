package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * @author Lup Hoong
 * @version 1.0 5/4/2022
 * comments: converted Tree into abstract Class
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
     * (eg
     * sprout might grow and spawn Goomba,
     * sampling might grow and drop coin,
     * mature might wither, spawn Koopa, grow new sprout randomlu)
     */
    abstract public void checkAction();

    /**
     * Checks if subclasses of Tree will grow or wither(for Mature only)
     */
    abstract public void checkGrowth();


}
