/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Rol {
    private int idRol;
    private String denominacion;

    public Rol(int idRol, String denominacion) {
        this.idRol = idRol;
        this.denominacion = denominacion;
    }
    public Rol() {
        this.idRol = 0;
        this.denominacion = "";
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    
    
}
