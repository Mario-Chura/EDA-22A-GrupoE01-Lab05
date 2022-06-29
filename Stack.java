public class Stack <T extends Comparable<T>>{
    private Nodo <T> root;
public T pop(){
    if (root!=null){
        Nodo<T>Nodoeliminado=root;
        root=root.getnextNodo();
        Nodoeliminado.setnextNodo(null);
        return Nodoeliminado.getdato();
    }
    return null;
}
public void push (T dato){
    Nodo <T> nodo=new Nodo<T>(dato);
    nodo.setnextNodo(root);
    root = nodo;
}
public T peek(){
    if (root!=null)
    return root.getdato();
    return null;
}
}
