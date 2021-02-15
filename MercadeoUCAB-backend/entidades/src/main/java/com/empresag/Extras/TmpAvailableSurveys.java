package com.empresag.Extras;

import com.empresag.FiltroEntity;

public class TmpAvailableSurveys {
    private FiltroEntity filtros;
    private long preguntas, respuestas;
    private int status;

    public TmpAvailableSurveys(FiltroEntity filtros, long preguntas, long respuestas) {
        this.filtros = filtros;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        if (respuestas == 0) {
            this.status = 0;
        }
        else if (respuestas == preguntas){
            this.status = 2;
        }
        else {
            this.status = 1;
        }
    }

    public FiltroEntity getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltroEntity filtros) {
        this.filtros = filtros;
    }

    public long getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(long preguntas) {
        this.preguntas = preguntas;
    }

    public long getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(long respuestas) {
        this.respuestas = respuestas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
