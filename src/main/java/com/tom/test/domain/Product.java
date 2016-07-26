package com.tom.test.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tom on 6/8/2016.
 */
@Entity
public class Product extends CommonGoodDetails {

    private String youtubeUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "DEVELOPER_ID")
    private Developer developer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @ManyToMany(mappedBy = "products")
    private Set<Bundle> bundles = new HashSet<Bundle>();

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(Set<Bundle> bundles) {
        this.bundles = bundles;
    }

    public void addBundle(Bundle bundle){
        if (bundle == null)
            throw new NullPointerException("Can't add null bundle.");
        getBundles().add(bundle);
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
