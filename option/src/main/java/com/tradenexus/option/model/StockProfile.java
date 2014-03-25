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

    /**
     * Parses the string to number.
     */
    public static Number parseNumber(String numberString) {
        if (numberString.startsWith("+") || numberString.startsWith("-")) {
            numberString = numberString.substring(1);
        }

        try {
            if (!StringUtil.isBlank(numberString)) {
                return NumberFormat.getInstance().parse(numberString.trim());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Parses the string to double.
     */
    public static double parseDouble(String numberString) {
        return parseNumber(numberString).doubleValue();
    }

    /**
     * Parses the string to long.
     */
    public static long parseLong(String numberString) {
        return parseNumber(numberString).longValue();
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}
