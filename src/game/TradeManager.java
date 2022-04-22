package game;

import edu.monash.fit2099.engine.items.Item;
import game.items.TradeableItem;

import java.util.ArrayList;


public class TradeManager {
    private ArrayList<TradeableItem> tradeableItemList;

    private static TradeManager instance;

    private TradeManager() {
        tradeableItemList = new ArrayList<TradeableItem>();
    }


    public static TradeManager getInstance(){
        if(instance == null){
            instance = new TradeManager();
        }
        return instance;
    }


    public void appendTradeableItem(TradeableItem item) {
        this.tradeableItemList.add(item);
    }

    public ArrayList<TradeableItem> getTradeableItems() {
        return new ArrayList<TradeableItem>(this.tradeableItemList);
    }

    public TradeableItem getTradeableItem(Item item){
        TradeableItem ret = null;
        for(int i = 0; i < tradeableItemList.size(); i++){
            if(tradeableItemList.contains(item))
                ret = (TradeableItem) item;
        }
        return ret;
    }
}
