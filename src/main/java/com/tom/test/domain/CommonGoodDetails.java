package com.tom.test.domain;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * Created by tom on 6/8/2016.
 */
@MappedSuperclass
public class CommonGoodDetails extends AbstartDomainClass{

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
