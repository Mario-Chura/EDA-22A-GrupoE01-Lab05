public class AVLTree <T extends Comparable<T>>{
    protected Node<T> root;
    private boolean height;     
    public AVLTree(){
        this.root=null;                      
    }
    public boolean isEmpty(){
        return this.root == null;
    }
    

}
