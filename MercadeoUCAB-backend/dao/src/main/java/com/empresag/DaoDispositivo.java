package com.empresag;

import javax.persistence.EntityManager;

public class DaoDispositivo extends Dao<DispositivoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoDispositivo( ) {
        super(_handler);
    }
}
