/**
 *
 */
package com.tradenexus.option.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradenexus.option.model.StockProfile;
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
        StockProfile profile = new ProbeTest().testZacks();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, profile);
    }

    private StockProfile testEstimize() {
        StockProfileProbe probe = new EstimizeProfileProbe();
        return probe.probe("VMW");
    }

    private StockProfile testMarketWatch() {
        StockProfileProbe probe = new MarketWatchProfileProbe();
        return probe.probe("VMW");
    }

    private StockProfile testNasdaqOption() {
        StockProfileProbe probe = new NasdaqOptionProfileProbe();
        return probe.probe("VMW");
    }

    private StockProfile testZacks() {
        StockProfileProbe probe = new ZacksProfileProbe();
        return probe.probe("TIF");
    }
}
