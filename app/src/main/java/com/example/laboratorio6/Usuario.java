package com.example.laboratorio6;

public class Usuario {
    String nombreCompleto, correo, usuario, contraseña;
    int genero;

    Usuario (String nombreCompleto, String correo, String usuario, String contraseña, int genero ){
        this.nombreCompleto=nombreCompleto;
        this.correo= correo;
        this.usuario= usuario;
        this.contraseña= contraseña;
        this.genero= genero;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}
