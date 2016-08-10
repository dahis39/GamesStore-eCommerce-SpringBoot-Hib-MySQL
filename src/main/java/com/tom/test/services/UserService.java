package com.tom.test.services;

import com.tom.test.commands.UserForm;
import com.tom.test.domain.User;

/**
 * Created by tom on 6/8/2016.
 */
public interface UserService extends CRUDservice<User> {
    User saveOrUpdateUserForm(UserForm userForm);
    UserForm findUserFormById(Integer id);
}
