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
    public Tipo[][] fichas;

    public Tablero() {
        this.fichas = new Tipo[3][3];
    }
    
    public Tipo[][] getFichas() {
        return fichas;
    }
    
    public Tipo getFicha(int i, int j){
        return this.fichas[i][j];
    }
    
    public void setFichas(Tipo[][] fichas) {
        this.fichas = fichas;
    }
    
    public void setFicha(int i, int j, Tipo ficha) {
        this.fichas[i][j] = ficha;
    }
    
    public boolean isFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(this.fichas[i][j] == null)
                    return false;
            }
        }
        return true;
    }
}
