package com.empresag;

import javax.persistence.EntityManager;

public class DaoPersonaNvlacademico extends Dao<PersonaNvlacademicoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaNvlacademico( ) {
        super(_handler);
    }
}
