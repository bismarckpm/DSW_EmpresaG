package com.empresag;

import javax.persistence.EntityManager;

public class DaoTipoPresentacion extends Dao<TipoPresentacionEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    public DaoTipoPresentacion(){
        super(_handler);
    }
}
