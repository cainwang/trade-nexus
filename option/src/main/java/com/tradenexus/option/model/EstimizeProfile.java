/**
 *
 */
package com.tradenexus.option.model;


/**
 * @author Cain
 *
 */
public class EstimizeProfile extends StockProfile {
    private double estimizeEps;

    private double estimizeRevenue;

    private double wsEps;

    private double wsRevenue;

    public EstimizeProfile(String symbol, String referenceUrl) {
        super(symbol, referenceUrl);
    }

    public double getEstimizeEps() {
        return estimizeEps;
    }

    public void setEstimizeEps(double estimizeEps) {
        this.estimizeEps = estimizeEps;
    }

    public double getEstimizeRevenue() {
        return estimizeRevenue;
    }

    public void setEstimizeRevenue(double estimizeRevenue) {
        this.estimizeRevenue = estimizeRevenue;
    }

    public double getWsEps() {
        return wsEps;
    }

    public void setWsEps(double wsEps) {
        this.wsEps = wsEps;
    }

    public double getWsRevenue() {
        return wsRevenue;
    }

    public void setWsRevenue(double wsRevenue) {
        this.wsRevenue = wsRevenue;
    }

}
