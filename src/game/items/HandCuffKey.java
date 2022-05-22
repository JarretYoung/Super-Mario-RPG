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
 * Syringe class that extends abstract class SpecialItem
 * Capabilities: Makes consumer heal over a period of time
 *
 * @author Lup Hoong
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

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
    }
}
