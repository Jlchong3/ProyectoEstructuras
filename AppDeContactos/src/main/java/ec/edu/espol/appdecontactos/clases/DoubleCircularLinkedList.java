/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.appdecontactos.clases;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author josel
 */
public class DoubleCircularLinkedList<E> implements List<E>, java.io.Serializable{
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
            return true;
        }
        Node<E> first = last.getNext();
        first.setPrevious(n);
        last.setNext(n);
        n.setPrevious(last);
        n.setNext(first);
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
        for(Node<E> n = last.getNext(); n != last;  n = n.getNext())
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
                E temp = cursor.getPrevious().getPrevious().getContent();
                cursor = cursor.getPrevious();
                return temp;
            }


            @Override
            public void remove() {
                Node<E> pp = cursor.getPrevious().getPrevious(); 
                if(cursor.getPrevious() == last){
                    last = pp;
                }
                if(cursor == cursor.getPrevious()){
                    cursor.setNext(null);
                    cursor.setPrevious(null);
                    last = null;
                }
                else{
                    pp.setNext(cursor);
                    cursor.setPrevious(pp);
                }
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

    @Override
    public boolean addFirst(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        if(isEmpty()){
            return null;
        }
        if(index < 0 || index >= size()){
            return null;
        }
        Node<E> n = last.getNext();
        for(int i = 0; i < index; i++){
            n = n.getNext();
        }
        return n.getContent();
        
        
        
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findALL(E e, Comparator<E> comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findLower(E e, Comparator<E> comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findGreater(E e, Comparator<E> comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findBetween(E e1, E e2, Comparator<E> comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String toString(){
        if(this.last == null)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("["+ last.getNext().getContent());
        Node<E> n;
        for(n = last.getNext().getNext(); n != last.getNext();n = n.getNext()){
            sb.append(", "+ n.getContent());
        }
        sb.append("]");
        return sb.toString();
    }
}

