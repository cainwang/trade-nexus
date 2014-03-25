package com.tradenexus.option.util;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tradenexus.exception.NexusException;

/**
 *
 */

/**
 * The HTML parser utility class.
 *
 * @author Cain
 */
public class HtmlParser {
    /**
     * The HTML document.
     */
    private Document document;

    public HtmlParser(String url) {
        try {
            Connection conn = Jsoup.connect(url);
            conn.timeout(30 * 1000);
            document = conn.get();
        } catch (SocketTimeoutException e) {
            throw new NexusException("Request timed out: " + url, e, true);
        } catch (HttpStatusException e) {
            throw new NexusException(e.getMessage() + ": " + url, e, true);
        } catch (IOException e) {
            throw new NexusException("Request IO exception.", e);
        }
    }

    /**
     * Selects all the elements matched by the selector expression.
     */
    public Elements selectAll(String selector) {
        return selectAll(document, selector);
    }

    /**
     * Selects all the elements matched by the selector expression.
     */
    public Elements selectAll(Element root, String selector) {
        return root.select(selector);
    }

    /**
     * Selects the first element matched by the selector expression.
     */
    public Element first(String selector) {
        return first(document, selector);
    }

    /**
     * Selects the first element matched by the selector expression.
     */
    public Element first(Element root, String selector) {
        return root.select(selector).first();
    }

    /**
     * Selects the last element matched by the selector expression.
     */
    public Element last(String selector) {
        return last(document, selector);
    }

    /**
     * Selects the last element matched by the selector expression.
     */
    public Element last(Element root, String selector) {
        return root.select(selector).last();
    }

    /**
     * Finds the text of the first element matched by the selector expression.
     */
    public String text(String selector) {
        return text(document, selector);
    }

    /**
     * Finds the text of the first element matched by the selector expression.
     */
    public String text(Element root, String selector) {
        Element el = first(root, selector);
        if (el != null) {
            return el.text();
        }
        return "";
    }
}
