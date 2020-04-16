package Logica;

import Ficheros.*;
import java.io.File;
import java.util.ArrayList;

public class Camping {
    public static ArrayList <Parcela> parcelas;
    
    /* CONTRUCTOR */
    public Camping(){
        parcelas = new ArrayList<>();
        File f = new File(Ficheros.rutaArrCamp);
        if (f.exists()){
            parcelas = Ficheros.cargarCamping();
        } 
        else {
            Param p = new Param();
            this.inicializarParcelas(p);
            guardarC();            
        }
    }
    
    /*METODOS*/
    
    public void inicializarParcelas(Param p){
        for (int i = 0; i < p.getParcelaTienda(); i++) {
            parcelas.add(new Tienda(i+1));
            System.out.println(i+1);
        }
        for (int i = p.getParcelaTienda(); i < p.getParcelaCaravana(); i++) {
            parcelas.add(new Caravana(i+1));
            System.out.println(i+1);
        }
        for (int i = p.getParcelaCaravana(); i < p.getParcelaBungalow(); i++) {
            parcelas.add(new Bungalow(i+1));
            System.out.println(i+1);
        }
    }
    
    public static void guardarC(){
        Ficheros.GuardarCamping(parcelas);
    }
}