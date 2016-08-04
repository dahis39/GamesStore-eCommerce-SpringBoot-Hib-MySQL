package com.tom.test.services.reposervices;

import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.Product;
import com.tom.test.domain.Publisher;
import com.tom.test.repositories.PublisherRepository;
import com.tom.test.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/21/2016.
 */
@Service
public class PublisherServiceRepoImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<?> listAll() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        publisherRepository.findAll().forEach(publishers::add);
        return publishers;
    }

    @Override
    public Publisher getById(Integer id) {
        return publisherRepository.findOne(id);
    }

    @Override
    public Publisher saveOrUpdate(Publisher domainObject) {
        return publisherRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        publisherRepository.findOne(id).getProducts().forEach(product -> product.setPublisher(publisherRepository.findOne(1)));
        publisherRepository.delete(id);
    }
}
