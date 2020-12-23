package com.empresag;

public class LugarDto extends BaseDto {
    private String nombre;
    private int tipo;
    private NivelSocioeconomicoDto fkNivelSocioeconomico;
    private LugarDto fkLugar;

    public LugarDto() {
    }

    public LugarDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public NivelSocioeconomicoDto getFkNivelSocioeconomico() {
        return fkNivelSocioeconomico;
    }

    public void setFkNivelSocioeconomico(NivelSocioeconomicoDto fkNivelSocioeconomico) {
        this.fkNivelSocioeconomico = fkNivelSocioeconomico;
    }

    public LugarDto getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarDto fkLugar) {
        this.fkLugar = fkLugar;
    }

    @Override
    public String toString() {
        return "\n{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", fkNivelSocioeconomico=" + fkNivelSocioeconomico +
                ", fkLugar=" + fkLugar +
                '}';
    }
}
