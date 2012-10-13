package com.bigsale.orm.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Seller: hanmoi
 * Date: 2/09/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SELLER")
public class Seller {

    @Id
    @Column(name = "SELLER_ID", nullable = false, unique = true)
    @Getter
    @Setter
    private String sellerId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "SELLER_LEVEL", nullable = false)
    @Getter
    @Setter
    private Level sellerLevel;

    @Column(name = "LOG_IN_COUNT", nullable = false)
    @Getter
    @Setter
    private int loginCount;

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

    public void increaseLoginCount() {
        loginCount++;
    }

    public void UpdateLevel() {
        if(loginCount > 5 && sellerLevel == Level.BRONZE){
            sellerLevel = sellerLevel.nextLevel();
        }
        else if(loginCount > 10 && sellerLevel == Level.SILVER){
            sellerLevel = sellerLevel.nextLevel();
        }
        else if(loginCount > 20 && sellerLevel == Level.GOLD){
            sellerLevel = sellerLevel.nextLevel();
        }
    }

}
