package game.actors.npc;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.EndGameAction;

public class PrincessPeach extends NPC {
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
            // We should consider to remove this monologue
//            actions.add(new TalkAction(otherActor, new Monologue_Peach()));
            actions.add(new EndGameAction(this));
        }
        return actions;
    }
}
