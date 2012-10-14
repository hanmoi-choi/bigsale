package com.bigsale.orm.dao;

import com.bigsale.orm.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("userRepository")
public class UserRepository extends AbstractRepository<User, String> {

    public static final String ENTITY_NAME_FOR_USER = "com.bigsale.orm.model.User";
    HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void setHibernateTemplate(SessionFactory sessionFactory)
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);

    }

    @Override
    public void add(User instance)
    {
        hibernateTemplate.save(instance);

    }

    @Override
    public void update(User instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(User instance)
    {
        hibernateTemplate.delete(instance);

    }

    @Override
    public User findById(String id)
    {
        return (User) hibernateTemplate.get(ENTITY_NAME_FOR_USER, id);
    }

    @Override
    public List<User> findAll()
    {
        return hibernateTemplate.loadAll(User.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }

}
