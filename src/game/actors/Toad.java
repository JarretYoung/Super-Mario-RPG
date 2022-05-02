package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.TradeManager;
import game.actions.TalkAction;
import game.actions.TradeAction;
import game.items.TradeableItem;

public class Toad extends Npc {
    /**
     * Constructor.
     */
    private TradeManager tradeManager;
    public Toad() {
        super("Toad", '0');
        this.tradeManager = TradeManager.getInstance();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        for(int i = 0; i < TradeManager.getInstance().getTradeableItems().size(); i++) {
           TradeableItem item = TradeManager.getInstance().getTradeableItems().get(i);
           actions.add(new TradeAction(this, item));
        }

        actions.add(new TalkAction(this));
        return actions;
    }
}
