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
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

public class PiranhaPlant extends Enemy implements Resettable {
    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("PiranhaPlant", 'Y', 150);
        this.getBehaviour().remove(10);
    }

    /**
     * Figure out what to do next.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If reset is queued then remove this instance of enemy from this location
        if (this.hasCapability(Status.RESET_QUEUED)) {
            this.increaseMaxHp(50);
            this.heal(this.getMaxHp());

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }

        for(game.behaviours.Behaviour Behaviour : this.getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    /** This method is used to assign a new intrinsic weapon to the PiranhaPlant
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
