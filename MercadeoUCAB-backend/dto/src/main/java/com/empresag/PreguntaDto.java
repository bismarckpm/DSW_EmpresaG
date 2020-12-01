package com.empresag;

public class PreguntaDto extends BaseDto {
    private String pregunta;
    private int status;
    private TipoDto fkTipo;

    public PreguntaDto() {
    }

    public PreguntaDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TipoDto getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoDto fkTipo) {
        this.fkTipo = fkTipo;
    }
}
