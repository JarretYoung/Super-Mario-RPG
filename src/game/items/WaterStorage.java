package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Buffable;
/**This class manages all tradeable items that can be traded
 *
 * @author Jastej Gill
 * @version 2.0 28/4/2022
 *
 */
public interface WaterStorage {

    /**
     * Called when Player performs DrinkAction on WaterStorage
     * @param by Actor drinking from WaterStorage
     * @return String of result
     */
    public String DrinkedFrom(Buffable by);

    /**
     * Called when Player performs FillUpAction on WaterStorage
     * @param by Actor filling up from from WaterStorage
     * @param water water by obtains
     * @return String of result
     */
    public String filled(Buffable by, MagicalWater water);

    /**
     * Checks when water storage is empty
     * @return boolean value, true if empty
     */
    public boolean isEmpty();

    /**
     * Gets stack of magical water
     * @return  magicalWaterStack
     */
    public String getStack();

    /**
     * Checks if water storage has a certain capability
     * @param capability capability to check
     * @return boolean value, true if has capability
     */
    public boolean hasCapability(Enum<?> capability);
}
