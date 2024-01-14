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
public class SessionManager {
 private static clases.SessionManager instance = null;

    private clases.Jugador jug1 = new clases.Jugador(Tipo.EQUIS);    
    private clases.Jugador jug2 = new clases.Jugador(Tipo.CIRCULO);

    public SessionManager() {
    }
    
    public static clases.SessionManager getInstance(){
        if (instance == null)
        {
            instance = new clases.SessionManager();
        }
        return instance;
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
