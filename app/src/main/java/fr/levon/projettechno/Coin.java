package fr.levon.projettechno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {


    private String name;
    private String symbol;
    private int rank;
    private float priceUSD;
    private float priceBTC;
    private float change1H;
    private float change24H;
    private float change7D;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(float priceUSD) {
        this.priceUSD = priceUSD;
    }

    public float getPriceBTC() {
        return priceBTC;
    }

    public void setPriceBTC(float priceBTC) {
        this.priceBTC = priceBTC;
    }

    public float getChange1H() {
        return change1H;
    }

    public void setChange1H(float change1H) {
        this.change1H = change1H;
    }

    public float getChange24H() {
        return change24H;
    }

    public void setChange24H(float change24H) {
        this.change24H = change24H;
    }

    public float getChange7D() {
        return change7D;
    }

    public void setChange7D(float change7D) {
        this.change7D = change7D;
    }
}
