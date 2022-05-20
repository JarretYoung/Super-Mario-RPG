package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.surfaces.WarpPipe;

/**
 * Teleports from source to destination
 */
public class TeleportAction extends Action {

    private Location sourceLocation;
    private Location destinationLocation;

    private WarpPipe sourceWarpPipe;
    private WarpPipe destinationWarpPipe;



    public TeleportAction(Location source, Location destination, WarpPipe sourceWarpPipe, WarpPipe destinationWarpPipe) {
        this.sourceLocation = source;
        this.destinationLocation = destination;
        this.sourceWarpPipe = sourceWarpPipe;
        this.destinationWarpPipe = destinationWarpPipe;
    }



    @Override
    public String execute(Actor actor, GameMap map) {

        // make sure player teleports back to the same source WarpPipe
        destinationWarpPipe.setTeleportAction( new TeleportAction(this.destinationLocation, this.sourceLocation, this.destinationWarpPipe, this.sourceWarpPipe));
        destinationLocation.map().removeActor(destinationWarpPipe.getPiranhaPlant());


        // teleport player from source map location to destination map and location
        map.moveActor(actor, destinationLocation );


        return ("Bzz. Player has teleported!");

    }


    @Override
    public String menuDescription(Actor actor) {
        return "Player Teleports";
    }
}
