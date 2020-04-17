package Grafica;

import Ficheros.Param;
import Logica.Camping;
import Logica.Tienda;

public class mainConsola {
    public static Camping camp;
    public static void main(String[] args) {
        
        Param p = new Param();
//        
//        System.out.println("Dias: "+p.getDiasMinimosCaravana());
        
        camp = new Camping();
//        

//
//    Camping.parcelas.get(0).checkIn("79336700v");
    
        for (int i = 0; i < camp.parcelas.size(); i++) {
            System.out.println(camp.parcelas.get(i).ocupado);
            System.out.println(camp.parcelas.get(i).fechaEntrada);
            System.out.println(camp.parcelas.get(i).dni);
            System.out.println(camp.parcelas.get(i).numero);
        }
    

//      Camping.parcelas.get(0).checkOut(p);
//      
//        for (int i = 0; i < Camping.parcelas.size(); i++) {
//            System.out.println(Camping.parcelas.get(i).ocupado);
//            System.out.println(Camping.parcelas.get(i).fechaEntrada);
//            System.out.println(Camping.parcelas.get(i).dni);
//            System.out.println(Camping.parcelas.get(i).numero);
//        }
    
    }
}
