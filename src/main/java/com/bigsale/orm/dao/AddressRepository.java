package com.bigsale.orm.dao;

import com.bigsale.orm.model.Address;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/09/12
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("addressRepository")
public class AddressRepository extends AbstractRepository<Address, String> {
    public static final String ENTITY_NAME = "com.bigsale.orm.model.Address";
    HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void add(Address instance)
    {
        hibernateTemplate.save(instance);
    }

    @Override
    public void update(Address instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(Address instance)
    {
        hibernateTemplate.delete(instance);
    }

    @Override
    public Address findById(String id)
    {
        return (Address) hibernateTemplate.get(ENTITY_NAME, id);
    }

    @Override
    public List<Address> findAll()
    {
        return hibernateTemplate.loadAll(Address.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }
}
