package game;

import game.items.TradeableItem;

import java.util.ArrayList;

public class TradeManager {
    private ArrayList<TradeableItem> tradeableItems;

    private static TradeManager instance;

    private TradeManager() {
        tradeableItems = new ArrayList<>();
    }

    public static TradeManager getInstance() {
        if (instance == null) {
            instance = new TradeManager();
        }
        return instance;
    }

    public void addTradeableItem(TradeableItem item) {
        this.tradeableItems.add(item);
    }

    public ArrayList<TradeableItem> getTradeableItems() {
        return new ArrayList<TradeableItem>(this.tradeableItems);
    }
}
