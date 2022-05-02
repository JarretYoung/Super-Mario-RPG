package game.currency;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.reset.Resettable;


/**
 * Coins
 * Capabilities: Picking up a coin should increase the Player's wallet balance. (have not implemented)
 *
 * @author Lup Hoong
 * @version 1.0 5/4/2022
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

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if (this.hasCapability(Status.RESET_QUEUED)) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }
}
