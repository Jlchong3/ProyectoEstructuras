
package trees;

import java.util.List;

public class Tree<E> {
    
    private TreeNode<E> root;
    private Integer utilidad;
    
    public Tree () {
        this.root = null; 
        this.utilidad = null;
    }
    
    public Tree(E content){
        this.root = new TreeNode<>(content);
        this.utilidad = null;
    }
    
    public boolean isEmpty () {
        return this.root == null;
    }

    public E getRoot() {
        return root.getContent();
    }
    

    public void setUtilidad(int utilidad){
        this.utilidad = utilidad;
    }

    public Integer getUtilidad(){
        return utilidad;
    }

    
    public void setRoot (E content) {
        this.root.setContent(content);
    }
    
    public List<Tree<E>> getChildren(){
        return this.root.getChildren();
    }
    
    public boolean isLeaf () {
        return this.root.getChildren().isEmpty();
    }
    
    public void addNode(E content){
        Tree<E> child = new Tree<>(content);
        this.root.getChildren().add(child);
    } 

    
}
