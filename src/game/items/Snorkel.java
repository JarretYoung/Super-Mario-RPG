package game.items;

import game.Status;

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
