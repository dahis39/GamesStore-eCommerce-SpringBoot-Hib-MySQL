package com.tom.test.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Created by tom on 6/8/2016.
 */
@Entity
public class User extends AbstartDomainClass {
    private String userName;
    private String email;
    private String password;
    private String encryptedPassword;

    @Embedded
    private Customer customer;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
