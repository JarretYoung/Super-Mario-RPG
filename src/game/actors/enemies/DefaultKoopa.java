package game.actors.enemies;


import game.reset.Resettable;

public class DefaultKoopa extends Koopa implements Resettable {

    /**
     * Constructor.
     */
    public DefaultKoopa() {
        super("Koopa", 'K', 20);
        this.setHitPoints_active(100); //This the Koopa's hp when it is in an active state
    }
}