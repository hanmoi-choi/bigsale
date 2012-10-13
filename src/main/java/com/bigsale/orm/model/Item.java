package com.bigsale.orm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 13/10/12
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID", nullable = false, unique = true)
    private int itemId;

    @Column(name = "ITEM_NAME", nullable = false)
    @Getter
    @Setter
    private String itemName;

    @Column(name = "STOCK_QUANTITY", nullable = false)
    @Getter
    @Setter
    private int stockQuantity;

    @Column(name = "PRICE", nullable = false)
    @Getter
    @Setter
    private int price;

    @Column(name = "DISCOUNT_RATE", nullable = false)
    @Getter
    @Setter
    private int discountRate;

    @Column(name = "DESCRIPTION", nullable = false)
    @Getter
    @Setter
    @Lob
    private String description;

//    @Column(name = "Seller", nullable = false)
//    @Getter
//    @Setter
//    private List<Seller> seller;
}

