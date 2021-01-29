package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class Dao<T>
{
    private com.empresag.DaoHandler _daoHandler;
    private EntityManager _em;

    public Dao()
    {
    }


    /**
     * @param daoHandler persistence.xml handler
     */
    public Dao( com.empresag.DaoHandler daoHandler )
    {
        _daoHandler = daoHandler;
    }

    public T insert( T entity )
    {
        _em = _daoHandler.getSession();

        try
        {
            _daoHandler.beginTransaction();
            _em.persist( entity );
            _em.flush();
            _daoHandler.finishTransaction();
        }
        catch ( Exception e )
        {
            throw  e;
        }

        return entity;
    }

    public T update( T entity )
    {
        _em = _daoHandler.getSession();
        try
        {
            _daoHandler.beginTransaction();
            _em.merge( entity );
            _em.flush();
            _daoHandler.finishTransaction();

        }
        catch ( Exception e )
        {
           throw  e;
        }
        return entity;
    }

    /**
     * Name:                  delete
     * Description:           method for deleting a record from the DB
     *
     * @param entity entity
     *
     * @author teixbr
     * @since 20/10/17
     */
    public T delete( T entity )
    {
        _em = _daoHandler.getSession();
        try
        {
            _daoHandler.beginTransaction();
            if (!_em.contains(entity)) {
                entity = _em.merge(entity);
            }
            _em.remove( entity );
            _em.flush();
            _daoHandler.finishTransaction();

        }
        catch ( Exception e )
        {
            throw e;
        }
        return entity;
    }

    /**
     * Name:                  findAll
     * Description:           method for collecting all registers from an entity
     *
     * @param type Class
     *
     * @author teixbr
     * @since 20/10/17
     */
    public List<T> findAll( Class<T> type )
    {
        _em = _daoHandler.getSession();

        final CriteriaBuilder criteriaBuilder = _em.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery( type );
        final Root<T> root = criteriaQuery.from( type );
        final List<T> list;

        try
        {
            criteriaQuery.select( root );

            list = _em.createQuery( criteriaQuery ).getResultList();

        }
        catch ( Exception e )
        {
            throw e;
        }

        return list;
    }

    /**
     * Name:                  find
     * Description:           method used for find a single record by it's ID
     *
     * @param id id of entity to search
     * @param type Class
     *
     * @author teixbr
     * @since 20/10/17
     */
    public T find( Long id, Class<T> type )
    {
        _em = _daoHandler.getSession();
        final T entity;

        try
        {
            final BaseEntity base = (BaseEntity) _em.find( type, id );
            base.get_id();
            entity = ( T ) base;
        }
        catch ( Exception e )
        {
            throw e;
        }
        return entity;
    }

    /**
     * Name:                  getDaoHandler
     * Description:           method that returns the daoHandler
     *
     * @return instance of daoHandler
     *
     * @author soarje
     * @since 10/24/17
     */
    public com.empresag.DaoHandler getDaoHandler()
    {
        return _daoHandler;
    }
}
