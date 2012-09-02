package com.bigsale.model;


import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "USER_ID", nullable = false, unique = true)
    private int userId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_TYPE", nullable = false)
    private UserType type;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_PASSWORD",nullable = false)
    private String password;

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
