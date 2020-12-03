package com.empresag;

import javax.json.bind.annotation.JsonbDateFormat;
import java.sql.Date;
import java.util.Arrays;

public class PersonaDto extends BaseDto {
    private String documentoIdentidad;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    @JsonbDateFormat(value = "dd/MM/yyyy")
    private Date fechaNacimiento;
    private GeneroDto fkGenero;
    private EdoCivilDto fkEdoCivil;
    private PersonaDto fkPersona;
    private LugarDto fkLugar;
    private PersonaDto[] hijos;

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GeneroDto getFkGenero() {
        return fkGenero;
    }

    public void setFkGenero(GeneroDto fkGenero) {
        this.fkGenero = fkGenero;
    }

    public EdoCivilDto getFkEdoCivil() {
        return fkEdoCivil;
    }

    public void setFkEdoCivil(EdoCivilDto fkEdoCivil) {
        this.fkEdoCivil = fkEdoCivil;
    }

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public LugarDto getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarDto fkLugar) {
        this.fkLugar = fkLugar;
    }

    public PersonaDto[] getHijos() {
        return hijos;
    }

    public void setHijos(PersonaDto[] hijos) {
        this.hijos = hijos;
    }

    @Override
    public String toString() {
        return "PersonaDto{" +
                "documentoIdentidad='" + documentoIdentidad + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fkGenero=" + fkGenero +
                ", fkEdoCivil=" + fkEdoCivil +
                ", fkPersona=" + fkPersona +
                ", fkLugar=" + fkLugar +
                ", hijos=" + Arrays.toString(hijos) +
                '}';
    }
}
