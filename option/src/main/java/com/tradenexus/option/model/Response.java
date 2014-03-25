/**
 *
 */
package com.tradenexus.option.model;

/**
 * Response object for the REST API.
 *
 * @author Cain
 */
public class Response {
    private Object result;

    private String message;

    private boolean successful;

    public Response(Object result) {
        setResult(result);
        setSuccessful(true);
    }

    public Response(String message, boolean successful) {
        setMessage(message);
        setSuccessful(successful);
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}
