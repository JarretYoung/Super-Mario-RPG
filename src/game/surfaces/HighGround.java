package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.reset.Resettable;

/**
 * This class is responsible for any grounds which are high grounds
 */
public abstract class HighGround extends Ground implements Resettable {

    /**
     * High Ground's name
     */
    private final String name;
    /**
     * Success rate of jumping onto high ground
     */
    private int jumpSuccessRate;
    /**
     * Damage done onto jump for failed jumps. This is specific high ground child classes.
     */
    private int jumpDamagePoints;

    /** This is the constructor for the HighGround class
     *
     * @param displayChar is the character to be displayed on the map
     * @param name name of high ground
     * @param jumpSuccessRate high ground jump success rate
     * @param jumpDamagePoints high ground failed jump damage done to actor
     */
    public HighGround(char displayChar, String name, int jumpSuccessRate, int jumpDamagePoints) {
        super(displayChar);
        this.addCapability(Status.HIGH_GROUND);
        this.name = name;
        this.jumpSuccessRate = jumpSuccessRate;
        this.jumpDamagePoints = jumpDamagePoints;
        registerInstance();

    }

    /**
     * if actor can enter/ move into
     * @param actor the Actor to check
     * @return true false
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * returns jump action if actor can jump
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return jump action
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND) && !(location.containsAnActor())){
            actions.add(new JumpAction(this, direction, location));
        }

       return actions;

    }

    /**
     * getter for jump success rate
     * @return jump success rate
     */
    public int getJumpSuccessRate() {
        return jumpSuccessRate;
    }

    /**
     * getter for jump damage points
     * @return jump damage points
     */
    public int getJumpDamagePoints() {
        return jumpDamagePoints;
    }

    /**
     * getter for high ground name
     * @return high ground name
     */
    public String getName(){
        return name;
    }

    /**
     * Method to queue a reset for all children extending the Tree class
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }


}
