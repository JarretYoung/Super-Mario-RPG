package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.surfaces.WarpPipe;

/**
 * Teleports actor from source to destination
 *
 * @author Lup Hoong
 * @version 1.0 18/5/2022
 */
public class TeleportAction extends Action {

    /**
     * Source location
     */
    private Location sourceLocation;

    /**
     * Destination location
     */
    private Location destinationLocation;

    /**
     * Source WarpPipe
     */
    private WarpPipe sourceWarpPipe;

    /**
     * Destination WarpPipe
     */
    private WarpPipe destinationWarpPipe;


    /**
     * Constructor
     * @param source source location
     * @param destination destination location
     * @param sourceWarpPipe source warp pipe
     * @param destinationWarpPipe destination warp pipe
     */
    public TeleportAction(Location source, Location destination, WarpPipe sourceWarpPipe, WarpPipe destinationWarpPipe) {
        this.sourceLocation = source;
        this.destinationLocation = destination;
        this.sourceWarpPipe = sourceWarpPipe;
        this.destinationWarpPipe = destinationWarpPipe;
    }


    /**
     * Sets new destination, and teleports player
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Successful teleportation message
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // make sure player teleports back to the same source WarpPipe
        destinationWarpPipe.setTeleportAction( new TeleportAction(this.destinationLocation, this.sourceLocation, this.destinationWarpPipe, this.sourceWarpPipe));
        destinationLocation.map().removeActor(destinationWarpPipe.getPiranhaPlant());


        // moves player from source map location to destination map and location
        map.moveActor(actor, destinationLocation );


        return ("Bzz. Player has teleported!");

    }


    @Override
    public String menuDescription(Actor actor) {
        return "Player Teleports to Lava Zone";
    }
}
