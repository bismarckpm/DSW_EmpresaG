package com.empresag;

import javax.persistence.EntityManager;

public class DaoOcupacion extends Dao<OcupacionEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOcupacion( ) {
        super(_handler);
    }
}
