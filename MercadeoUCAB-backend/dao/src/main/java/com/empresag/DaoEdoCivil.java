package com.empresag;

import javax.persistence.EntityManager;

public class DaoEdoCivil extends Dao<EdoCivilEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEdoCivil( ) {
        super(_handler);
    }
}
