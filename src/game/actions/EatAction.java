package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.ConsumableItem;
import game.items.SpecialItem;

/**
 * Consume action consumes special items
 */
public class EatAction extends Action {
    /**
     * Item consumed by actor
     */
    private ConsumableItem itemConsumed;


    /**
     * Constructor
     * @param item Item consumed by actor
     */
    public EatAction(ConsumableItem item)
    {
        this.itemConsumed = item;
    }

    /**
     * Execution for ConsumeAction
     * @param actor actor consumes special item
     * @param map The map the actor is on.
     * @return message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        if(map.locationOf(actor).getItems().contains(itemConsumed)) {
            ret = itemConsumed.eatenFromGround(actor);
            Location location = map.locationOf(actor);
            location.removeItem(itemConsumed);
        } else if(actor.getInventory().contains(itemConsumed)) {
            ret = itemConsumed.eatenFromInventory(actor);
        }
        return ret;
    }

    /**
     * Menu description shown to player
     * @param actor The actor performing the action.
     * @return String of option to perform action
     */
    public String menuDescription(Actor actor) {
        return actor + " eats " + itemConsumed;
    }
}
