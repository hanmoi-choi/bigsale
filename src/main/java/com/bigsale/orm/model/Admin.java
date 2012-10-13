package com.bigsale.orm.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 2/09/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ADMIN")
public class Admin {

    @Id
    @Column(name = "ADMIN_ID", nullable = false, unique = true)
    @Getter
    @Setter
    private String adminId;


    @Column(name = "PASSWORD", nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(name = "EMAIL", nullable = true)
    @Getter
    @Setter
    private String email;

}
