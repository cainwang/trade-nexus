/**
 *
 */
package com.tradenexus.option.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tradenexus.option.service.IndexFutureProbe;
import com.tradenexus.option.service.StockProfileProbe;

/**
 * The controller for stock profiles.
 *
 * @author cainwang
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    @Qualifier("zacksProfileProbe")
    private StockProfileProbe zacksProfileProbe;

    @Autowired
    @Qualifier("marketWatchProfileProbe")
    private StockProfileProbe marketWatchProfileProbe;

    @Autowired
    @Qualifier("estimizeProfileProbe")
    private StockProfileProbe estimizeProfileProbe;

    @Autowired
    @Qualifier("nasdaqProfileProbe")
    private StockProfileProbe nasdaqProfileProbe;

    @Autowired
    @Qualifier("nasdaqOptionProfileProbe")
    private StockProfileProbe nasdaqOptionProfileProbe;

    @Autowired
    @Qualifier("shortInterestProbe")
    private StockProfileProbe shortInterestProbe;

    @Autowired
    private IndexFutureProbe indexFutureProbe;

    @RequestMapping(value = "/zacks/{symbol}", method = RequestMethod.GET)
    public Object findZacksProfile(@PathVariable("symbol") String symbol) {
        return zacksProfileProbe.probe(symbol);
    }

    @RequestMapping(value = "/marketwatch/{symbol}", method = RequestMethod.GET)
    public Object findMarketWatchProfile(@PathVariable("symbol") String symbol) {
        return marketWatchProfileProbe.probe(symbol);
    }

    @RequestMapping(value = "/estimize/{symbol}")
    public Object findEstimizeProfile(@PathVariable("symbol") String symbol) {
        return estimizeProfileProbe.probe(symbol);
    }

    @RequestMapping(value = "/nasdaq/{symbol}")
    public Object findNasdaqProfile(@PathVariable("symbol") String symbol) {
        return nasdaqProfileProbe.probe(symbol);
    }

    @RequestMapping(value = "/nasdaq/option/{symbol}")
    public Object findNasdaqOptionProfile(@PathVariable("symbol") String symbol) {
        return nasdaqOptionProfileProbe.probe(symbol);
    }

    @RequestMapping(value = "/us-future")
    public Object findIndexFuture() {
        return indexFutureProbe.probe();
    }

    @RequestMapping(value = "/nasdaq/short-interest/{symbol}")
    public Object findShortInterest(@PathVariable("symbol") String symbol) {
        return shortInterestProbe.probe(symbol);
    }
}
