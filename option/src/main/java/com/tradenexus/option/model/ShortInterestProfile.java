/**
 *
 */
package com.tradenexus.option.model;

/**
 * The stock short interest profile.
 *
 * @author Cain
 */
public class ShortInterestProfile extends StockProfile {
    private long shortInterest;

    public ShortInterestProfile(String symbol, String referenceUrl) {
        super(symbol, referenceUrl);
    }

    public long getShortInterest() {
        return shortInterest;
    }

    public void setShortInterest(long shortInterest) {
        this.shortInterest = shortInterest;
    }

}
