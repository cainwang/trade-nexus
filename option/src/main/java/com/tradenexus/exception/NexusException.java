/**
 *
 */
package com.tradenexus.exception;

/**
 * The general Nexus exception.
 *
 * @author Cain
 */
public class NexusException extends RuntimeException {
    private static final long serialVersionUID = -4245444200112419239L;

    /**
     * If this exception is for internal system and should not be exposed to the
     * users.
     */
    private boolean internal;

    public NexusException(String string, Throwable e) {
        super(string, e);
    }

    public NexusException(String string, Throwable e, boolean internal) {
        this(string, e);
        setInternal(internal);
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

}
