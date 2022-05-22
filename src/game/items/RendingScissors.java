package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * RendingScissors class that extends abstract class WeaponItem
 * Capabilities: Let's the user attack enemies (and potentially cripple them)
 *
 * @author Garret Yong Shern Min
 * @version 2.0 20/5/2022
 */
public class RendingScissors extends WeaponItem implements Crippleable{

    /**
     * Chance to cripple of RendingScissor
     */
    private int chanceToCripple;
    /**
     * Constructor.
     *
     */
    public RendingScissors() {

        super("Rending Scissors", '7', 40, "snips", 85);
        this.addCapability(Status.CRIPPLE_ATTACK);
        this.setChanceToCripple(15);
    }

    /**
     * Getter for the change of a weapon to cripple
     *
     * @return the chance for a weapon to cripple
     */
    @Override
    public int getChanceToCripple() {
        return chanceToCripple;
    }

    /** Mutator for the chance of a weapon to cripple
     *
     * @param chance out of 100% to cause crippling
     */
    @Override
    public void setChanceToCripple(int chance) {
        this.chanceToCripple = chance;
    }
}
