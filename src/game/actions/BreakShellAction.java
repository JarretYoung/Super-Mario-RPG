package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.enemies.Enemy;

/** This class is used to destroy the shell of the Koopa
 *
 * @author Garret Yong Shern Min
 */
public class BreakShellAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Enemy target;

    /**
     * The direction of incoming attack. (Subject to removal)
     */
    protected String direction;


    /** This is the constructor for the BreakShellAction
     *
     * @param target is the target of the breakShellAction
     * @param direction is the direction that the target is at
     */
    public BreakShellAction(Enemy target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /** Method run to execute this action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String which narrates what has occurred during execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {


        String result = "";

        if (actor.hasCapability(Status.HAS_WRENCH)) {
            target.destroyShell();
        }

        else if(!actor.hasCapability(Status.HAS_WRENCH)) {
            System.out.println("Need a wrench to break Koopa's shell!");
        }

        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);

        }

        return result;
    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param actor The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroy " + target + " shell";
    }
}
