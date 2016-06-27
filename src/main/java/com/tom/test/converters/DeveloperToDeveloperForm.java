package com.tom.test.converters;

import com.tom.test.commands.DeveloperForm;
import com.tom.test.domain.Developer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by tom on 6/27/2016.
 */
@Component
public class DeveloperToDeveloperForm implements Converter<Developer,DeveloperForm>{
    @Override
    public DeveloperForm convert(Developer developer) {
        DeveloperForm developerForm = new DeveloperForm();

        developerForm.setDeveloperId(developer.getId());
        developerForm.setDeveloperVersion(developer.getVersion());
        developerForm.setDeveloperName(developer.getName());
        developerForm.setDeveloperDescription(developer.getDescription());
        developerForm.setDeveloperImageUrl(developer.getImageUrl());

        developerForm.setDeveloperProducts(developer.getProducts());
        return developerForm;
    }
}
