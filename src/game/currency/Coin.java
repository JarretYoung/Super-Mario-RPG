package game.currency;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.reset.Resettable;


/**
 * Coin class that extends abstract class currency
 * Capabilities: Picking up a coin should increase the Player's wallet balance.
 *
 * @author Lup Hoong
 * @version 1.0 21/4/2022
 */
public class Coin extends Currency implements Resettable {


    /**
     * Constructor
     * @param value of coin object
     */
    public Coin(int value) {
        super("coin", '$', value);
        this.registerInstance();
    }

    /**
     * Allows coin to be removed when reset is queued
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        // If reset is queued then remove this instance of coin from current location
        if (this.hasCapability(Status.RESET_QUEUED)) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Queues reset for coin
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }
}
