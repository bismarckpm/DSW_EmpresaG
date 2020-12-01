package com.empresag;

public class SolicitudDto extends BaseDto {
    private String tipo;
    private UsuarioDto fkUsuario;

    public SolicitudDto() {
    }

    public SolicitudDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UsuarioDto getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioDto fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
