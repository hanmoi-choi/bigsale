package com.bigsale.orm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 13/10/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID", nullable = false, unique = true)
    private int orderId;

    @Column(name = "ORDER_QUANTITY", nullable = false)
    @Getter
    @Setter
    private int orderQuantity;

    @Column(name = "ORDER_DATE", nullable = false)
    @Getter
    @Setter
    private Date orderDate;

    @Column(name = "ITEM_ID", nullable = false)
    @Getter
    @Setter
    private int itemId;

    @Column(name = "DELIVERY_STATUS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @Getter
    @Setter
    @Lob
    private DeliveryStatus description;
}
