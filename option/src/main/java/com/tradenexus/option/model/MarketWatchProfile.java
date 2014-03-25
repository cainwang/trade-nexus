/**
 *
 */
package com.tradenexus.option.model;

/**
 * The market watch stock profile.
 *
 * @author Cain
 */
public class MarketWatchProfile extends StockProfile {
    private int buys;

    private int overweights;

    private int holds;

    private int underweights;

    private int sells;

    private double averageTargetPrice;

    public MarketWatchProfile(String symbol, String referenceUrl) {
        super(symbol, referenceUrl);
    }

    public int getBuys() {
        return buys;
    }

    public void setBuys(int buys) {
        this.buys = buys;
    }

    public int getOverweights() {
        return overweights;
    }

    public void setOverweights(int overweights) {
        this.overweights = overweights;
    }

    public int getHolds() {
        return holds;
    }

    public void setHolds(int holds) {
        this.holds = holds;
    }

    public int getUnderweights() {
        return underweights;
    }

    public void setUnderweights(int underweights) {
        this.underweights = underweights;
    }

    public int getSells() {
        return sells;
    }

    public void setSells(int sells) {
        this.sells = sells;
    }

    public double getAverageTargetPrice() {
        return averageTargetPrice;
    }

    public void setAverageTargetPrice(double averageTargetPrice) {
        this.averageTargetPrice = averageTargetPrice;
    }

}
