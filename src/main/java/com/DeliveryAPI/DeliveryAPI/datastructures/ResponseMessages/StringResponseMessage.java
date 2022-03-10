package com.DeliveryAPI.DeliveryAPI.datastructures.ResponseMessages;

/**
 * The String response message class.
 */
public class StringResponseMessage extends ResponseMessage {
    private String responseMessage;


    /**
     * Instantiates a new String response message.
     *
     * @param responseMessage the response message
     */
    public StringResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * Gets response message.
     *
     * @return the response message
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets response message.
     *
     * @param responseMessage the response message
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
