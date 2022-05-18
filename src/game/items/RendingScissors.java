package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actors.CurrencyCollector;

/**
 * Wrench class that extends abstract class SpecialItem
 * Capabilities: Lets consumer hit enemies with more damage and break Koopa shells
 *
 * @author Garret Yong Shern Min
 * @version 1.0 30/4/2022
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

        super("Rending Scissors", 'Y', 40, "snips", 85);
        this.addCapability(Status.CRIPPLE_WEAPON);
        this.setChanceToCripple(60);
    }


    @Override
    public int getChanceToCripple() {
        return chanceToCripple;
    }

    @Override
    public void setChanceToCripple(int chance) {

    }
}
