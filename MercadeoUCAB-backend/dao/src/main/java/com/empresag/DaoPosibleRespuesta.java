package com.empresag;

import javax.persistence.EntityManager;

public class DaoPosibleRespuesta extends Dao<PosibleRespuestaEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPosibleRespuesta( ) {
        super(_handler);
    }
}
