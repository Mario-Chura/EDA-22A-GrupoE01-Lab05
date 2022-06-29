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
    public T search(T x) throws ItemNotFound{
        Node<T> res = searchNode(x , this.root);
        if(res == null)
            throw new ItemNotFound("El dato "+ x +" no esta");      
        return res.getData();
    }
    protected Node<T> searchNode(T x,Node<T> current) throws ItemNotFound{
        if(current == null)
            return null;
        else{
            int resC = current.getData().compareTo(x);
            if(resC<0)
                return searchNode(x, current.getRightNode());
            else if(resC>0)
                return searchNode(x, current.getLeftNode());
            else 
                return current;
        }
    }
    //Metodo Publico (remove)
    public void remove(T x) throws ItemNotFound{
        this.root=removeAVL(this.root,x);
    }
    //Metodo Protegido (remove)
    protected Node<T> removeAVL(Node<T> actual,T x) throws ItemNotFound{        
        //si es nulo, no existe el dato
        if(actual == null){
            throw new ItemNotFound(x + " no esta");
        }
        //comparar el dato x con el dato del nodo
        int resC = x.compareTo(actual.getData());
        //menor a cero trabajamos por la izquierda
        if(resC < 0){
            actual.setLeftNode(removeAVL(actual.getLeftNode(), x)); //recursividad
        }else if(resC > 0){ //mayor a cero trabajamos por la derecha
            actual.setRightNode(removeAVL(actual.getRightNode(), x));//recursividad
        }else{
            //El nodo es igual a la clave, se elimina
            //Nodo con un unico hijo o es hoja
            if ((actual.getLeftNode() == null) || (actual.getRightNode() == null)){
                Node<T> temp = null;
                if (temp == actual.getLeftNode()) {
                    temp = actual.getRightNode();
                }else {
                    temp = actual.getLeftNode();
                }
 
                // Caso que no tiene hijos
                if (temp == null) {
                    actual = null;//Se elimina dejandolo en null
                }else{
                    //Caso con un hijo
                    actual = temp;//Elimina el valor actual reemplazandolo por su hijo
                }
            }
            else {
                //Nodo con dos hijos, se busca el predecesor
                Node<T> temp = getNodoMaximo(actual.getLeftNode());
                
                //Se copia el dato del predecesor
                actual.setData(temp.getData());
 
                //Se elimina el predecesor
                actual.setLeftNode(removeAVL(actual.getLeftNode(),temp.getData()));
            }
        }
        return actual;
    }
    private Node<T> getNodoMaximo(Node<T> node) {
        Node<T> current = node;        
        while (current.getRightNode() != null){
           current = current.getRightNode();
        }        
        return current;
    }
    private String inOrden(Node<T> current){
        String str="";
        if(current.getLeftNode() != null) str += inOrden(current.getLeftNode());
        str += current.getData()+"["+current.getBalanceFactor()+"], ";
        if(current.getRightNode() != null) str += inOrden(current.getRightNode());
        return str;
    }
    public String toString(){
        if(isEmpty())
            return "Arbol vacio...";
        return inOrden(this.root); 
        
    }

    

}
