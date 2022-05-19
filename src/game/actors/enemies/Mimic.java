package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.actions.OpenMimicAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

public class Mimic extends Enemy{
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Mimic(String name, char displayChar, int hitPoints) {
        super("Mimic", 'R', 200);
        this.addCapability(Status.DORMANT);
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if ((this.hasCapability(Status.DORMANT))) {
            actions.add(new OpenMimicAction(this, direction));
        } else if (this.hasCapability(Status.ACTIVE)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.ACTIVE)) {
            this.setDisplayChar('{');
            this.getBehaviour().put(10, new WanderBehaviour());
            this.getBehaviour().put(9, new AttackBehaviour());
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean isConscious() {
        if (super.isConscious() == false) {
            // Uncomment this after creation of new weapon
//            this.addItemToInventory(new OversizedScissors);
        }
        return super.isConscious();
    }

    /** This method is used to assign a new intrinsic weapon to the Koopa
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "chomp");
    }
}