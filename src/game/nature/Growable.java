package game.nature;

import edu.monash.fit2099.engine.positions.Location;

/**
 * interface method implemented by tree subclasses.
 */
public interface Growable {
    /** This is the method to advance the growth of a growable object
     *
     * @param location of the growable object
     */
    public void grow(Location location);

}
