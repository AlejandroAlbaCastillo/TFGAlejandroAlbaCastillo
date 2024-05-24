/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Permiso {
    private int idRol;
    private int idModulo;
    private String permiso;

    public Permiso(int idRol, int idModulo, String permiso) {
        this.idRol = idRol;
        this.idModulo = idModulo;
        this.permiso = permiso;
    }

    public Permiso() {
        this.idRol = 0;
        this.idModulo = 0;
        this.permiso = "";
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
}
