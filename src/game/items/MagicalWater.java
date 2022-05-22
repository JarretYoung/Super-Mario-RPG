package game.items;

import game.actors.Buffable;
import game.surfaces.Fountain;

/**
 * MagicalWater class
 * Capabilities: Is stored within the bottle to bestow different effects to the consumer
 *
 * @author Jastej Gill
 * @version 1.0 20/5/2022
 */
public class MagicalWater {
    /**
     * This is the source of the MagicalWater
     */
    private Fountain source;

    /** This is the setter for the source of the MagicalWater
     *
     * @param source is the source of the MagicalWater
     */
    public void setSource(Fountain source) {
        this.source = source;
    }

    /** This is a getter for the source of the MagicalWater
     *
     * @return the Fountain which is the source of the MagicalWater
     */
    public Fountain getSource() { return source; }

    /** Constructor
     *
     * @param fountain is the souce of the MagicalWater
     */
    public MagicalWater(Fountain fountain){
        setSource(fountain);
    }

    /** This method is used to bestow certain buffs unto an Actor
     *
     * @param actor is the actor that is getting buffed
     * @return a description of the buff
     */
    public String drinked(Buffable actor){
        String ret = "";
        if(source.getName().equals("Health Fountain")) {
            actor.heal(50);
            ret = ", " + actor + " has healed 50 HP";
        }
        else if(source.getName().equals("Power Fountain")) {
            actor.addDamage(15);
            ret = ", " + actor + " has increased intrinsic damage by 15";
        }
        return ret;
    }

    /** This method is used to label the name of the MagicalWater based on the name of the source
     *
     * @return the name of the MagicalWater based on its source
     */
    public String toString(){
        String ret = "";
        if(source.getName().equals("Health Fountain"))
            ret = "Health Water";
        else if(source.getName().equals("Power Fountain"))
            ret = "Power Water";
        return ret;
    }
}
