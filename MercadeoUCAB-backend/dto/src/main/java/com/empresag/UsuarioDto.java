package com.empresag;

public class UsuarioDto extends BaseDto {
    private String email;
    private String password;
    private int estado;
    private String tokenLogin;
    private String tokenRecovery;
    private PersonaDto fkPersona;
    private RolDto fkRol;

    public UsuarioDto() {
    }

    public UsuarioDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public RolDto getFkRol() {
        return fkRol;
    }

    public void setFkRol(RolDto fkRol) {
        this.fkRol = fkRol;
    }

    public String getTokenLogin() {
        return tokenLogin;
    }

    public void setTokenLogin(String tokenLogin) {
        this.tokenLogin = tokenLogin;
    }

    public String getTokenRecovery() {
        return tokenRecovery;
    }

    public void setTokenRecovery(String tokenRecovery) {
        this.tokenRecovery = tokenRecovery;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", estado=" + estado +
                ", fkPersona=" + fkPersona +
                ", fkRol=" + fkRol +
                '}';
    }
}
