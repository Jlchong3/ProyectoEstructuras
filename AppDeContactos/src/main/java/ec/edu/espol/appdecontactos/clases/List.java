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
public interface List<E> extends Iterable<E> {

    
    boolean sort(Comparator<E> comp);

    boolean addFirst(E element);

    boolean addLast(E element);

    boolean removeFirst();

    boolean removeLast();

    E getFirst();

    E getLast();

    boolean insert(int index, E element);

    boolean contains(E element);

    E get(int index);

    int indexOf(E element);

    boolean isEmpty();

    E remove(int index);

    boolean remove(E element);

    E set(int index, E element);

    int size();
    
    List<E> findALL(E e, Comparator<E> comp);
    
    List<E> findLower(E e, Comparator<E> comp);
    
    List<E> findGreater(E e, Comparator<E> comp);
    
    List<E> findBetween(E e1, E e2, Comparator<E> comp);

}
