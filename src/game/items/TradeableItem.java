package game.items;

import game.actors.CurrencyCollector;

public interface TradeableItem{
    int getValue();

    void setValue(int value);

    default void addToTradeManager(){
        TradeManager.getInstance().addTradeableItem(this);
    }

    public String traded(CurrencyCollector customer);
}
