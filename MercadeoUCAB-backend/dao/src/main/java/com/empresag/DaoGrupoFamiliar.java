package com.empresag;

import javax.persistence.EntityManager;

public class DaoGrupoFamiliar extends Dao<GrupoFamiliarEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoGrupoFamiliar( ) {
        super(_handler);
    }
}
