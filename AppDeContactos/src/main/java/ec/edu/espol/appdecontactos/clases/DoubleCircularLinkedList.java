/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.appdecontactos.clases;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author josel
 */
public class DoubleCircularLinkedList<E> implements List<E>{
    private Node<E> last;

    public DoubleCircularLinkedList(){
        this.last = null;
    }
    
    @Override
    public boolean addLast(E element) {
        if(element == null){
            return false;
        }

        Node<E> n = new Node<>(element);
        if(isEmpty()){
            last = n;
            last.setNext(last);
            last.setPrevious(last);
        }
        Node<E> first = last.getNext();
        last.setNext(n);
        first.setPrevious(n);
        last = n;
        return true;
    }

    @Override
    public boolean sort(Comparator<E> comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        return  last == null;
    }


    @Override
    public int size() {
        int i = 1;
        for(Node<E> n = last.getNext(); n != last; n.getNext())
            i++;
        return i;
    }

    
    public boolean contains(E element, Comparator<E> comp){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
    
    
    public ListIterator<E> CircularIterator() {
        ListIterator<E> it = new ListIterator<>(){
            
            Node<E> cursor = last.getNext();

            @Override
            public boolean hasNext() {
                return size() > 1;
            }

            @Override
            public E next() {
                E temp = cursor.getContent();
                cursor = cursor.getNext();
                return temp;
            }

            @Override
            public boolean hasPrevious() {
                return size() > 1;
            }

            @Override
            public E previous() {
                E temp = cursor.getContent();
                cursor = cursor.getPrevious();
                return temp;
            }


            @Override
            public void remove() {
                Node<E> pp = cursor.getPrevious().getPrevious();
                pp.setNext(cursor);
                cursor.setPrevious(pp);
                next();
            }

            @Override
            public void set(E e) {
                cursor.setContent(e);
            }

            @Override
            public void add(E e) {
                addLast(e);
            }         

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        return it; 
    }

    @Override
    public boolean remove(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<>(){
            Node<E> cursor = last.getNext();
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public E next() {
                E temp = cursor.getContent();
                cursor = cursor.getNext();
                return temp;
            }
        };
        return it;
    }

}
