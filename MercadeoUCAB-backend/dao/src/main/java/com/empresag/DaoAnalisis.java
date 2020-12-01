package com.empresag;

import javax.persistence.EntityManager;

public class DaoAnalisis extends Dao<AnalisisEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoAnalisis() {
        super(_handler);
    }
}
