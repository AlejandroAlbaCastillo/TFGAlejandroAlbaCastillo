/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Empleado extends Persona {
    
    private float sueldo;

    public Empleado(String dni, String nombre, String apellidos, String correo, int telefono, String contrasena, int rol, float sueldo) {
        super(dni, nombre, apellidos, correo, telefono, contrasena,rol);
        this.sueldo = sueldo;
    }
     public Empleado() {
        super();
        this.sueldo = 0;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

}
