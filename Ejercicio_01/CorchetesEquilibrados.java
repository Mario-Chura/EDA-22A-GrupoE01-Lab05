package Ejercicio_01;

import java.util.Scanner;
    public class CorchetesEquilibrados {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
    System.out.print("cadena : ");
    String cadena= sc.next();
    System.out.println(isBalanced(cadena));
    }
    public static String isBalanced(String cadena) {
        Stack<Character> pila  = new Stack<Character>();
        
        for(int i = 0; i < cadena.length(); i++) {
            char simbolo = cadena.charAt(i);
            
            if(simbolo == '[' || simbolo == '(' || simbolo == '{' ) {     
                pila.push(simbolo);
            } else if(simbolo == ']') {
                if(pila.isEmpty() || pila.pop() != '[')
                    return "No";
        
            } else if(simbolo== ')') {
                if(pila.isEmpty() || pila.pop() != '(')
                    return "No"; 
                             
            } else if(simbolo == '}') {
                if(pila.isEmpty() || pila.pop() != '{')
                    return "No";
            }        
        }  
        if (pila.isEmpty())
        	return "Si";
        else
        	return "No";
}
}
    



    
