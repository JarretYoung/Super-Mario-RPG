package game.surfaces;

public class PowerFountain extends Fountain{
    public PowerFountain() {
        super('A', "Power Fountain");
    }

    public String toString(){
        return "Power Fountain " + getCapacity();
    }
}
