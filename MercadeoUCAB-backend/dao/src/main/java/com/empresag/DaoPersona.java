package com.empresag;

import javax.persistence.EntityManager;

public class DaoPersona extends Dao<PersonaEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersona( ) {
        super(_handler);
    }
}
