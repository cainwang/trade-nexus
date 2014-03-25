/**
 *
 */
package com.tradenexus.option.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tradenexus.option.model.Response;
import com.tradenexus.option.service.PortfolioEarningProbe;

/**
 * The controller for option earnings trade.
 *
 * @author cainwang
 */
@RestController
@RequestMapping("/earning")
public class EarningController {
    @Autowired
    PortfolioEarningProbe portfolioEarningProbe;

    @RequestMapping(value = "/calendar/{date}", method = RequestMethod.GET)
    public Response getEarningList(@PathVariable("date") long dateTime) {
        return new Response(portfolioEarningProbe.probe(new Date(dateTime)));
    }
}
