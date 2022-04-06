package game;

public class Sampling extends Tree  {
    public Sampling() {
        super('t');
    }


    /**
     * Checks all actions taken in every tick
     */
    public void checkAction(){
        checkGrowth();
        dropCoin();
    }

    /**
     * Sampling grows into Mature
     */
    public void checkGrowth() {
        if (super.getAge() % 9 == 0){
            //Sampling grows into Mature
        }
    }

    /**
     * Drop pattern: 10% chance to drop a coin ($20) on its location in every turn.
     */
    public void dropCoin(){
        //drop coin of $20 with 10% chance on Sampling location
    }
}
