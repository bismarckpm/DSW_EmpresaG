package com.empresag;

public class LoginException extends Exception{

    public LoginException() {
        super("Error de conexión, autenticación no verificada!");
    }

}
