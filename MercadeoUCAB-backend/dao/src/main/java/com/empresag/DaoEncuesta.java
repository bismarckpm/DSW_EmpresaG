package com.empresag;

import javax.persistence.EntityManager;

public class DaoEncuesta extends Dao<EncuestaEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuesta( ) {
        super(_handler);
    }
}
