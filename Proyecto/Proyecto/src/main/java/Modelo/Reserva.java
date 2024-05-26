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
public class Reserva {
    private int idPista;
    private String dni;
    private String horaIni;
    private float duracion;
    private Date fecha;
    private float precioReserva;

    public Reserva(int idPista, String dni, String horaIni, float duracion, Date fecha, float precioReserva) {
        this.idPista = idPista;
        this.dni = dni;
        this.horaIni = horaIni;
        this.duracion = duracion;
        this.fecha = fecha;
        this.precioReserva = precioReserva;
    }
    public Reserva() {
        this.idPista = 0;
        this.dni = "";
        this.horaIni = "";
        this.duracion = 0;
        this.fecha = new Date();
        this.precioReserva = 0;
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

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecioReserva() {
        return precioReserva;
    }

    public void setPrecioReserva(float precioReserva) {
        this.precioReserva = precioReserva;
    }
    
    
}
