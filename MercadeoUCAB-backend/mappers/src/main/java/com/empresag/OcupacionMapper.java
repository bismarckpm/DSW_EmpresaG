package com.empresag;

public class OcupacionMapper {

    public static OcupacionEntity mapIntToEntity (long ocupacion){

        OcupacionEntity entity = new OcupacionEntity(ocupacion);

        return entity;

    }

}
