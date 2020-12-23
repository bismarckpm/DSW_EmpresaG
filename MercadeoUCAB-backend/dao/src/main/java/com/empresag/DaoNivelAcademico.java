package com.empresag;

import javax.persistence.EntityManager;

public class DaoNivelAcademico extends Dao<NivelAcademicoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoNivelAcademico( ) {
        super(_handler);
    }
}
