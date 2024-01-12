/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import Excepciones.NonValidMove;

/**
 *
 * @author Migi
 */
public abstract class Ficha {
    public Tipo tipo;
    public int x;
    public int y;
    protected Tablero t;
    
    public Ficha(Tipo tipo, int x, int y, Tablero t) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.t= t;
    }
    
    public abstract void validarMovimiento(int x, int y) throws NonValidMove;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tablero getT() {
        return t;
    }

    public void setT(Tablero t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Ficha{" + "tipo=" + tipo + ", x=" + x + ", y=" + y + ", t=" + t + '}';
    }
    
    

}
