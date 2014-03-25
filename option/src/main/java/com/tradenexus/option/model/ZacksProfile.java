/**
 *
 */
package com.tradenexus.option.model;

/**
 * The Zacks stock profile.
 *
 * @author Cain
 */
public class ZacksProfile extends StockProfile {
    /**
     * The quarter earning surprise.
     */
    private String earningSurprise;

    /**
     * The zacks rank.
     */
    private String rank;

    public ZacksProfile(String symbol, String referenceUrl) {
        super(symbol, referenceUrl);
    }

    public String getEarningSurprise() {
        return earningSurprise;
    }

    public void setEarningSurprise(String earningSurprise) {
        this.earningSurprise = earningSurprise;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
