/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import clases.Tipo;

/**
 *
 * @author mariu
 */
public class Jugador {
     private clases.Jugador jug1;

    private clases.Jugador jug2;
    
    private Tipo tipoJ;

    public Jugador(Tipo tipoJ) {
        this.tipoJ = tipoJ;
    }

    public Tipo getTipoJ() {
        return tipoJ;
    }

    public void setTipoJ(Tipo tipoJ) {
        this.tipoJ = tipoJ;
    }

    public clases.Jugador getJug1() {
        return jug1;
    }

    public void setJug1(clases.Jugador jug1) {
        this.jug1 = jug1;
    }

    public clases.Jugador getJug2() {
        return jug2;
    }

    public void setJug2(clases.Jugador jug2) {
        this.jug2 = jug2;
    }
       
}
