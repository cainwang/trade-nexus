/**
 *
 */
package com.tradenexus.option.model;

/**
 * An earning report entry.
 *
 * @author cainwang
 */
public class EarningEntry {
    /**
     * The stock symbol.
     */
    private String symbol;

    /**
     * The report time of the day.
     */
    private String reportTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

}
