/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.tradenexus.option.model.NasdaqProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.StockProfileProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * This class finds the stock profile from the nasdaq.
 *
 * @author Cain
 */
@Service("nasdaqProfileProbe")
public class NasdaqProfileProbe implements StockProfileProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public NasdaqProfile probe(String symbol) {
        String url = "http://www.nasdaq.com/symbol/" + symbol + "/stock-report";
        logger.info("Fetching nasdaq profile: " + url);
        HtmlParser parser = new HtmlParser(url);

        NasdaqProfile profile = new NasdaqProfile(symbol, url);
        String lastSale = parser.text("div#qwidget_lastsale");
        if (StringUtils.isNoneBlank(lastSale) && lastSale.startsWith("$")) {
            profile.setLastPrice(StockProfile.parseNumber(lastSale.substring(1)));
        }

        Element reportTable = parser.first("#quotes_content_left_pnlContent table");
        String yearHighText = parser.text(reportTable, "tr:nth-of-type(2) td:nth-of-type(1)");
        profile.setYearHigh(StockProfile.parseNumber(yearHighText));

        String yearLowText = parser.text(reportTable, "tr:nth-of-type(2) td:nth-of-type(2)");
        profile.setYearLow(StockProfile.parseNumber(yearLowText));

        String outstandingSharesText = parser.text(reportTable, "tr:nth-of-type(4) td:nth-of-type(2)");
        profile.setOutstandingShares(StockProfile.parseNumber(outstandingSharesText));

        return profile;
    }

}
