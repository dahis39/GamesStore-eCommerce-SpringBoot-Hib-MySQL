package com.tom.test.commands;

import com.tom.test.domain.Product;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 7/5/2016.
 */
public class BundleForm {
    private Integer bundleId;
    private Integer bundleVersion;
    @NotEmpty
    private String bundleName;
    private String bundleDescription;
    @NotEmpty
    private String bundleImageUrl;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal bundlePrice;
    private List<Integer> bundlePruductIds = new ArrayList<>();

    public Integer getBundleId() {
        return bundleId;
    }

    public void setBundleId(Integer bundleId) {
        this.bundleId = bundleId;
    }

    public Integer getBundleVersion() {
        return bundleVersion;
    }

    public void setBundleVersion(Integer bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleDescription() {
        return bundleDescription;
    }

    public void setBundleDescription(String bundleDescription) {
        this.bundleDescription = bundleDescription;
    }

    public String getBundleImageUrl() {
        return bundleImageUrl;
    }

    public void setBundleImageUrl(String bundleImageUrl) {
        this.bundleImageUrl = bundleImageUrl;
    }

    public BigDecimal getBundlePrice() {
        return bundlePrice;
    }

    public void setBundlePrice(BigDecimal bundlePrice) {
        this.bundlePrice = bundlePrice;
    }

    public List<Integer> getBundlePruductIds() {
        return bundlePruductIds;
    }

    public void setBundlePruductIds(List<Integer> bundlePruductIds) {
        this.bundlePruductIds = bundlePruductIds;
    }
}
