public class AVLTree <T extends Comparable<T>>{
    protected Node<T> root;
    private boolean height;     
    public AVLTree(){
        this.root=null;                      
    }
    public boolean isEmpty(){
        return this.root == null;
    }
    public void insert(T x) throws ItemDuplicated{
        this.height = false;
        this.root = insertRec(x, this.root);
    }
    private Node<T> insertRec(T x, Node<T> current) throws ItemDuplicated{
        Node<T> res = current;
        if(current == null){
            this.height = true;
            res = new Node(x);
        }
        else{
            int resC= current.getData().compareTo(x);
            if(resC == 0){
                throw new ItemDuplicated("El Dato "+ x +" ya fue insertado antes ...");
            }
            if(resC < 0){
                res.setRightNode(insertRec(x, current.getRightNode()));
                if(this.height){
                    switch(res.getBalanceFactor()){
                        case -1: res.setBalanceFactor(0); this.height=false; break;
                        case 0: res.setBalanceFactor(1);  this.height=true; break;
                        case 1: 
                                res = balanceToLeft(res);
                                this.height=false;
                                break;
                    }
                }
            }
            else{ 
                res.setLeftNode(insertRec(x, current.getLeftNode()));
                if(this.height){
                    switch(res.getBalanceFactor()){
                        case 1: res.setBalanceFactor(0); this.height=false; break;
                        case 0: res.setBalanceFactor(-1); this.height=true; break;
                        case -1: 
                                res = balanceToRight(res);
                                this.height=false; break;
                    }
                }
            }
        }
        return res;
    }
    private Node<T> balanceToLeft(Node<T> node){
        Node<T> son = node.getRightNode(); //trabajo con el hijo derecho
        switch(son.getBalanceFactor()){
            case 0:
            case 1: node.setBalanceFactor(0);
                    son.setBalanceFactor(0);
                    System.out.println("Rotacion Simple Izquierda");
                    node = rotateSL(node); 
                    break;
            case -1: Node<T> grandson = son.getLeftNode();
                    switch(grandson.getBalanceFactor()){
                        case -1 :node.setBalanceFactor(0); son.setBalanceFactor(1);break;
                        case 0 : node.setBalanceFactor(0); son.setBalanceFactor(0);break;
                        case 1 : node.setBalanceFactor(-1); son.setBalanceFactor(0);break;
                    }
                    grandson.setBalanceFactor(0);
                    System.out.println("Rotacion Doble Izquierda");
                    node.setRightNode(rotateSR(son));
                    node = rotateSL(node);                                       
                    break;
        }
        return node;
    }
    private Node<T> balanceToRight(Node<T> node){
        Node<T> son = node.getLeftNode(); //trabajo con el hijo izquierdo
        switch(son.getBalanceFactor()){
            case 0:
            case -1:node.setBalanceFactor(0);
                    son.setBalanceFactor(0);
                    System.out.println("Rotacion Simple Derecha");
                    node = rotateSR(node);
                    break;
            case 1: Node<T> grandson = son.getRightNode();
                    switch(grandson.getBalanceFactor()){
                        case -1 : node.setBalanceFactor(0);son.setBalanceFactor(1); break;
                        case 0 : node.setBalanceFactor(0);son.setBalanceFactor(0); break;
                        case 1 : node.setBalanceFactor(-1);son.setBalanceFactor(0); break;
                    }
                    grandson.setBalanceFactor(0);
                    System.out.println("Rotacion Doble Derecha");
                    node.setLeftNode(rotateSL(son));
                    node = rotateSR(node);
                    break;
        }
        return node;
    }
    private Node<T> rotateSL(Node<T> node){
        Node<T> son = node.getRightNode();
        node.setRightNode(son.getLeftNode());
        son.setLeftNode(node);
        node = son;
        return node;
    }
    private Node<T> rotateSR(Node<T> node){
        Node<T> son = node.getLeftNode();
        node.setLeftNode(son.getRightNode());
        son.setRightNode(node);
        node = son;
        return node;
    }

    

}
