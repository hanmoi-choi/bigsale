package com.bigsale.orm.dao;

import com.bigsale.orm.model.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Item: hanmoi
 * Date: 29/09/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("itemRepository")
@Transactional
public class ItemRepository extends AbstractRepository<Item, Integer> {

    public static final String ENTITY_NAME = "com.bigsale.orm.model.Item";
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
    public void add(Item instance)
    {
        hibernateTemplate.save(instance);

    }

    @Override
    public void update(Item instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(Item instance)
    {
        hibernateTemplate.delete(instance);

    }

    @Override
    public Item findById(Integer id)
    {
        return (Item) hibernateTemplate.get(ENTITY_NAME, id);
    }

    @Override
    public List<Item> findAll()
    {
        return hibernateTemplate.loadAll(Item.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }

}
