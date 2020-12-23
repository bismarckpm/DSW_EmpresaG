package com.empresag;

import javax.persistence.*;

@Entity
@Table(name = "tokens", schema = "empresag")
public class TokenEntity extends BaseEntity {
    @Basic
    @Column(name = "token_login")
    private String token_login;

    public String getToken_login() {
        return token_login;
    }

    public void setToken_login(String token_login) {
        this.token_login = token_login;
    }

    @Basic
    @Column(name = "token_reset")
    private String token_reset;

    public String getToken_reset() {
        return token_reset;
    }

    public void setToken_reset(String token_reset) {
        this.token_reset = token_reset;
    }

    @OneToOne
    @JoinColumn(name = "fk_usuario")
    public UsuarioEntity fkUsuario;

    public UsuarioEntity getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioEntity fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
