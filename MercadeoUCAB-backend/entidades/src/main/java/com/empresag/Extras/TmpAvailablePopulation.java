package com.empresag.Extras;

import com.empresag.PersonaEntity;

public class TmpAvailablePopulation {
    private PersonaEntity persona;
    private long preguntas, respuestas;
    private int status;

    public TmpAvailablePopulation(PersonaEntity persona, long respuestas, long preguntas) {
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.persona = persona;
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

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
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

    @Override
    public String toString() {
        return "TmpAvailablePopulation{" +
                "persona=" + persona +
                ", preguntas=" + preguntas +
                ", respuestas=" + respuestas +
                ", status=" + status +
                '}';
    }
}
