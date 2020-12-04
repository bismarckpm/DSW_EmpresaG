package com.empresag;

import javax.persistence.EntityManager;

public class DaoOpcion extends Dao<OpcionEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOpcion(){
        super(_handler);
    }
}
