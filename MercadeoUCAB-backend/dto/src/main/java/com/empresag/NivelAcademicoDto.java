package com.empresag;

public class NivelAcademicoDto extends BaseDto {
    private String nombre;

    public NivelAcademicoDto() {
    }

    public NivelAcademicoDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
