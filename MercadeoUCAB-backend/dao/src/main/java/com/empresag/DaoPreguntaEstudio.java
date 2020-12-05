package com.empresag;

import javax.persistence.EntityManager;

public class DaoPreguntaEstudio extends Dao<PreguntaEstudioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPreguntaEstudio( ) {
        super(_handler);
    }
}
