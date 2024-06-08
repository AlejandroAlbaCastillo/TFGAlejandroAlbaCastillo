/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Alejandro
 */
public class Mantenimiento {
    private int idMantenimiento;
    private int idPista;
    private String dni;
    private String tipoTrabajo;
    private float duracion;
    private String fecha;

    public Mantenimiento(int idMantenimiento, int idPista, String dni, String tipoTrabajo, float duracion, String fecha) {
        this.idMantenimiento=idMantenimiento;
        this.idPista = idPista;
        this.dni = dni;
        this.tipoTrabajo = tipoTrabajo;
        this.duracion = duracion;
        this.fecha = fecha;
    }
    public Mantenimiento() {
        this.idMantenimiento = 0;
        this.idPista = 0;
        this.dni = "";
        this.tipoTrabajo = "";
        this.duracion = 0;
        this.fecha = "";
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
