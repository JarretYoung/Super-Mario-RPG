package game.items;

/**Class of Special items that implements interface TradeableItem
 * Items that give the consumer special capabilities
 * @author Jastej Gill
 * @version 2.0 29/4/2022
 *
 */
public abstract class SpecialItem extends ConsumableItem implements TradeableItem{
    /**
     * Value of special item
     */
    private int value;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public SpecialItem(String name, char displayChar) {
        super(name, displayChar, true, false);
    }

    /**
     * Accessor for value of special item
     * @return integer value of special item
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Mutator for value of special item
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

}
