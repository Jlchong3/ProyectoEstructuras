/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.appdecontactos.clases;

/**
 *
 * @author josel
 */
public class Node<E> {
    private Node<E> next;
    private Node<E> previous;
    private E content;
    
    public Node(Node<E> next, Node<E> previous, E content){
        this.next = next;
        this.previous = previous;
        this.content = content;
    }
    
    public Node(E content){
        this(null, null, content);
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public E getContent() {
        return content;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    public void setContent(E content) {
        this.content = content;
    }
    
}
