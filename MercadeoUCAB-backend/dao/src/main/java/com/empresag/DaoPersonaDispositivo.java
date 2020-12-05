package com.empresag;

import javax.persistence.EntityManager;

public class DaoPersonaDispositivo extends Dao<PersonaDispositivoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaDispositivo( ) {
        super(_handler);
    }
}
