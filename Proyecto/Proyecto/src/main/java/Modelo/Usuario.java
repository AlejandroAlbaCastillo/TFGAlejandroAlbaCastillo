/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Usuario extends Persona {
    
    private String usuario;

    public Usuario(String dni, String nombre, String apellidos, String correo, int telefono, String contrasena, int rol, String usuario) {
        super(dni, nombre, apellidos, correo, telefono, contrasena,rol);
        this.usuario = usuario;
    }
     public Usuario() {
        super();
        this.usuario = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return getDni() + " - " + usuario;
    }

    
    
    
}
