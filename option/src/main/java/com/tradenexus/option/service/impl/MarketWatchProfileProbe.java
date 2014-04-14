/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.logging.Logger;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.tradenexus.option.model.MarketWatchProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.StockProfileProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * This class finds the stock profile from the market watch.
 *
 * @author Cain
 */
@Service("marketWatchProfileProbe")
public class MarketWatchProfileProbe implements StockProfileProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public StockProfile probe(String symbol) {
        String url = "http://www.marketwatch.com/investing/stock/" + symbol + "/analystestimates";
        logger.info("Fetching MarketWatch profile: " + url);

        HtmlParser parser = new HtmlParser(url);
        MarketWatchProfile profile = new MarketWatchProfile(symbol, url);

        String averageTargetPrice = parser.text("table.snapshot tbody tr td:nth-of-type(4)");
        profile.setAverageTargetPrice(StockProfile.parseDouble(averageTargetPrice));

        Element ratingTableEl = parser.first("table.ratings");
        profile.setBuys(findRatingCount(parser, ratingTableEl, 1));
        profile.setOverweights(findRatingCount(parser, ratingTableEl, 2));
        profile.setHolds(findRatingCount(parser, ratingTableEl, 3));
        profile.setUnderweights(findRatingCount(parser, ratingTableEl, 4));
        profile.setSells(findRatingCount(parser, ratingTableEl, 5));

        return profile;
    }

    private int findRatingCount(HtmlParser parser, Element ratingTableElement, int rowNo) {
        String expr = "tbody tr:nth-of-type(" + rowNo + ") td.current";
        String ratingCount = parser.text(expr);
        return Integer.parseInt(ratingCount);
    }
}
