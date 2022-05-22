package game.items;

import game.Status;

/**
 * Snorkel class that extends abstract class SpecialItem
 * Capabilities: Is an item to be sold by a merchant to allow swimming underwater
 *
 * @author Jastej Gill
 * @version 2.0 22/5/2022
 */
public class Snorkel extends SpecialItem{
    /***
     * Constructor.
     */
    public Snorkel() {
        super("Snorke;", '8', true);
        this.addCapability(Status.AMPHIBIOUS);
        this.setValue(500);
    }
}
