/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.espol.appdecontactos.clases;

import java.util.Comparator;

/**
 *
 * @author josel
 */
public interface List<E> extends Iterable {

    boolean addLast(E element);
    
    boolean sort(Comparator<E> comp);

    boolean isEmpty();

    boolean remove(E element);

    int size();
    
    boolean contains(E element, Comparator<E> comp);
}
