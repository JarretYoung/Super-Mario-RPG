package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;

/**
 * This class is responsible for any grounds which are high grounds
 */
public abstract class HighGround extends Ground {

    /**
     * High Ground's name
     */
    private final String name;
    private int jumpSuccessRate;
    private int jumpDamagePoints;

    /**
     *
     * @param displayChar
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
     * getter for junp succes rate
     * @return jump success rate
     */
    public int getJumpSuccessRate() {
        return jumpSuccessRate;
    }

    /**
     * getter for junp damage points
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

}
