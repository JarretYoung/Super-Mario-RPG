package game.surfaces;

public class HealthFountain extends Fountain{
    public HealthFountain() {
        super('H', "Health Fountain");
    }

    public String toString(){
        return "Health Fountain " + getCapacity();
    }
}
