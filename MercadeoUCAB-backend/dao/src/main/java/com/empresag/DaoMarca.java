package com.empresag;

import javax.persistence.EntityManager;

public class DaoMarca extends Dao<MarcaEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    public DaoMarca(){
        super(_handler);
    }
}
