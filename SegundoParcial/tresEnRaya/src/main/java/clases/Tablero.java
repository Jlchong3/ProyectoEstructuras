/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author mariu
 */
public class Tablero {
    public Ficha[][] fichas;

    public Tablero() {
        this.fichas = new Ficha[3][3];
    }
    
    public Ficha[][] getFichas() {
        return fichas;
    }
    
    public void setFichas(Ficha[][] fichas) {
        this.fichas = fichas;
    }
}
