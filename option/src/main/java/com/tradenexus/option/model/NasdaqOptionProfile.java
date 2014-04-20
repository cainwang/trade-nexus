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
    private double atmStrikePrice;

    private double atmCallPrice;

    private double atmPutPrice;

    private double straddleLowBound;

    private double straddleHighBound;

    private Date nextExpirationDate;

    public NasdaqOptionProfile(String symbol) {
        super(symbol);
    }

    public double getAtmStrikePrice() {
        return atmStrikePrice;
    }

    public void setAtmStrikePrice(double atmPrice) {
        this.atmStrikePrice = atmPrice;
    }

    public double getAtmCallPrice() {
        return atmCallPrice;
    }

    public void setAtmCallPrice(double callPrice) {
        this.atmCallPrice = callPrice;
    }

    public double getAtmPutPrice() {
        return atmPutPrice;
    }

    public void setAtmPutPrice(double putPrice) {
        this.atmPutPrice = putPrice;
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
