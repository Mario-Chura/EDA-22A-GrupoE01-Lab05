public class Node<T> {
    //Atributos
    private T data;
    private int balanceFactor;
    private Node<T> rightNode;
    private Node<T> leftNode;
    //Constructores
    public Node(T data, Node<T> left, Node<T> right){
        this.data=data;
        this.leftNode=left;
        this.rightNode=right;
        this.balanceFactor=0;
    }
    public Node(T data){
        this(data,null,null);
    }
    
    //Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public Node<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
    }

    public Node<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
    }  
}
