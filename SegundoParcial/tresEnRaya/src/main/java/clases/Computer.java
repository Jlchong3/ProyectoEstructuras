/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Computer to edit this template
 */
package clases;

import java.util.List;
import java.util.PriorityQueue;
import trees.Tree;

/**
 *
 * @author josel
 */
public class Computer {
    private Tablero tablero;
    private Tipo tipo;
    
    public Computer(Tablero tablero, Tipo tipo){
        this.tablero = tablero;
        this.tipo = tipo;
    }
    
    private Tree<Tablero> crearPosibilidades(Tipo tipo){
        Tree<Tablero> posibilidades = new Tree<>(tablero); 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Tablero copia = tablero.copy();
                if(copia.getFicha(i, j) == null){
                    copia.setFicha(i, j, tipo);
                    posibilidades.addNode(copia);
                }
            }
        }
        
        for(Tree<Tablero> children : posibilidades.getChildren()){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    Tablero copy = children.getRoot().copy();
                    if(copy.getFicha(i, j) == null){
                        copy.setFicha(i, j, tipo.opuesto());
                        children.addNode(copy);
                    }
                }
            }
        }
        
        return posibilidades;
    }
    
    public int[] mejorOpcion(){
        PriorityQueue<Tree<Tablero>> max = new PriorityQueue<>((t1, t2) -> t2.getUtilidad() - t1.getUtilidad());
        List<Tree<Tablero>> opciones = crearPosibilidades(tipo).getChildren();
        if(opciones.size() == 1){
            return opciones.get(0).getRoot().getUltimoMovimiento();
        } 
        
        for(Tree<Tablero> opcion : opciones){
            List<Tree<Tablero>> posibilidades = opcion.getChildren();
            PriorityQueue<Integer> min = new PriorityQueue<>((t1, t2) -> t1 - t2);
            
            for(Tree<Tablero> posibilidad : posibilidades){
                min.offer(calcularUtilidad(posibilidad.getRoot()));
            }
            opcion.setUtilidad(min.poll());
            max.offer(opcion);
        }
        return max.poll().getRoot().getUltimoMovimiento();
    }
    
    private int calcularUtilidad(Tablero tablero){
        int totalJugador = 0;
        int totalComputer = 0;
        for(int i = 0; i < 3; i++){
            //computadora
            if(filaDisponible(tablero, this.tipo, i))
                totalComputer++;
            if(columnaDisponible(tablero, this.tipo, i))
                totalComputer++;
            //jugador
            if(filaDisponible(tablero, this.tipo.opuesto(), i))
                totalJugador++;
            if(columnaDisponible(tablero, this.tipo.opuesto(), i))
                totalJugador++;
        }
        //diagonales de computadora
        if(diagonalPrincipalDisponible(tablero, this.tipo))
            totalComputer++;
        if(diagonalSecundariaDisponible(tablero, this.tipo))
            totalComputer++;
        if(diagonalPrincipalDisponible(tablero, this.tipo.opuesto()))
            totalJugador++;
        if(diagonalSecundariaDisponible(tablero, this.tipo.opuesto()))
            totalJugador++;
        
        return totalComputer - totalJugador;
    }
    
    private boolean filaDisponible(Tablero tablero, Tipo tipo, int fila){
        return (tablero.getFicha(fila, 0) == tipo || tablero.getFicha(fila, 0) == null) &&
               (tablero.getFicha(fila, 1) == tipo || tablero.getFicha(fila, 1) == null) &&
               (tablero.getFicha(fila, 2) == tipo || tablero.getFicha(fila, 2) == null);
    }
    
    private boolean columnaDisponible(Tablero tablero, Tipo tipo, int columna){
        return (tablero.getFicha(0, columna) == tipo || tablero.getFicha(0, columna) == null) &&
               (tablero.getFicha(1, columna) == tipo || tablero.getFicha(1, columna) == null) &&
               (tablero.getFicha(2, columna) == tipo || tablero.getFicha(2, columna) == null);
    }
    
    private boolean diagonalPrincipalDisponible(Tablero tablero, Tipo tipo){
        return (tablero.getFicha(0, 0) == tipo || tablero.getFicha(0, 0) == null) &&
               (tablero.getFicha(1, 1) == tipo || tablero.getFicha(1, 1) == null) &&
               (tablero.getFicha(2, 2) == tipo || tablero.getFicha(2, 2) == null);
    }
    
    private boolean diagonalSecundariaDisponible(Tablero tablero, Tipo tipo){
        return (tablero.getFicha(0, 2) == tipo || tablero.getFicha(0, 2) == null) &&
               (tablero.getFicha(1, 1) == tipo || tablero.getFicha(1, 1) == null) &&
               (tablero.getFicha(2, 0) == tipo || tablero.getFicha(2, 0) == null);
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    
}
