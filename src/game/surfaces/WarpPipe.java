package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;

public class WarpPipe extends HighGround  {


    private boolean resetted;
    private PiranhaPlant piranhaPlant;
    private TeleportAction teleportAction;




    public WarpPipe() {
        super('C', "Warp Pipe", 100, 0);
        this.resetted = true;
        this.teleportAction = null;
        this.piranhaPlant = null;

    }


    public void setTeleportAction(TeleportAction teleportAction) {
        this.teleportAction = teleportAction;
    }

    public TeleportAction getTeleportAction() {
        return teleportAction;
    }

    @Override
    public void tick(Location location) {
        // when game starts, we show piranha plant after the first turn
        if (this.resetted){
            //this.piranhaPlant.addCapability(Status.LIVE_PIRANHA_PLANT);
            this.piranhaPlant = new PiranhaPlant();
            location.addActor(this.piranhaPlant);
            this.resetted = false;
        }

    }




    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // if piranha plant is killed/removed from map, and

        if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY) ){ // if player is on warp pipe
            //add teleport action
            actions.add(this.teleportAction);

        }
        else if (actor.hasCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND) && !(location.containsAnActor()) && (this.piranhaPlant != null)){
            actions.add(new JumpAction(this, direction, location));
        }




        return actions;
    }

    public PiranhaPlant getPiranhaPlant() {
        return piranhaPlant;
    }
}
