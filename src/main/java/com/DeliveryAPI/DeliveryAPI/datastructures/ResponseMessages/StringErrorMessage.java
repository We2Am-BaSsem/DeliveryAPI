package com.DeliveryAPI.DeliveryAPI.datastructures.ResponseMessages;

/**
 * The String error message class.
 */
public class StringErrorMessage extends ResponseMessage {
    private String errorMessage;

    /**
     * Instantiates a new String error message.
     *
     * @param errorMessage the error message
     */
    public StringErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
