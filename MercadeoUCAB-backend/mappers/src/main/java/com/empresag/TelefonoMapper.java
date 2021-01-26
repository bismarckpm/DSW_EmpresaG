package com.empresag;

public class TelefonoMapper {

    public static TelefonoEntity mapStringToEntity(String telefono, PersonaEntity persona){

        TelefonoEntity entity = new TelefonoEntity();

        entity.setNumero(telefono);
        entity.setFkPersona(persona);

        return entity;
    }


}
