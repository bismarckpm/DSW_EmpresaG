package com.empresag;

import javax.persistence.EntityManager;

public class DaoCategoriaSubcategoria extends Dao<CategoriaSubcategoriaEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoCategoriaSubcategoria( ) {
        super(_handler);
    }
}
