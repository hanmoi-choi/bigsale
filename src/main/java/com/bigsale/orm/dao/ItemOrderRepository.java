package com.bigsale.orm.dao;

import com.bigsale.orm.model.ItemOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ItemOrder: hanmoi
 * Date: 29/09/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("itemOrderRepository")
public class ItemOrderRepository extends AbstractRepository<ItemOrder, Integer> {

    public static final String ENTITY_NAME = "com.bigsale.orm.model.ItemOrder";
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
    public void add(ItemOrder instance)
    {
        hibernateTemplate.save(instance);

    }

    @Override
    public void update(ItemOrder instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(ItemOrder instance)
    {
        hibernateTemplate.delete(instance);

    }

    @Override
    public ItemOrder findById(Integer id)
    {
        return (ItemOrder) hibernateTemplate.get(ENTITY_NAME, id);
    }

    @Override
    public List<ItemOrder> findAll()
    {
        return hibernateTemplate.loadAll(ItemOrder.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }

}
