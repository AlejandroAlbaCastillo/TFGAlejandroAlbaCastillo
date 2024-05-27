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
public class Trabajan {
 
    private String dni;
    private int idSucursal;
    private Date fechaInicio;
    private Date fechaFin;

    public Trabajan(String dni, int idSucursal, Date fechaInicio, Date fechaFin) {
        this.dni = dni;
        this.idSucursal = idSucursal;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public Trabajan() {
        this.dni = "";
        this.idSucursal = 0;
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
}
