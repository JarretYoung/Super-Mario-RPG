package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;

public abstract class HighGround extends Ground {

    /**
     * High Ground's name
     */
    protected final String name;

    private int jumpSuccessRate;
    private int jumpDamagePoints;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar, String name, int jumpSuccessRate, int jumpDamagePoints) {
        super(displayChar);
        this.addCapability(Status.HIGH_GROUND);
        this.name = name;
        this.jumpSuccessRate = jumpSuccessRate;
        this.jumpDamagePoints = jumpDamagePoints;

    }


    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND) && !(location.containsAnActor())){
            actions.add(new JumpAction(this, direction, location));
        }

       return actions;

    }


    public int getJumpSuccessRate() {
        return jumpSuccessRate;
    }

    public int getJumpDamagePoints() {
        return jumpDamagePoints;
    }

    public String getName(){
        return name;
    }

}
