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
 * Date: 13/10/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ORDER")
public class ItemOrder {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ORDER_ID", nullable = false, unique = true)
    private int itemOrderId;

    @Column(name = "ORDER_QUANTITY", nullable = false)
    @Getter
    @Setter
    private int itemOrderQuantity;

    @Column(name = "ORDER_DATE", nullable = false)
    @Getter
    @Setter
    private Date itemOrderDate;

    @Column(name = "DELIVERY_STATUS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @Getter
    @Setter
    @Lob
    private DeliveryStatus description;


    @ManyToMany(mappedBy="itemOrders")
    @Getter
    @Setter
    private Set<User> users = new HashSet<User>();

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
