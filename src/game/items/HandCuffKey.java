package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.EndGameAction;

import java.util.List;

/**
 * This is the Key to free Princess Key and end the game
 * It is initially in Bowsers inventory. It is dropped when Bowser dies.
 * Pick up this Key and talk to Princess Peach to end the game.
 *
 * @author Clarissa Low Lup Hoong
 * @version 1.0 20/5/2022
 */
public class HandCuffKey extends Item {


    /***
     * Constructor.
     */
    public HandCuffKey() {
        super("Key to Princess Peach's Handcuffs", 'k', true);
        addCapability(Status.FINAL_BOSS_CLEARED);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
    }
}
