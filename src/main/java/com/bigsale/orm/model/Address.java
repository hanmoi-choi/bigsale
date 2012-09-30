package com.bigsale.orm.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "address")
public class Address {
    public Address() {
        users = new ArrayList<User>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDR_ID", nullable = false, unique = true)
    private int addrId;

    @Getter
    @Setter
    @Column(name = "ADDR_STREET", nullable = false)
    public String street;


    @Getter
    @Setter
    @Column(name = "ADDR_CITY", nullable = false)
    public String city;


    @Getter
    @Setter
    @Column(name = "ADDR_STATE", nullable = false)
    public String state;


    @Getter
    @Setter
    @Column(name = "ADDR_ZIP_CODE", nullable = false)
    public String zipcode;

    @Getter
    @Setter
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="ADDR_ID")
    @IndexColumn(name="USER_IDX")
    private List<User> users;
}
