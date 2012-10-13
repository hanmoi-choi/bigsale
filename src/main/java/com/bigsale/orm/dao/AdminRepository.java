package com.bigsale.orm.dao;

import com.bigsale.orm.model.Admin;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Admin: hanmoi
 * Date: 29/09/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("adminRepository")
@Transactional
public class AdminRepository extends AbstractRepository<Admin, String> {

    public static final String ENTITY_NAME = "com.bigsale.orm.model.Admin";
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
    public void add(Admin instance)
    {
        hibernateTemplate.save(instance);

    }

    @Override
    public void update(Admin instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(Admin instance)
    {
        hibernateTemplate.delete(instance);

    }

    @Override
    public Admin findById(String id)
    {
        return (Admin) hibernateTemplate.get(ENTITY_NAME, id);
    }

    @Override
    public List<Admin> findAll()
    {
        return hibernateTemplate.loadAll(Admin.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }

}
