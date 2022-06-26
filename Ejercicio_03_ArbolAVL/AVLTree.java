public class AVLTree <T extends Comparable<T>>{
    protected Node<T> root;
    private boolean height;     
    public AVLTree(){
        this.root=null;                      
    }
    public boolean isEmpty(){
        return this.root == null;
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
    

}
