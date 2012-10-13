package com.bigsale.orm.dao;

import com.bigsale.orm.model.Seller;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Seller: hanmoi
 * Date: 29/09/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("sellerRepository")
public class SellerRepository extends AbstractRepository<Seller, String> {

    public static final String ENTITY_NAME = "com.bigsale.orm.model.Seller";
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
    public void add(Seller instance)
    {
        hibernateTemplate.save(instance);

    }

    @Override
    public void update(Seller instance)
    {
        hibernateTemplate.update(instance);
    }

    @Override
    public void delete(Seller instance)
    {
        hibernateTemplate.delete(instance);

    }

    @Override
    public Seller findById(String id)
    {
        return (Seller) hibernateTemplate.get(ENTITY_NAME, id);
    }

    @Override
    public List<Seller> findAll()
    {
        return hibernateTemplate.loadAll(Seller.class);
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return hibernateTemplate.getSessionFactory();
    }

}
