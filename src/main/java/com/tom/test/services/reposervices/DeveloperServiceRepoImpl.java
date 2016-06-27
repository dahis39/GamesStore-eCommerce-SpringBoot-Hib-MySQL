package com.tom.test.services.reposervices;

import com.tom.test.commands.DeveloperForm;
import com.tom.test.converters.DeveloperFormToDeveloper;
import com.tom.test.converters.DeveloperToDeveloperForm;
import com.tom.test.domain.Developer;
import com.tom.test.repositories.DeveloperRepository;
import com.tom.test.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/13/2016.
 */
@Service
public class DeveloperServiceRepoImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private DeveloperFormToDeveloper developerFormToDeveloper;

    @Autowired
    private DeveloperToDeveloperForm developerToDeveloperForm;

    @Override
    public List<Developer> listAll() {
        ArrayList<Developer> developers = new ArrayList<>();
        developerRepository.findAll().forEach(developers::add);
        return developers;
    }

    @Override
    public Developer getById(Integer id) {
        return developerRepository.findOne(id);
    }

    @Override
    public Developer saveOrUpdate(Developer domainObject) {
        return developerRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        developerRepository.delete(id);
    }

    @Override
    public Developer saveOrUpdateDeveloperForm(DeveloperForm developerForm) {
        System.out.println("Before converting method");
        Developer developer = developerFormToDeveloper.convert(developerForm);
        System.out.println("Converted");
        return developerRepository.save(developer);
    }

    @Override
    public DeveloperForm getDeveloperFormById(Integer id) {
        return developerToDeveloperForm.convert(developerRepository.findOne(id));
    }
}
