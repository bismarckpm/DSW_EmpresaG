package com.empresag;

import javax.persistence.EntityManager;

public class DaoPregunta extends Dao<PreguntaEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPregunta(){
        super(_handler);
    }
}
