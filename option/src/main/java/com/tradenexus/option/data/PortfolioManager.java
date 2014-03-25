/**
 *
 */
package com.tradenexus.option.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * This class provides methods to load the portfolio.
 *
 * @author cainwang
 */
@Component
public class PortfolioManager {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * The stock symbols.
     */
    private List<String> symbols;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Loads the stock symbols from the portfolio file.
     */
    @PostConstruct
    public void loadPortfolio() {
        symbols = new LinkedList<>();

        Resource resource = applicationContext.getResource("classpath:portfolio.txt");
        try (InputStream input = resource.getInputStream()) {
            String content = IOUtils.toString(input);

            for (String symbol : content.split("\\s+")) {
                symbols.add(symbol.toUpperCase());
            }

            logger.info("Portfolio loaded:\n" + symbols);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSymbols() {
        return symbols;
    }

    /**
     * Checks if the symbol is in the portfolio.
     */
    public boolean isInPortfolio(String symbol) {
        return symbols.contains(symbol);
    }
}
