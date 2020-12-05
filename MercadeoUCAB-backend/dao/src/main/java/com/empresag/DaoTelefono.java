package com.empresag;

import javax.persistence.EntityManager;

public class DaoTelefono extends Dao<TelefonoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTelefono( ) {
        super(_handler);
    }
}
