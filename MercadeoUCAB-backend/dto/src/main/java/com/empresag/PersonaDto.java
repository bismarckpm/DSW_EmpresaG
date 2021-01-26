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
    private int numero_personas_encasa;
    private PersonaDto[] hijos;
    private int[] dispositivos;
    private String telefono;
    private DisponibilidadDto id_horario_inicial;
    private DisponibilidadDto id_horario_final;
    private long id_nivel_academico;
    private long ocupacion;

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

    public int getNumero_personas_encasa() {
        return numero_personas_encasa;
    }

    public void setNumero_personas_encasa(int numero_personas_encasa) {
        this.numero_personas_encasa = numero_personas_encasa;
    }

    public PersonaDto[] getHijos() {
        return hijos;
    }

    public void setHijos(PersonaDto[] hijos) {
        this.hijos = hijos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int[] getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(int[] dispositivos) {
        this.dispositivos = dispositivos;
    }

    public DisponibilidadDto getId_horario_inicial() {
        return id_horario_inicial;
    }

    public void setId_horario_inicial(DisponibilidadDto id_horario_inicial) {
        this.id_horario_inicial = id_horario_inicial;
    }

    public DisponibilidadDto getId_horario_final() {
        return id_horario_final;
    }

    public void setId_horario_final(DisponibilidadDto id_horario_final) {
        this.id_horario_final = id_horario_final;
    }

    public long getId_nivel_academico() {
        return id_nivel_academico;
    }

    public void setId_nivel_academico(long id_nivel_academico) {
        this.id_nivel_academico = id_nivel_academico;
    }

    public long getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(long ocupacion) {
        this.ocupacion = ocupacion;
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
                ", numero_personas_encasa=" + numero_personas_encasa +
                ", hijos=" + Arrays.toString(hijos) +
                ", dispositivos=" + Arrays.toString(dispositivos) +
                ", telefono=" + telefono +
                ", id_horario_inicial=" + id_horario_inicial +
                ", id_horario_final=" + id_horario_final +
                ", id_nivel_academico=" + id_nivel_academico +
                '}';
    }
}
