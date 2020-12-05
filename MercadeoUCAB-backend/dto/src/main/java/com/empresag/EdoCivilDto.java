package com.empresag;

public class EdoCivilDto extends BaseDto {

    private String nombre;

    public EdoCivilDto() {
    }

    public EdoCivilDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EdoCivilDto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
