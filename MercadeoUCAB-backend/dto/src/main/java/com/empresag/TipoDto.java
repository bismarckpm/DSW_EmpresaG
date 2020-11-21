package com.empresag;

public class TipoDto extends BaseDto {
    private String nombre;
    private String descripcion;

    public TipoDto() {
    }

    public TipoDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
