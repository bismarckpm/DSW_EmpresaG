package com.empresag;

import javax.persistence.EntityManager;

public class DaoSolicitudEstudio extends Dao<SolicitudEstudioEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSolicitudEstudio( ) {
        super(_handler);
    }
}
