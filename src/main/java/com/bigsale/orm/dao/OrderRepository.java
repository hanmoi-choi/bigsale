package com.bigsale.orm.dao;

import com.bigsale.orm.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Order: hanmoi
 * Date: 29/09/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("orderRepository")
@Transactional
public class OrderRepository extends AbstractRepository<Order, String> {

    public static final String ENTITY_NAME = "com.bigsale.orm.model.Order";
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
    public void add(Order instance)
    {
        hibernateTemplate.save(instance);

    }

    @Override
    public void update(Order instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(Order instance)
    {
        hibernateTemplate.delete(instance);

    }

    @Override
    public Order findById(String id)
    {
        return (Order) hibernateTemplate.get(ENTITY_NAME, id);
    }

    @Override
    public List<Order> findAll()
    {
        return hibernateTemplate.loadAll(Order.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }

}
