/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Pista {
    
    
    private int idPista;
    private int precioHora;
    private String actividad;
    private int idSucursal;

    public Pista(int idPista, int precioHora, String actividad, int idSucursal) {
        this.idPista = idPista;
        this.precioHora = precioHora;
        this.actividad = actividad;
        this.idSucursal = idSucursal;
    }
    public Pista() {
        this.idPista = 0;
        this.precioHora = 0;
        this.actividad = "";
        this.idSucursal = 0;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public int getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(int precioHora) {
        this.precioHora = precioHora;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString(){
        return idPista+"-"+actividad;
    }
}
