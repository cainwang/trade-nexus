/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.model.ZacksProfile;
import com.tradenexus.option.service.StockProfileProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * This class finds stock profile from Zacks.
 *
 * @author Cain
 */
@Service("zacksProfileProbe")
public class ZacksProfileProbe implements StockProfileProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public StockProfile probe(String symbol) {
        String url = "http://www.zacks.com/stock/quote/" + symbol;
        logger.info("Fetching Zacks profile: " + url);
        HtmlParser parser = new HtmlParser(url);

        ZacksProfile profile = new ZacksProfile(symbol, url);

        String earningSurprise = parser.text("section#stock_key_earnings tr:nth-of-type(" + 6 + ") td:nth-of-type(2)");
        profile.setEarningSurprise(earningSurprise);

        String rank = parser.text("section#premium_research td strong");
        profile.setRank(rank);

        return profile;
    }

}
