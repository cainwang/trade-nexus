/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.logging.Logger;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.tradenexus.option.model.EstimizeProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.StockProfileProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * @author Cain
 *
 */
@Service("estimizeProfileProbe")
public class EstimizeProfileProbe implements StockProfileProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public StockProfile probe(String symbol) {
        String url = "http://estimize.com/" + symbol;
        logger.info("Fetching Estimize earning: " + url);

        HtmlParser parser = new HtmlParser(url);
        EstimizeProfile profile = new EstimizeProfile(symbol, url);

        Element estimizeConsensusEl = parser.first(".estimize-consensus");
        String estimizeEps = parser.text(estimizeConsensusEl, ".eps");
        String estimizeRevenue = parser.text(estimizeConsensusEl, ".revenue");

        profile.setEstimizeEps(StockProfile.parseLong(estimizeEps));
        profile.setEstimizeRevenue(StockProfile.parseLong(estimizeRevenue));

        Element wsConsensusEl = parser.first(".wall-street-consensus");
        String wsEps = parser.text(wsConsensusEl, ".eps");
        String wsRevenue = parser.text(wsConsensusEl, ".revenue");

        profile.setWsEps(StockProfile.parseLong(wsEps));
        profile.setWsRevenue(StockProfile.parseLong(wsRevenue));

        return profile;
    }

}
