/**
 *
 */
package com.tradenexus.option.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradenexus.option.data.PortfolioManager;
import com.tradenexus.option.model.EarningEntry;
import com.tradenexus.option.service.PortfolioEarningProbe;
import com.tradenexus.option.util.HtmlParser;

/**
 * This class finds the earning reports for the portfolio from Yahoo earning
 * website.
 *
 * @author cainwang
 */
@Service
public class YahooEarningProbe implements PortfolioEarningProbe {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private PortfolioManager portfolioManager;

    @Override
    public List<EarningEntry> probe(Date date) {
        return getEarnings(date);
    }

    public List<EarningEntry> getEarnings(Date date) {
        List<EarningEntry> earningList = new LinkedList<>();

        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
        String url = "http://biz.yahoo.com/research/earncal/" + formattedDate + ".html";
        logger.info("Fetching earning URL: " + url);

        HtmlParser parser = new HtmlParser(url);
        Elements elements = parser.selectAll("td a[href^=http://finance.yahoo.com/q?]");

        for (Element symbolElement : elements) {
            String symbol = symbolElement.text();
            if (StringUtils.isNotBlank(symbol) && portfolioManager.isInPortfolio(symbol)) {
                EarningEntry entry = new EarningEntry();
                entry.setSymbol(symbol);

                Element earningRowElement = symbolElement.parent().parent();
                Elements values = earningRowElement.select("td");

                // Past earnings don't have estimates column.
                int reportTimeColumnIndex = values.size() == 4 ? 2 : 3;
                String reportTimeText = values.get(reportTimeColumnIndex).text();
                entry.setReportTime(reportTimeText);

                earningList.add(entry);
            }
        }

        return earningList;
    }
}
