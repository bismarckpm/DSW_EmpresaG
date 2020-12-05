package com.empresag;

import javax.persistence.EntityManager;

public class DaoFiltro extends Dao<FiltroEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoFiltro( ) {
        super(_handler);
    }
}
