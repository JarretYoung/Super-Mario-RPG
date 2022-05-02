package game.currency;

import game.actors.CurrencyCollector;

import java.util.ArrayList;

public class WalletManager {
    private ArrayList<Wallet> wallets;
    private ArrayList<CurrencyCollector> owners;

    private static WalletManager instance;

    private WalletManager() {
        wallets = new ArrayList<>();
        owners = new ArrayList<>();
    }

    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    public void addWallet(Wallet wallet) {
        this.wallets.add(wallet);
    }

    public ArrayList<Wallet> getWallets() {
        return new ArrayList<Wallet>(this.wallets);
    }

    public void addOwner(CurrencyCollector owner) {
        this.owners.add(owner);
    }

    public ArrayList<CurrencyCollector> getOwners() {
        return new ArrayList<CurrencyCollector>(this.owners);
    }
}
