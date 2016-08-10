package com.tom.test.commands;

import com.tom.test.domain.Address;
import com.tom.test.domain.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 8/9/2016.
 */
public class UserForm {
    private Integer userId;
    private Integer userVersion;
    @NotEmpty
    @Size(min = 3,max = 30)
    private String userName;
    private String userEmail;
    @NotEmpty
    @Size(min = 3,max = 30)
    private String userPassword;
    private Address userBillingAddress;
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(Integer userVersion) {
        this.userVersion = userVersion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Address getUserBillingAddress() {
        return userBillingAddress;
    }

    public void setUserBillingAddress(Address userBillingAddress) {
        this.userBillingAddress = userBillingAddress;
    }
}
