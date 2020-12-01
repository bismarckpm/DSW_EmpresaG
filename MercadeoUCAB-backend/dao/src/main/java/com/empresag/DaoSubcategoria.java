package com.empresag;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoSubcategoria extends Dao<SubcategoriaEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSubcategoria(){
        super(_handler);
    }
}
