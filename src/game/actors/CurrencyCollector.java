package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.WalletManager;
import game.currency.Wallet;

public abstract class CurrencyCollector extends Actor {

    private Wallet wallet;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public CurrencyCollector(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.wallet = new Wallet();
        this.addItemToInventory(this.wallet);
        WalletManager.getInstance().addWallet(this.wallet);
        WalletManager.getInstance().addOwner(this);
    }

    public Wallet getWallet() {
        Wallet ret = null;
        int index = this.getInventory().indexOf(this.wallet);
        if (WalletManager.getInstance().getWallets().contains(this.wallet))
            ret = (Wallet) this.getInventory().get(index);
        return ret;
    }

}
