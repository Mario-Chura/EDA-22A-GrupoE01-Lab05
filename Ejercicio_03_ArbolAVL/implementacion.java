public class implementacion {

    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        try{
             System.out.println("*Inserta: "+50);
             avl.insert(50);
             System.out.println(avl);
             System.out.println("*Inserta: "+30);
             avl.insert(30);
             System.out.println(avl);
             System.out.println("*Inserta: "+70);
             avl.insert(70);
             System.out.println(avl);
             System.out.println("*Inserta: "+65);
             avl.insert(65);
             System.out.println(avl);
             System.out.println("*Inserta: "+68);
             avl.insert(68);
             System.out.println(avl);
             System.out.println("*Inserta: "+45);
             avl.insert(45);                    
             System.out.println(avl);
             System.out.println("*Inserta: "+64);
             avl.insert(64);                    
             System.out.println(avl);
             System.out.println("*Inserta: "+63);
             avl.insert(63);                    
             System.out.println(avl);
             System.out.println("BUSQUEDA -> Dato: "+avl.search(63));
             System.out.println("Remove: "+65);
             avl.remove(65);
             System.out.println(avl);
             System.out.println("Remove: "+68);
             avl.remove(68);
             System.out.println(avl);
             
         }catch(Exception x){
             System.out.println(x.getMessage());
         }
    }
    
}