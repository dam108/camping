package Ficheros;

import Logica.*;
import java.io.*;
import java.util.*;

public class Ficheros {
    public static final String rutaArrCamp = "data\\camping.dat";
    public static final String rutaFactura = "data\\facturas.csv";
    public static final String rutaProperties = "data\\camping.props";
    
    /*CONSTRUCTOR*/
    Ficheros(){}

    /*METODOS*/
    public static void GuardarLineaFactura(String dni, int n, String tParcela,
            String fE,String fS, double importe){
        
        try(FileWriter fw = new FileWriter(rutaFactura, true);
            BufferedWriter btw = new BufferedWriter (fw)){
            
            btw.write(dni);btw.write(";");
            btw.write(String.valueOf(n));btw.write(";");
            btw.write(tParcela);btw.write(";");
            btw.write(fE);btw.write(";");
            btw.write(fS);btw.write(";");
            btw.write(String.valueOf(importe));
        }
        catch(IOException ex){System.err.printf("%nError: ",ex.getMessage());}
        
    }
    
    public static void GuardarCamping(ArrayList arr){

        try( FileOutputStream fos = new FileOutputStream(rutaArrCamp, false);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)){
            
            for (int i = 0; i < arr.size(); i++) {
                oos.writeObject(arr.get(i));
            }
                
        }
        catch(IOException ex){System.err.printf("%nError1: %s",ex.getMessage());}
    }
            
    
    public static ArrayList cargarCamping(){
        boolean eof = false;
        ArrayList<Parcela> arr = new ArrayList<>();
        
        try(FileInputStream fis = new FileInputStream(rutaArrCamp);
            BufferedInputStream bfis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bfis)){
        
            while(!eof){
                Object obj = ois.readObject();
                
                if (obj instanceof Tienda){
                    arr.add((Tienda) obj);
                }
                if (obj instanceof Caravana){
                    arr.add((Caravana) obj);
                }
                if (obj instanceof Bungalow){
                    arr.add((Bungalow) obj);
                }                
            }
        }
        catch(EOFException e){eof = true; return arr;}
        catch(ClassNotFoundException ex){System.err.printf("%nError2: %s",ex.getMessage());}
        catch(IOException ex){System.err.printf("%nError3: %s",ex.getMessage());}
        return arr;
    }
}
