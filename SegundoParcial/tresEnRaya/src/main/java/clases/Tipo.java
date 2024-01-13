/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package clases;

/**
 *
 * @author Migi
 */
public enum Tipo {
    EQUIS, CIRCULO;

    @Override
    public String toString() {
        if (this.equals(Tipo.EQUIS))
                return "X";
        else
            return "O";
    }
    
    public Tipo opuesto(){
        return (this == Tipo.EQUIS) ? Tipo.CIRCULO : Tipo.EQUIS;
    }
    
    
}
