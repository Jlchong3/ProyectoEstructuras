
package trees;

public class Tree<E> {
    
    private TreeNode<E> root;
    
    public Tree () {
        this.root = null; 
    }
    
    public boolean isEmpty () {
        return this.root == null;
    }

    public E getRoot() {
        return root.getContent();
    }
    
    private TreeNode getRootNode () {
        return this.root;
    }

    private void setRootNode(TreeNode<E> root) {
        this.root = root;
    }
    
    public void setRoot (E content) {
        this.root.setContent(content);
    }
    
    public boolean isLeaf () {
        return this.root.getChildren().isEmpty();
    }
    
}
