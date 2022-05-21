package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class GetItemAction extends Action {
    private Actor owner;
    private Item received;

    public GetItemAction(Item item, Actor owner){
        this.received = item;
        this.owner = owner;
    }
    @Override
    public String execute(Actor receiver, GameMap map) {
        String ret = "";
        if(owner.getInventory().contains(received)){
            receiver.addItemToInventory(received);
            owner.removeItemFromInventory(received);
            ret = receiver + " gets " + received + " from " + owner;
        }
        return ret;
    }

    @Override
    public String menuDescription(Actor receiver) {
        return "Get " + received + " from " + owner;
    }
}
