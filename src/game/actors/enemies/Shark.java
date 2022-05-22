package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.reset.Resettable;

public class Shark extends Enemy{
    /**
     * Constructor.
     *
     */
    public Shark() {
        super("Shark", '>', 15);
        this.addCapability(Status.SWIMMABLE_ENEMY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If reset is queued then remove this instance of enemy from this location
        if (this.hasCapability(Status.RESET_QUEUED)) {

            // Heals this Actor
            this.heal(this.getMaxHp());

            //Remove Cripple debuff
            if (this.hasCapability(Status.CRIPPLED)) {
                // This line is to clear off all statuses
                this.removeCapability(Status.CRIPPLED);
            }

            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }

            // This line is to ensure that the RESET_QUEUED status is removed
            this.removeCapability(Status.RESET_QUEUED);

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }

        if (!this.hasCapability(Status.CRIPPLED))
            for (Exit exit : map.locationOf(this).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                    }
                }
                for (Exit exitOfExit : exit.getDestination().getExits()){
                    if (destination.containsAnActor()) {
                        Location destination2 = exitOfExit.getDestination();
                        if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                            this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                        }
                    }
                }
            }
        else {
            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    /** This method is used to assign a new intrinsic weapon to the Koopa
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
