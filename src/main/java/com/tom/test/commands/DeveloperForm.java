package com.tom.test.commands;

import com.tom.test.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/25/2016.
 */
public class DeveloperForm {
    private Integer developerId;
    private Integer developerVersion;
    private String developerName;
    private String developerDescription;
    private String developerImageUrl;
    private List<Product> developerProducts = new ArrayList<>();

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getDeveloperVersion() {
        return developerVersion;
    }

    public void setDeveloperVersion(Integer developerVersion) {
        this.developerVersion = developerVersion;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperDescription() {
        return developerDescription;
    }

    public void setDeveloperDescription(String developerDescription) {
        this.developerDescription = developerDescription;
    }

    public String getDeveloperImageUrl() {
        return developerImageUrl;
    }

    public void setDeveloperImageUrl(String developerImageUrl) {
        this.developerImageUrl = developerImageUrl;
    }

    public List<Product> getDeveloperProducts() {
        return developerProducts;
    }

    public void setDeveloperProducts(List<Product> developerProducts) {
        this.developerProducts = developerProducts;
    }
}
