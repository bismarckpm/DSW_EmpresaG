package com.empresag;

import javax.persistence.EntityManager;

public class DaoGenero extends Dao<GeneroEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoGenero( ) {
        super(_handler);
    }
}
