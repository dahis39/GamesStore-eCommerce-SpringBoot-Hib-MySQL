package com.tom.test.services;

import com.tom.test.commands.DeveloperForm;
import com.tom.test.domain.Developer;

/**
 * Created by tom on 6/13/2016.
 */
public interface DeveloperService extends CRUDservice<Developer> {
    Developer saveOrUpdateDeveloperForm(DeveloperForm developerForm);
    DeveloperForm getDeveloperFormById(Integer id);
}
