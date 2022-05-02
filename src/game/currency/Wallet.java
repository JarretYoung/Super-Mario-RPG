package game.currency;

import edu.monash.fit2099.engine.items.Item;

/**
 * Wallet class that extends abstract class item
 * @author Jastej Gill
 * @version 1.0 25/4/2022
 */
public class Wallet extends Item {

    /**
     * Balance in wallet
     */
    private int balance;
    /***
     * Constructor.
     */
    public Wallet() {
        super("wallet", 'W', false);
        this.balance = 0;
    }

    /**
     * Method to get balance value remaining in wallet
     * @return integer of balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Mutator for balance attribute
     * @param balance integer value to assign to balance attribute
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Method to deduct from balance
     * @param balance value to be deducted from balance in wallet, remaining balance must be at least zero
     */
    public void removeBalance(int balance) {
        if(getBalance() - balance > 0)
            this.balance -= balance;
    }

    /**
     * Method to add to balance
     * @param added value added to total balance in wallet
     */
    public void addToBalance(int added) {
        setBalance(getBalance() + added);
    }
}
