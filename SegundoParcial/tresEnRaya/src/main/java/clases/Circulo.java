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
public class Circulo extends Ficha {
    
    public Circulo(Tipo tipo, int x, int y, Tablero t) {
        super(tipo, x, y, t);
    }

    @Override
    public void validarMovimiento(int x, int y) throws NonValidMove {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
