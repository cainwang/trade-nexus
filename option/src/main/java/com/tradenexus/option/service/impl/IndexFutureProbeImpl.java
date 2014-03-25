/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.model.IndexFutureProfile;
import com.tradenexus.option.service.IndexFutureProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * @author Cain
 */
@Service
public class IndexFutureProbeImpl implements IndexFutureProbe {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public IndexFutureProfile probe() {
        String url = "http://www.bloomberg.com/markets/stocks/futures";
        logger.info("Fetching Bloomberg future profile: " + url);
        HtmlParser parser = new HtmlParser(url);

        IndexFutureProfile profile = new IndexFutureProfile();
        String dowJonesText = parser.text("table.index_table tbody tr:nth-of-type(2) td:nth-of-type(4)");
        profile.setDowJones(StockProfile.parseDouble(dowJonesText));

        return profile;
    }

}
