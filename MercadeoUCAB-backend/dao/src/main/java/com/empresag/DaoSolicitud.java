package com.empresag;

import javax.persistence.EntityManager;

public class DaoSolicitud extends Dao<SolicitudEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSolicitud( ) {
        super(_handler);
    }
}
