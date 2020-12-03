package com.empresag;

import javax.persistence.EntityManager;

public class DaoTipo extends Dao<TipoEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    public DaoTipo(){
        super(_handler);
    }
}
