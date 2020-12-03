package com.empresag;

import javax.persistence.EntityManager;

public class DaoPersonaGrupofamiliar extends Dao<PersonaGrupofamiliarEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaGrupofamiliar( ) {
        super(_handler);
    }
}
