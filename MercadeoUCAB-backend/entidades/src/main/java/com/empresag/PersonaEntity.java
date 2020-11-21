package com.empresag;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "persona", schema = "empresag", catalog = "")
public class PersonaEntity extends BaseEntity{
    private int id;
    private String documentoIdentidad;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private GeneroEntity fkGenero;
    private EdoCivilEntity fkEdoCivil;
    private PersonaEntity fkPersona;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return id == that.id &&
                fkGenero == that.fkGenero &&
                fkEdoCivil == that.fkEdoCivil &&
                Objects.equals(documentoIdentidad, that.documentoIdentidad) &&
                Objects.equals(primerNombre, that.primerNombre) &&
                Objects.equals(segundoNombre, that.segundoNombre) &&
                Objects.equals(primerApellido, that.primerApellido) &&
                Objects.equals(segundoApellido, that.segundoApellido) &&
                Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
                Objects.equals(fkPersona, that.fkPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentoIdentidad, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, fkGenero, fkEdoCivil, fkPersona);
    }
}
