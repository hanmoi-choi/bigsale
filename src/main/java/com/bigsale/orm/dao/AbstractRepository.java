package com.bigsale.orm.dao;

import java.util.List;

public abstract class AbstractRepository<INSTANCE_CLASS, PRIMARY_KEY_CLASS> implements Repository<INSTANCE_CLASS, PRIMARY_KEY_CLASS> {

    @Override
    public void add(INSTANCE_CLASS instance)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(INSTANCE_CLASS instance)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(INSTANCE_CLASS instance)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public INSTANCE_CLASS findById(PRIMARY_KEY_CLASS id)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<INSTANCE_CLASS> findAll()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
