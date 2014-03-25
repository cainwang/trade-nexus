/**
 *
 */
package com.tradenexus.option.service;

import com.tradenexus.option.model.IndexFutureProfile;

/**
 * Defines probe class to find stock index future.
 *
 * @author Cain
 */
public interface IndexFutureProbe {
    /**
     * Finds US stock index future.
     */
    IndexFutureProfile probe();
}
