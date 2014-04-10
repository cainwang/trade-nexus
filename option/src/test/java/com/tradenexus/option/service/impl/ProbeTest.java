/**
 *
 */
package com.tradenexus.option.service.impl;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradenexus.option.model.EarningEntry;
import com.tradenexus.option.model.IndexFutureProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.IndexFutureProbe;
import com.tradenexus.option.service.PortfolioEarningProbe;
import com.tradenexus.option.service.StockProfileProbe;

/**
 * @author cainwang
 *
 */
public class ProbeTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        new ProbeTest().testProfileProbe();
    }

    private void testProfileProbe() throws Exception {
        Object profile = new ProbeTest().testShort();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, profile);
    }

    private List<EarningEntry> testEarnings() {
        PortfolioEarningProbe probe = new YahooEarningProbe();
        return probe.probe(new Date());
    }

    private StockProfile testEstimize() {
        StockProfileProbe probe = new EstimizeProfileProbe();
        return probe.probe("BBBY");
    }

    private StockProfile testMarketWatch() {
        StockProfileProbe probe = new MarketWatchProfileProbe();
        return probe.probe("VMW");
    }

    private StockProfile testNasdaqOption() {
        StockProfileProbe probe = new NasdaqOptionProfileProbe();
        return probe.probe("RHT");
    }

    private StockProfile testZacks() {
        StockProfileProbe probe = new ZacksProfileProbe();
        return probe.probe("TIF");
    }

    private IndexFutureProfile testFuture() {
        IndexFutureProbe probe = new IndexFutureProbeImpl();
        return probe.probe();
    }

    private StockProfile testShort() {
        StockProfileProbe probe = new ShortInterestProbe();
        return probe.probe("VMW");
    }
}
