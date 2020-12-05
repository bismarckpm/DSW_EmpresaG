package com.empresag;

import javax.persistence.EntityManager;

public class DaoPersonaOcupacion extends Dao<PersonaOcupacionEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaOcupacion( ) {
        super(_handler);
    }
}
