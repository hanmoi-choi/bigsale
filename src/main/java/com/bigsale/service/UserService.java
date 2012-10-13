package com.bigsale.service;

import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.orm.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();

    boolean checkIdDuplication(String userId);

    @Transactional
    List<User> findUserBySearchCriteria(UserSearchDto userSearchDto);

    void updateUserInfoWithLogin(User user);
}
