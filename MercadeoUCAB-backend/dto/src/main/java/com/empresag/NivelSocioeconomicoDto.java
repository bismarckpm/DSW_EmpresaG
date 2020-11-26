package com.empresag;

public class NivelSocioeconomicoDto extends BaseDto {
    private String nombre;

    public NivelSocioeconomicoDto() {
    }

    public NivelSocioeconomicoDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
