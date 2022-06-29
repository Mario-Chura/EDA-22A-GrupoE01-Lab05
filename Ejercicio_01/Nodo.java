package Ejercicio_01;

public class Nodo <T>{
    private T dato;
    private Nodo <T> puntero;
public Nodo (T dato){
    this.dato=dato;
}
public T getdato(){
    return dato;

}
public void setdato(T dato){
    this.dato=dato;
}
public Nodo<T> getnextNodo(){
    return puntero;
}
public void setnextNodo(Nodo<T> siguienteNodo){
    puntero=siguienteNodo;
}
}
