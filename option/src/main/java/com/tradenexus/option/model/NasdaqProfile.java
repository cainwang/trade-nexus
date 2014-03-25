/**
 *
 */
package com.tradenexus.option.model;

/**
 * The nasdaq stock quote profile.
 *
 * @author Cain
 */
public class NasdaqProfile extends StockProfile {

    private double lastPrice;

    private double yearLow;

    private double yearHigh;

    private long outstandingShares;

    public NasdaqProfile(String symbol, String referenceUrl) {
        super(symbol, referenceUrl);
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getYearLow() {
        return yearLow;
    }

    public void setYearLow(double yearLow) {
        this.yearLow = yearLow;
    }

    public double getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(double yearHigh) {
        this.yearHigh = yearHigh;
    }

    public long getOutstandingShares() {
        return outstandingShares;
    }

    public void setOutstandingShares(long marketCap) {
        this.outstandingShares = marketCap;
    }

}
