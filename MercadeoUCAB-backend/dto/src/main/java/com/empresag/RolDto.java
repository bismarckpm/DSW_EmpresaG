package com.empresag;

public class RolDto extends BaseDto {
    private String nombre;

    public RolDto() {
    }

    public RolDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
