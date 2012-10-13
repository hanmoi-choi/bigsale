package com.bigsale.service;

import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.orm.dao.Repository;
import com.bigsale.orm.model.User;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    Repository userRepository;

    @Override
    public void addUser(User user)
    {
        userRepository.add(user);
    }

    @Override
    public void updateUser(User user)
    {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }

    @Override
    public User getUserById(String userId)
    {
        return (User) userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public boolean checkIdDuplication(String userId)
    {
        User user = (User) userRepository.findById(userId);
        return user != null;
    }

    @Override
    @Transactional
    public List<User> findUserBySearchCriteria(UserSearchDto userSearchDto){
        Session session = userRepository.getSessionFactory().openSession();
        session.beginTransaction();

        List<User> userList = (List<User>) session.createCriteria(User.class)
                .add(Restrictions.ilike("userId", userSearchDto.getUserId(), MatchMode.ANYWHERE))
                .addOrder(Order.asc("userId"))
                .list();

        session.close();

        return userList;
    }

    @Override
    public void updateUserInfoWithLogin(User user)
    {
        user.increaseLoginCount();
        user.UpdateLevel();
        userRepository.update(user);
    }
}
