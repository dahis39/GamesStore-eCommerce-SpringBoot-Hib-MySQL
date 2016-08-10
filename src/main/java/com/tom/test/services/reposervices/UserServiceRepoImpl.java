package com.tom.test.services.reposervices;

import com.tom.test.commands.UserForm;
import com.tom.test.converters.UserFormToUser;
import com.tom.test.converters.UserToUserForm;
import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.User;
import com.tom.test.repositories.RoleRepository;
import com.tom.test.repositories.UserRepository;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/8/2016.
 */
@Service
public class UserServiceRepoImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFormToUser userFormToUser;
    @Autowired
    private UserToUserForm userToUserForm;

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserForm findUserFormById(Integer id) {
        return userToUserForm.convert(userRepository.findOne(id));
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return userRepository.save(domainObject);
    }

    @Override
    public User saveOrUpdateUserForm(UserForm userForm) {
        return userRepository.save(userFormToUser.convert(userForm));
    }

    @Override
    public void delete(Integer id) {
        User temp = userRepository.findOne(id);
        temp.removeAllRoles();
        userRepository.save(temp);
        userRepository.delete(id);
    }
}
