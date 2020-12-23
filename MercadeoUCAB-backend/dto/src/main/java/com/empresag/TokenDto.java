package com.empresag;

public class TokenDto extends BaseDto {
    private String token_login;
    private String token_reset;
    private UsuarioDto fkUsuario;

    public String getToken_login() {
        return token_login;
    }

    public void setToken_login(String token_login) {
        this.token_login = token_login;
    }

    public String getToken_reset() {
        return token_reset;
    }

    public void setToken_reset(String token_reset) {
        this.token_reset = token_reset;
    }

    public UsuarioDto getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioDto fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
