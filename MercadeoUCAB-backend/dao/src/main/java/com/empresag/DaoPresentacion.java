package com.empresag;

import javax.persistence.EntityManager;

public class DaoPresentacion extends Dao<PresentacionEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    public DaoPresentacion(){
        super(_handler);
    }
}
