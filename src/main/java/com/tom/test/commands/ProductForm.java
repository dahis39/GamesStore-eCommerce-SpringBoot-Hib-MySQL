package com.tom.test.commands;

import com.tom.test.domain.Developer;
import com.tom.test.domain.Publisher;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tom on 8/2/2016.
 */
public class ProductForm {
    private Integer productId;
    private Integer productVersion;
    @NotEmpty
    private String productName;
    private String productDescription;
    @NotEmpty
    private String productImageUrl;
    @NotEmpty
    private String productYoutubeUrl;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal productPrice;
    private Developer productDeveloper;
    private Publisher productPublisher;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(Integer productVersion) {
        this.productVersion = productVersion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductYoutubeUrl() {
        return productYoutubeUrl;
    }

    public void setProductYoutubeUrl(String productYoutubeUrl) {
        this.productYoutubeUrl = productYoutubeUrl;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Developer getProductDeveloper() {
        return productDeveloper;
    }

    public void setProductDeveloper(Developer productDeveloper) {
        this.productDeveloper = productDeveloper;
    }

    public Publisher getProductPublisher() {
        return productPublisher;
    }

    public void setProductPublisher(Publisher productPublisher) {
        this.productPublisher = productPublisher;
    }
}
