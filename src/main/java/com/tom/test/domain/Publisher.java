package com.tom.test.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tom on 6/9/2016.
 */
@Entity
public class Publisher{


    @Id
    @JoinColumn(nullable = false)
    private String name;

    private String description;

    private String imageUrl;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PUBLISHER_NAME")
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }


    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps(){
        lastUpdated = new Date();
        if (dateCreated == null)
            dateCreated = new Date();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
