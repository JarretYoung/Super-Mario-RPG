package game.items;

import java.util.ArrayList;
/**This class manages all tradeable items that can be traded
 *
 * @author Jastej Gill
 * @version 2.0 28/4/2022
 *
 */
public class TradeManager {
    /**
     * List of tradeable items
     */
    private ArrayList<TradeableItem> tradeableItems;

    /**
     * Instance of trade manager
     */
    private static TradeManager instance;

    /**
     * Constructor for TradeManager class
     * Creates an ArrayList of tradeable items
     */
    private TradeManager() {
        tradeableItems = new ArrayList<>();
    }

    /**
     * Method to allow user to access methods of TradeManager class
     * @return an instance of class Trade manager
     */
    public static TradeManager getInstance() {
        if (instance == null) {
            instance = new TradeManager();
        }
        return instance;
    }

    /**
     * Method to add a tradeable item to tradeable items ArrayList
     * @param item tradeable item to be added to tradeable items ArrayList
     */
    public void addTradeableItem(TradeableItem item) {
        this.tradeableItems.add(item);
    }


    /**
     * Getter for tradeable items ArrayList
     * @return tradeable items ArrayList
     */
    public ArrayList<TradeableItem> getTradeableItems() {
        return new ArrayList<TradeableItem>(this.tradeableItems);
    }
}
