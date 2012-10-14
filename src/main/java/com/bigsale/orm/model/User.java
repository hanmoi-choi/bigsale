package com.bigsale.orm.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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



    @Id
    @Column(name = "USER_ID", nullable = false, unique = true)
    @Getter
    @Setter
    private String userId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_LEVEL", nullable = false)
    @Getter
    @Setter
    private Level userLevel;

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

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDR_ID")
    private Address address;

    @Getter
    @Setter
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Set<ItemOrder> itemOrders = new HashSet<ItemOrder>();

    public void increaseLoginCount() {
        loginCount++;
    }

    public void UpdateLevel() {
        if(loginCount > 5 && userLevel == Level.BRONZE){
            userLevel = userLevel.nextLevel();
        }
        else if(loginCount > 10 && userLevel == Level.SILVER){
            userLevel = userLevel.nextLevel();
        }
        else if(loginCount > 20 && userLevel == Level.GOLD){
            userLevel = userLevel.nextLevel();
        }
    }

}
