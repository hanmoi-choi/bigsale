package com.bigsale.orm.model;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 13/10/12
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public enum DeliveryStatus {
    PREPARING(0), DELIVERED(1), ARRIVING(2), UNKNOWN(-1);

    private int value;

    DeliveryStatus(int value)
    {
        this.value = value;
    }

    // the identifierMethod
    public int toInt()
    {
        return value;
    }

    // the valueOfMethod
    public static DeliveryStatus fromInt(int value)
    {
        switch (value)
        {
            case 0:
                return PREPARING;
            case 1:
                return DELIVERED;
            case 2:
                return ARRIVING;
            default:
                return UNKNOWN;
        }
    }

    public String toString()
    {
        switch (this)
        {
            case PREPARING:
                return "preparing";
            case DELIVERED:
                return "delivered";
            case ARRIVING:
                return "arriving";
        }
        return "unknown";
    }
}
