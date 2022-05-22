package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * GetItemAction class that extends abstract class Action class
 * Usage: It is used to get items from another actor
 *
 * @author Jastej Gill
 * @version 1.0 20/5/2022
 */
public class GetItemAction extends Action {
    /**
     * This is the owner of the item
     */
    private Actor owner;

    /**
     * This is the item to be received
     */
    private Item received;

    /** This is the constructor for the GetItemAction
     *
     * @param item is the item to be received
     * @param owner is the owner of the item to be received
     */
    public GetItemAction(Item item, Actor owner){
        this.received = item;
        this.owner = owner;
    }

    /** Method run to execute this action
     *
     * @param receiver The actor performing the action to receive the item
     * @param map The map the actor is on.
     * @return a String which narrates what has occurred during execution
     */
    @Override
    public String execute(Actor receiver, GameMap map) {
        String ret = "";
        if(owner.getInventory().contains(received)){
            receiver.addItemToInventory(received);
            owner.removeItemFromInventory(received);
            ret = receiver + " gets " + received + " from " + owner;
        }
        return ret;
    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param receiver The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor receiver) {
        return "Get " + received + " from " + owner;
    }
}
