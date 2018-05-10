package fr.levon.projettechno;

import java.util.ArrayList;
import java.util.List;

public class Folio {

    private List<Coin> coins;
    private List<Integer> quantites;
    private String username;

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Integer> getQuantites() {
        return quantites;
    }

    public void setQuantites(List<Integer> quantites) {
        this.quantites = quantites;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Folio() {
       this.coins = new ArrayList<Coin>();
       this.quantites = new ArrayList<Integer>();
    }
}
