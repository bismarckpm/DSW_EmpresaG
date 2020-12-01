package com.empresag;

import javax.persistence.EntityManager;

public class DaoDisponibilidad extends Dao<DisponibilidadEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoDisponibilidad( ) {
        super(_handler);
    }
}
