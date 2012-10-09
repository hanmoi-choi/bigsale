package com.bigsale.model;

public enum Role
{
    ADMIN_ROLE("ROLE_ADMIN", 2), SELLER_ROLE("ROLE_SELLER", 1), USER_ROLE("ROLE_BUYER", 0);

    private final String roleName;
    private final int order;

    private Role(final String roleName, final int order)
    {
        this.roleName = roleName;
        this.order = order;
    }

    public int order()
    {
        return order;
    }

    public String roleName()
    {
        return roleName;
    }
}
