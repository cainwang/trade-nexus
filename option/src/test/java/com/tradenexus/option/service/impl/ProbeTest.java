/**
 *
 */
package com.tradenexus.option.service.impl;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradenexus.option.model.IndexFutureProfile;
import com.tradenexus.option.model.StockProfile;
import com.tradenexus.option.service.IndexFutureProbe;
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
        URL url = new URL("http://www.nasdaq.com/symbol/vmw/historical");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        output.writeBytes("18m|false|VMW");
        output.flush();
        output.close();

        try (InputStream input = conn.getInputStream()) {
            String result = IOUtils.toString(input);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ProbeTest().testProfileProbe();
    }

    private void testProfileProbe() throws Exception {
        Object profile = new ProbeTest().testShort();

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
