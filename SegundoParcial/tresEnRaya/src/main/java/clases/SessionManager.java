/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author mariu
 */
public class SessionManager {

    private static SessionManager instance = null;

    private Tipo tipo = null;

    private boolean pvp = false;

    public SessionManager() {
    }

    public static SessionManager getInstance(){
        if (instance == null)
        {
            instance = new SessionManager();
        }
        return instance;
    }
    
    public Tipo getTipo(){
        return tipo;
    }

    public void setTipo(Tipo tipo){
        this.tipo = tipo; 
    }

    public boolean getPvp(){
        return pvp;
    }
    
    public void setPvp(boolean state){
        pvp = state;
    }

}
