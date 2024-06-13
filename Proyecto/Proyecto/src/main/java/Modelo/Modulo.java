/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Modulo {
    private int idModulo;
    private String modulo;

    public Modulo(int idModulo, String modulo) {
        this.idModulo = idModulo;
        this.modulo = modulo;
    }
    public Modulo() {
        this.idModulo = 0;
        this.modulo = "";
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return idModulo + " - " + modulo ;
    }
    
}
