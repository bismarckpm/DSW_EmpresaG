package com.empresag;

import javax.persistence.EntityManager;

public class DaoRol extends Dao<RolEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRol( ) {
        super(_handler);
    }
}
