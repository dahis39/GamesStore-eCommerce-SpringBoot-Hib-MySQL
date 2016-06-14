package com.tom.test.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tom on 6/8/2016.
 */
@Entity
public class Product extends CommonGoodDetails {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEVELOPER_ID")
    private Developer developer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PUBLISHER_NAME")
    private Publisher publisher;

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

    @Override
    public String toString() {
        return this.getName();
    }
}
