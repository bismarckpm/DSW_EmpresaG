package com.empresag;

public class RespuestaDto<T> {
    private int codigo;
    private String mensaje;
    private String mensajesoporte;
    private String estado;
    private T objeto;


    public RespuestaDto() {

    }

    public RespuestaDto(int codigo, String mensaje, String mensajesoporte, String estado, T objeto) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.mensajesoporte = mensajesoporte;
        this.estado = estado;
        this.objeto = objeto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajesoporte() {
        return mensajesoporte;
    }

    public void setMensajesoporte(String mensajesoporte) {
        this.mensajesoporte = mensajesoporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

}