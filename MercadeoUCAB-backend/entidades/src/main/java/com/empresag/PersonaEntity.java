package com.empresag;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "persona", schema = "empresag", catalog = "")
public class PersonaEntity extends BaseEntity{
    @Basic
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    @Basic
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Basic
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Basic
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic
    @Column(name = "fecha_nacimiento")
    @JsonbDateFormat(value = "dd/MM/yyyy")
    private Date fechaNacimiento;
    @ManyToOne
    @JoinColumn(name = "fk_genero")
    private GeneroEntity fkGenero;
    @ManyToOne
    @JoinColumn(name = "fk_edo_civil")
    private EdoCivilEntity fkEdoCivil;
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;

    @Basic
    @Column(name = "numero_personas_encasa")
    private int numero_personas_encasa;

    @ManyToOne
    @JoinColumn(name = "fk_lugar")
    private LugarEntity fkLugar;

    @ManyToOne
    @JoinColumn(name = "fk_disponibilidad_inicial")
    private DisponibilidadEntity fkDisponibilidadInicial;

    @ManyToOne
    @JoinColumn(name = "fk_disponibilidad_final")
    private DisponibilidadEntity fkDisponibilidadFinal;


    public PersonaEntity() {

    }


    @Basic
    @Column(name = "documento_identidad")
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @Basic
    @Column(name = "primer_nombre")
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @Basic
    @Column(name = "segundo_nombre")
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Basic
    @Column(name = "primer_apellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Basic
    @Column(name = "segundo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Basic
    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @ManyToOne
    @JoinColumn(name = "fk_genero")
    public GeneroEntity getFkGenero() {
        return fkGenero;
    }

    public void setFkGenero(GeneroEntity fkGenero) {
        this.fkGenero = fkGenero;
    }

    @ManyToOne
    @JoinColumn(name = "fk_edo_civil")
    public EdoCivilEntity getFkEdoCivil() {
        return fkEdoCivil;
    }

    public void setFkEdoCivil(EdoCivilEntity fkEdoCivil) {
        this.fkEdoCivil = fkEdoCivil;
    }

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_lugar")
    public LugarEntity getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarEntity fkLugar) {
        this.fkLugar = fkLugar;
    }

    @Basic
    @Column(name = "numero_personas_encasa")
    public int getNumero_personas_encasa() {
        return numero_personas_encasa;
    }

    public void setNumero_personas_encasa(int numero_personas_encasa) {
        this.numero_personas_encasa = numero_personas_encasa;
    }

    @ManyToOne
    @JoinColumn(name = "fk_disponibilidad_inicial")
    public DisponibilidadEntity getFkDisponibilidadInicial() {
        return fkDisponibilidadInicial;
    }

    public void setFkDisponibilidadInicial(DisponibilidadEntity fkDisponibilidadInicial) {
        this.fkDisponibilidadInicial = fkDisponibilidadInicial;
    }

    @ManyToOne
    @JoinColumn(name = "fk_disponibilidad_final")
    public DisponibilidadEntity getFkDisponibilidadFinal() {
        return fkDisponibilidadFinal;
    }

    public void setFkDisponibilidadFinal(DisponibilidadEntity fkDisponibilidadFinal) {
        this.fkDisponibilidadFinal = fkDisponibilidadFinal;
    }

    public Integer age(Date fechaNacimiento){
        return 1;
    }

    @Override
    public String toString() {
        String FK_Persona = "";
        if (fkPersona != null)
            FK_Persona = fkPersona.toString();

        return "PersonaEntity{" +
                "documentoIdentidad='" + documentoIdentidad + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fkGenero=" + fkGenero.toString() +
                ", fkEdoCivil=" + fkEdoCivil.toString() +
                ", fkPersona=" + FK_Persona +
                ", numero_personas_encasa=" + numero_personas_encasa +
                ", fkLugar=" + fkLugar.toString() +
                '}';
    }
}
