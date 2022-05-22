package game.surfaces;

/**
 * Class for power fountain
 * @author Jastej Gill
 * @version 2.0 21/5/2022
 */
public class PowerFountain extends Fountain{
    /**
     * Constructor
     */
    public PowerFountain() {
        super('A', "Power Fountain");
    }

    /**
     * Return String of Fountain object
     * @return String of Fountain object
     */
    public String toString(){
        return "Power Fountain " + getCapacity();
    }
}
