package com.bigsale.orm.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 2/09/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "USER")
public class User {

    public enum UserType {
        ADMIN(0), BUYER(1), SELLER(2), UNKNOW(-1);

        private int value;

        UserType(int value)
        {
            this.value = value;
        }

        // the identifierMethod
        public int toInt()
        {
            return value;
        }

        // the valueOfMethod
        public static UserType fromInt(int value)
        {
            switch (value)
            {
                case 0:
                    return ADMIN;
                case 1:
                    return BUYER;
                case 2:
                    return SELLER;
                default:
                    return UNKNOW;
            }
        }

        public String toString()
        {
            switch (this)
            {
                case ADMIN:
                    return "admin";
                case BUYER:
                    return "buyer";
                case SELLER:
                    return "seller";
            }
            return "unknown";
        }
    }
    public enum UserLevel {
        BRONZE(0), SILVER(1), GOLD(2), PLATINUM(3), UNKNOW(-1);

        private int value;

        UserLevel(int value)
        {
            this.value = value;
        }

        // the identifierMethod
        public int toInt()
        {
            return value;
        }

        // the valueOfMethod
        public static UserLevel fromInt(int value)
        {
            switch (value)
            {
                case 0:
                    return BRONZE;
                case 1:
                    return SILVER;
                case 2:
                    return GOLD;
                case 3:
                    return PLATINUM;
                default:
                    return UNKNOW;
            }
        }

        public String toString()
        {
            switch (this)
            {
                case BRONZE:
                    return "bronze";
                case SILVER:
                    return "silver";
                case GOLD:
                    return "gold";
                case PLATINUM:
                    return "platinum";
            }
            return "unknown";
        }
    }

    @Id
    @Column(name = "USER_ID", nullable = false, unique = true)
    @Getter
    @Setter
    private String userId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_TYPE", nullable = false)
    @Getter
    @Setter
    private UserType userType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_LEVEL", nullable = false)
    @Getter
    @Setter
    private UserLevel userLevel;

    @Column(name = "PASSWORD", nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(name = "FULL_NAME", nullable = false)
    @Getter
    @Setter
    private String fullName;

    @Column(name = "EMAIL", nullable = true)
    @Getter
    @Setter
    private String email;

    @Column(name = "DATE_CREATED", nullable = false)
    @Getter
    @Setter
    private Date dateCreated;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "addr_id")
    private Address address;

}
