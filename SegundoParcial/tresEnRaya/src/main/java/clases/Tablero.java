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
    private Tipo[][] fichas;
    private int[] ultimoMovimiento;

    public Tablero() {
        this.fichas = new Tipo[3][3];
        this.ultimoMovimiento = new int[2];
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
        this.ultimoMovimiento[0] = i;
        this.ultimoMovimiento[1] = j;
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

    public int[] getUltimoMovimiento() {
        return ultimoMovimiento;
    }
    
    public Tablero copy(){
        Tablero copy = new Tablero();
        for(int i = 0; i < 3; i++){
            for(int j = 0 ; j < 3; j++){
                copy.fichas[i][j] = this.fichas[i][j];
            }
        }
        return copy;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tipo ficha = getFicha(i, j);
                if (ficha == null) {
                    sb.append("   "); // Espacios para celdas vacías
                } else {
                    sb.append(ficha).append(" "); // Agrega la ficha seguida de un espacio
                }

                if (j < 2) {
                    sb.append("|"); // Separador entre columnas
                }
            }
            sb.append("\n");

            if (i < 2) {
                sb.append("-----------\n"); // Separador entre filas
            }
        }
        return sb.toString();
    }
}
