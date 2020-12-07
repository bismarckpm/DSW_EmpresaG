package com.empresag;

import java.util.List;

public class PreguntaDto extends BaseDto {
    private String pregunta;
    private int status;
    private TipoPreguntaDto fkTipoPregunta;
    private List<OpcionDto> listOpciones;

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

    public TipoPreguntaDto getFkTipoPregunta() {
        return fkTipoPregunta;
    }

    public void setFkTipoPregunta(TipoPreguntaDto fkTipoPregunta) {
        this.fkTipoPregunta = fkTipoPregunta;
    }

    public List<OpcionDto> getListOpciones() {
        return listOpciones;
    }

    public void setListOpciones(List<OpcionDto> listOpciones) {
        this.listOpciones = listOpciones;
    }

    @Override
    public String toString() {
        return "PreguntaDto{" +
                "pregunta='" + pregunta + '\'' +
                ", status=" + status +
                '}';
    }
}
