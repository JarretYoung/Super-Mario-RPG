package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.actions.IncognitoAction;
import game.actions.OpenMimicAction;
import game.behaviours.*;
import game.items.RendingScissors;

public class Mimic extends Enemy{


    /**
     * Constructor.
     */
    public Mimic() {
        super("Mimic", 'G', 200);

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
            this.setDisplayChar('R');
            this.getBehaviour().put(10, new WanderBehaviour());
            this.getBehaviour().put(8, new AttackBehaviour());
            for (Exit exit : map.locationOf(this).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                    }
                }
            }
        }

        if (!this.isConscious()){
            map.removeActor(this);
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new IncognitoAction();
    }

    @Override
    public boolean isConscious() {
        if (super.isConscious() == false) {
            this.addItemToInventory(new RendingScissors());
        }
        return super.isConscious();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "chomp");
    }

}
