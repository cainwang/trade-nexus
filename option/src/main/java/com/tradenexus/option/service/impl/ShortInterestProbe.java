/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.tradenexus.option.model.ShortInterestProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.StockProfileProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * This class finds the stock short interest.
 *
 * @author Cain
 */
@Service("shortInterestProbe")
public class ShortInterestProbe implements StockProfileProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public StockProfile probe(String symbol) {
        String url = "http://www.nasdaq.com/symbol/" + symbol + "/short-interest";
        logger.info("Fetching Nasdaq short interest profile: " + url);
        HtmlParser parser = new HtmlParser(url);

        ShortInterestProfile profile = new ShortInterestProfile(symbol, url);

        String shortInterestText = parser.text("table#quotes_content_left_ShortInterest1_ShortInterestGrid"
                + " tr:nth-of-type(2) td:nth-of-type(2)");
        profile.setShortInterest(StockProfile.parseLong(shortInterestText));

        return profile;
    }

}
