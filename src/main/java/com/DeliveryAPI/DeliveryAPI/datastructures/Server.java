package com.DeliveryAPI.DeliveryAPI.datastructures;

/**
 * The Server class used to check the server status.
 */
public class Server {
    private boolean isServerWorking = false;

    /**
     * Check the server status.
     *
     * @return the boolean
     */
    public boolean isServerWorking() {
        return isServerWorking;
    }

    /**
     * Sets server status.
     *
     * @param serverWorking the server working
     */
    public void setServerWorking(boolean serverWorking) {
        isServerWorking = serverWorking;
    }
}
