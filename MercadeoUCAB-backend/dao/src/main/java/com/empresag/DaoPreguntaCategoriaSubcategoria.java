package com.empresag;

import javax.persistence.EntityManager;

public class DaoPreguntaCategoriaSubcategoria extends Dao<PreguntaCatSubcatEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPreguntaCategoriaSubcategoria(){
        super(_handler);
    }
}
