package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Buffable;
import game.items.ConsumableItem;
import game.items.water.MagicalWater;
import game.items.water.WaterContainer;
import game.surfaces.fountains.Fountain;

public class DrinkAction extends Action {
    /**
     * Item consumed by actor
     */
    private MagicalWater water;
    /**
     * Constructor
     * @param water water that the actor drinks
     */
    public DrinkAction(MagicalWater water)
    {
        this.water = water;
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
        if(actor instanceof Buffable)
            ret = water.drinked((Buffable) actor);
        return ret;
    }

    /**
     * Menu description shown to player
     * @param actor The actor performing the action.
     * @return String of option to perform action
     */
    public String menuDescription(Actor actor) {
        return actor + " drinks " + water;
    }
}
