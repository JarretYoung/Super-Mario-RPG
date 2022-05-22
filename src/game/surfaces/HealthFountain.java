package game.surfaces;

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
