package com.bigsale.orm.model;

public enum Level {
    PLATINUM(0, null),GOLD(1, PLATINUM),SILVER(2, GOLD),  BRONZE(3, SILVER),
    UNKNOWN(-1, null);

    private int value;
    private Level nextLevel;

    Level(int value, Level nextLevel)
    {
        this.value = value;
        this.nextLevel = nextLevel;
    }

    // the identifierMethod
    public int toInt()
    {
        return value;
    }

    // the valueOfMethod
    public static Level fromInt(int value)
    {
        switch (value)
        {
            case 0:
                return PLATINUM;
            case 1:
                return GOLD;
            case 2:
                return SILVER;
            case 3:
                return BRONZE;
            default:
                return UNKNOWN;
        }
    }

    public Level nextLevel(){
        return this.nextLevel;
    }

    public String toString()
    {
        switch (this)
        {
            case BRONZE:
                return "BRONZE";
            case SILVER:
                return "SILVER";
            case GOLD:
                return "GOLD";
            case PLATINUM:
        }
        return "unknown";
    }
    }
