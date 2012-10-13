package com.bigsale.orm.dao;

import org.hibernate.SessionFactory;

import java.util.List;

public interface Repository<INSTANCE_CLASS, PRIMARY_KEY_CLASS> {
    void add(INSTANCE_CLASS instance);

    void update(INSTANCE_CLASS instance);

    void delete(INSTANCE_CLASS instance);

    INSTANCE_CLASS findById(PRIMARY_KEY_CLASS id);

    List<INSTANCE_CLASS> findAll();

    SessionFactory getSessionFactory();

    //    INSTANCE_CLASS findById(PRIMARY_KEY_CLASS id, Class fetchingStrategy, Object... args);
//    List<INSTANCE_CLASS> findAll(Class fetchingStrategy, Object... args);
//    List<INSTANCE_CLASS> findAll(int pageNumber, int pageSize);
//    List<INSTANCE_CLASS> findAll(int pageNumber, int pageSize, Class fetchingStrategy, Object... args);
//    List<INSTANCE_CLASS> findAllByCriteria(Criteria criteria, Class fetchingStrategy, Object... args);
//    List<INSTANCE_CLASS> findAllByCriteria(int pageNumber, int pageSize, Criteria criteria);
//    List<INSTANCE_CLASS> findAllByCriteria(int pageNumber, int pageSize, Criteria criteria, Class fetchingStrategy, Object... args);

}
