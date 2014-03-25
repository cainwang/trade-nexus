/**
 *
 */
package com.tradenexus.option.model;

import java.text.NumberFormat;
import java.text.ParseException;

import org.jsoup.helper.StringUtil;

/**
 * @author Cain
 *
 */
public class StockProfile {
    /**
     * The stock symbol.
     */
    private String symbol;

    /**
     * The profile reference URL.
     */
    private String referenceUrl;

    public StockProfile(String symbol) {
        setSymbol(symbol);
    }

    public StockProfile(String symbol, String referenceUrl) {
        this(symbol);
        setReferenceUrl(referenceUrl);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static double parseNumber(String numberString) {
        try {
            if (!StringUtil.isBlank(numberString)) {
                return NumberFormat.getInstance().parse(numberString.trim()).doubleValue();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}
