package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Buffable;
import game.items.Bottle;
import game.surfaces.Fountain;

/**
 * FillUpAction class that extends abstract class Action class
 * Usage: Is used to fill the bottle with the water of a given fountain
 *
 * @author Jastej Gill
 * @version 1.0 18/5/2022
 */
public class FillUpAction extends Action {

    /**
     * This is the fountain that is the bottle is filling up from
     */
    private Fountain fountain;

    /** Constructor for the FillUpAction
     *
     * @param fountain is the fountain that the bottle is filling up from
     */
    public FillUpAction(Fountain fountain){
        this.fountain = fountain;
    }

    /** Method run to execute this action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String which narrates what has occurred during execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle bottle = null;
        String ret = "";
        if(actor.hasCapability(Status.HAS_BOTTLE) && !actor.hasCapability(Status.HAS_FULL_BOTTLE)) {
            for(int i = 0; i < actor.getInventory().size(); i++){
                if (actor.getInventory().get(i).hasCapability(Status.HAS_BOTTLE))
                    bottle = (Bottle) actor.getInventory().get(i);
            }
            ret = fountain.FilledUpFrom((Buffable) actor, bottle);
        }
        return ret;
    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param actor The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills up bottle with " + fountain.peek();
    }
}
