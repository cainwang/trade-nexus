/**
 *
 */
package com.tradenexus.option.model;

import java.util.Date;

/**
 * The Nasdaq stock option profile.
 *
 * @author Cain
 */
public class NasdaqOptionProfile extends StockProfile {
    private double atmPrice;

    private double callPrice;

    private double putPrice;

    private double straddleLowBound;

    private double straddleHighBound;

    private Date nextExpirationDate;

    public NasdaqOptionProfile(String symbol, String referenceUrl) {
        super(symbol, referenceUrl);
    }

    public double getAtmPrice() {
        return atmPrice;
    }

    public void setAtmPrice(double atmPrice) {
        this.atmPrice = atmPrice;
    }

    public double getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(double callPrice) {
        this.callPrice = callPrice;
    }

    public double getPutPrice() {
        return putPrice;
    }

    public void setPutPrice(double putPrice) {
        this.putPrice = putPrice;
    }

    public double getStraddleLowBound() {
        return straddleLowBound;
    }

    public void setStraddleLowBound(double straddleLowBound) {
        this.straddleLowBound = straddleLowBound;
    }

    public double getStraddleHighBound() {
        return straddleHighBound;
    }

    public void setStraddleHighBound(double straddleHighBound) {
        this.straddleHighBound = straddleHighBound;
    }

    public Date getNextExpirationDate() {
        return nextExpirationDate;
    }

    public void setNextExpirationDate(Date nextExpirationDate) {
        this.nextExpirationDate = nextExpirationDate;
    }
}
