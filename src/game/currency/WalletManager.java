package game.currency;

import game.actors.CurrencyCollector;

import java.util.ArrayList;
/**This class manages all wallets and their owners
 *
 * @author Jastej Gill
 * @version 2.0 28/4/2022
 *
 */
public class WalletManager {
    /**
     * List of wallets
     */
    private ArrayList<Wallet> wallets;
    /**
     * List of currency collectors
     */
    private ArrayList<CurrencyCollector> owners;

    private static WalletManager instance;

    /**
     * Constructor for WalletManager class
     * Creates an ArrayList of wallets and their owners
     */
    private WalletManager() {
        wallets = new ArrayList<>();
        owners = new ArrayList<>();
    }

    /**
     * Method to allow user to access methods of WalletManager class
     * @return an instance of class Wallet manager
     */
    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Method to add a wallet item to wallet ArrayList
     * @param wallet wallet to be added to wallets ArrayList
     */
    public void addWallet(Wallet wallet) {
        this.wallets.add(wallet);
    }

    /**
     * Getter for wallets ArrayList
     * @return wallets ArrayList
     */
    public ArrayList<Wallet> getWallets() {
        return new ArrayList<Wallet>(this.wallets);
    }

    /**
     * Method to add a CurrencyCollector owner to owner ArrayList
     * @param owner to be added to owners ArrayList
     */
    public void addOwner(CurrencyCollector owner) {
        this.owners.add(owner);
    }

    /**
     * Getter for owners ArrayList
     * @return owners ArrayList
     */
    public ArrayList<CurrencyCollector> getOwners() {
        return new ArrayList<CurrencyCollector>(this.owners);
    }
}
