package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actors.Koopa;
import game.items.Wrench;

import java.util.Random;

public class BreakShellAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Koopa target;

    /**
     * The direction of incoming attack. (Subject to removal)
     */
    protected String direction;


    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public BreakShellAction(Koopa target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();

        String result = "";

//        if (weapon instanceof Wrench ) {
//
//        }
        if (actor.hasCapability(Status.HAS_WRENCH)) {
            target.destroyShell();
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
            result = target + " shell has been destroyed";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroy " + target + " shell";
    }
}

