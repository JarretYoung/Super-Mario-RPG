package game.actors.npc;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.TalkAction;
import game.actors.npc.npc;
import game.monologue.Monologue_Peach;

public class PrincessPeach extends npc {
    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P');
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.FINAL_BOSS_CLEARED)) {
            actions.add(new TalkAction(otherActor, new Monologue_Peach()));
        }
        return actions;
    }
}
