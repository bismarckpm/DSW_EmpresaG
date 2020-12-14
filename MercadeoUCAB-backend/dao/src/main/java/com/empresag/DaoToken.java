package com.empresag;

import javax.persistence.EntityManager;

public class DaoToken extends Dao<TokenEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    public DaoToken(){
        super(_handler);
    }
}
