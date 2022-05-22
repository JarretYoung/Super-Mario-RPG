package game.surfaces;

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
