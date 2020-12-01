package com.empresag;

import javax.persistence.EntityManager;

public class DaoCategoria extends Dao<CategoriaEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoCategoria() {
        super(_handler);
    }
}
