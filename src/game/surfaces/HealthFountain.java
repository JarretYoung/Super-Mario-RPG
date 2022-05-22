package game.surfaces;

/**
 * Class for health fountain
 * @author Jastej Gill
 * @version 2.0 21/5/2022
 */
public class HealthFountain extends Fountain{
    /**
     * Constructor
     */
    public HealthFountain() {
        super('H', "Health Fountain");
    }

    /**
     * Return String of Fountain object
     * @return String of Fountain object
     */
    public String toString(){
        return "Health Fountain " + getCapacity();
    }
}
