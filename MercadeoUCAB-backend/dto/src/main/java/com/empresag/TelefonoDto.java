package com.empresag;

public class TelefonoDto extends BaseDto {
    private String numero;
    private PersonaDto fkPersona;

    public TelefonoDto() {
    }

    public TelefonoDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }
}
