/**
 *
 */
package com.tradenexus.option.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.tradenexus.exception.NexusException;
import com.tradenexus.option.model.NasdaqOptionProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.StockProfileProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * This class finds the stock option profile from the nasdaq.
 *
 * @author Cain
 */
@Service("nasdaqOptionProfileProbe")
public class NasdaqOptionProfileProbe implements StockProfileProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public NasdaqOptionProfile probe(String symbol) {
        int page = 1, maxTries = 5;
        Element atmOptionElement = null;
        NasdaqOptionProfile profile = new NasdaqOptionProfile(symbol);

        while (atmOptionElement == null && page < maxTries) {
            atmOptionElement = findAtmOption(profile, symbol, page++);
        }

        if (atmOptionElement != null) {
            Elements columns = atmOptionElement.select("td");
            Element strikePriceColumn = columns.get(columns.size() / 2);
            double atmStrikePrice = StockProfile.parseDouble(strikePriceColumn.text());
            profile.setAtmStrikePrice(atmStrikePrice);

            profile.setNextExpirationDate(parseExpirationDate(columns.first().text()));

            Element callPriceColumn = columns.get(1);
            double callPrice = StockProfile.parseDouble(callPriceColumn.text());
            profile.setAtmCallPrice(callPrice);

            Element putPriceColumn = columns.get(10);
            double putPrice = StockProfile.parseDouble(putPriceColumn.text());
            profile.setAtmPutPrice(putPrice);

            double distance = callPrice + putPrice;
            profile.setStraddleLowBound(atmStrikePrice - distance);
            profile.setStraddleHighBound(atmStrikePrice + distance);
        }

        return profile;
    }

    /**
     * Finds the ATM option info.
     */
    private Element findAtmOption(NasdaqOptionProfile profile, String symbol, int page) {
        String url = "http://www.nasdaq.com/symbol/" + symbol + "/option-chain?dateindex=-1";
        if (page > 1) {
            url += "&page=" + page;
        }
        logger.info("Fetching nasdaq option chain: " + url);

        profile.setReferenceUrl(url);
        HtmlParser parser = new HtmlParser(url);

        return findAtmStrike(parser);
    }

    /**
     * Finds the ATM option.
     */
    private Element findAtmStrike(HtmlParser parser) {
        Elements strikes = parser.selectAll(".OptionsChain-chart tbody tr");

        for (int i = 0; i < strikes.size(); i++) {
            Element strike = strikes.get(i);
            Elements values = strike.select("td");

            // Skip table rows of expiration date.
            if (values.size() > 1) {
                // If has future ITM put, find the previous strike price with
                // future IMT call.
                if (isInFuture(values.first().text()) && isITM(values.last()) && i > 0) {
                    Element previousStrike = strikes.get(i - 1);
                    Element previousFirstValue = previousStrike.select("td").first();

                    if (isInFuture(previousFirstValue.text())) {
                        return previousStrike;
                    }
                }
            }
        }

        return null;
    }

    /**
     * If the specified date is in the future.
     */
    private boolean isInFuture(String dateString) {
        return parseExpirationDate(dateString).compareTo(new Date()) > 0;
    }

    /**
     * Parses the expiration date string.
     */
    private Date parseExpirationDate(String dateString) {
        try {
            return new SimpleDateFormat("MMM d, yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new NexusException("Error parsing option expiration date.", e);
        }
    }

    /**
     * If the table cell's strike price is out of the moeny.
     */
    private boolean isITM(Element element) {
        return StringUtils.isNotBlank(element.attr("bgcolor"));
    }
}
