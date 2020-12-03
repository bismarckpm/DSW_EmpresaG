package com.empresag;

import javax.persistence.EntityManager;

public class DaoPersonaDisponibilidad extends Dao<PersonaDisponibilidadEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaDisponibilidad( ) {
        super(_handler);
    }
}
