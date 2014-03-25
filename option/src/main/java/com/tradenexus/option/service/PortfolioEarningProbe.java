/**
 *
 */
package com.tradenexus.option.service;

import java.util.Date;
import java.util.List;

import com.tradenexus.option.model.EarningEntry;

/**
 * This interface defines methods to find earning reports for the portfolio.
 *
 * @author cainwang
 */
public interface PortfolioEarningProbe {
    /**
     * Finds the earnings for the stocks on the specified date.
     */
    abstract public List<EarningEntry> probe(Date date);
}
