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

import com.tradenexus.option.model.Response;
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

    @RequestMapping(value = "/zacks/{symbol}", method = RequestMethod.GET)
    public Response findZacksProfile(@PathVariable("symbol") String symbol) {
        return new Response(zacksProfileProbe.probe(symbol));
    }

    @RequestMapping(value = "/marketwatch/{symbol}", method = RequestMethod.GET)
    public Response findMarketWatchProfile(@PathVariable("symbol") String symbol) {
        return new Response(marketWatchProfileProbe.probe(symbol));
    }

    @RequestMapping(value = "/estimize/{symbol}")
    public Response findEstimizeProfile(@PathVariable("symbol") String symbol) {
        return new Response(estimizeProfileProbe.probe(symbol));
    }

    @RequestMapping(value = "/nasdaq/{symbol}")
    public Response findNasdaqProfile(@PathVariable("symbol") String symbol) {
        return new Response(nasdaqProfileProbe.probe(symbol));
    }

    @RequestMapping(value = "/nasdaq/option/{symbol}")
    public Response findNasdaqOptionProfile(@PathVariable("symbol") String symbol) {
        return new Response(nasdaqOptionProfileProbe.probe(symbol));
    }
}
