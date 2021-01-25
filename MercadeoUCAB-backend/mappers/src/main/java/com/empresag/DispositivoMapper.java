package com.empresag;

import java.util.Objects;

public class DispositivoMapper {

    public static DispositivoEntity mapIntToEntity( int id )
    {
        DispositivoEntity entity = new DispositivoEntity(id);

        return entity;
    }

}
