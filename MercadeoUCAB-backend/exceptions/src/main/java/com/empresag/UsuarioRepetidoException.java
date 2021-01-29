package com.empresag;

public class UsuarioRepetidoException extends Exception{
    public UsuarioRepetidoException() {
        super("El usuario ya existe");
    }
}
