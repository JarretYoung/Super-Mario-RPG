package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.reset.Resettable;

/**
 * Warp Pipe Class
 * Teleports Player from source location to destination location
 * Piranha Plant needs to be killed for player to jump onto, and then teleport
 *
 * @author Lup Hoong
 * @version 1.0 18/5/2022
 */
public class WarpPipe extends HighGround implements Resettable {


    /**
     * An attribute to check when to intialise PiranhaPlant
     * True when game starts -> only initialise Piranha PLant on second turn
     * True when game resets
     * False upon initialised
     */
    private boolean resetted;

    /**
     * The Warp pipe's piranha plant to be defeated
     */
    private PiranhaPlant piranhaPlant;

    /**
     * Teleport Action used by player
     */
    private TeleportAction teleportAction;


    /**
     * Constructor
     */
    public WarpPipe() {
        super('C', "Warp Pipe", 100, 0);
        this.addCapability(Status.TELEPORTATION_GROUND);
        this.resetted = true;
        this.teleportAction = null;
        this.piranhaPlant = null;
        registerInstance();

    }

    /**
     * Sets the source and destination
     * @param teleportAction new teleportAction
     */
    public void setTeleportAction(TeleportAction teleportAction) {
        this.teleportAction = teleportAction;
    }

    public TeleportAction getTeleportAction() {
        return teleportAction;
    }

    /**
     * Every tick,
     * Ensures that Piranha plant is present: 1 turn after game starts/game reset
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // when game starts, we show piranha plant after the first turn
        if (this.resetted){
            this.piranhaPlant = new PiranhaPlant();
            location.addActor(this.piranhaPlant);
            this.resetted = false;
        }else if (this.hasCapability(Status.RESET_QUEUED)){
            if (!location.containsAnActor()){
                this.resetted = true;
                this.removeCapability(Status.RESET_QUEUED);
            }

        }


    }


    /**
     * Returns JumpAction when Piranha is killed
     * Returns Teleport Action when player is on WarpPipe high ground
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Action list
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();


        // if player is on warp pipe
        if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY) ){
            actions.add(this.teleportAction);

        } // if player defeats piranha plant
        else if (actor.hasCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND) && !(location.containsAnActor()) && (this.piranhaPlant != null)){
            actions.add(new JumpAction(this, direction, location));
        }


        return actions;
    }

    /**
     * Getter for its piranha plant
     * @return its piranhaPlant
     */
    public PiranhaPlant getPiranhaPlant() {
        return piranhaPlant;
    }



}
