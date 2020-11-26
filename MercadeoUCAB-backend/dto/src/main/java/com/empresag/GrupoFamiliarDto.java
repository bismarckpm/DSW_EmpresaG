package com.empresag;

public class GrupoFamiliarDto extends BaseDto {
    private String nombre;

    public GrupoFamiliarDto() {
    }

    public GrupoFamiliarDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
