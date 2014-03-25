/**
 *
 */
package com.tradenexus.option.service;

import com.tradenexus.option.model.StockProfile;

/**
 * An interface to find stock profile.
 *
 * @author Cain
 */
public interface StockProfileProbe {
    /**
     * Finds the stock profile.
     */
    StockProfile probe(String symbol);
}
