package com.empresag;

import javax.persistence.EntityManager;

public class DaoEstudio extends Dao<EstudioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio( ) {
        super(_handler);
    }
}
