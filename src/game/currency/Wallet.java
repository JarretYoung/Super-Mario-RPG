package game.currency;

import edu.monash.fit2099.engine.items.Item;

public class Wallet extends Item {

    private int balance;
    /***
     * Constructor.
     */
    public Wallet() {
        super("wallet", 'W', false);
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void removeBalance(int balance) {
        this.balance -= balance;
    }

    public void addToBalance(int added) {
        setBalance(getBalance() + added);
    }
}
