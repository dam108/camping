package Ficheros;

import java.io.*;
import java.util.Properties;

public class Param {
    private double precioTiendaCampaña, descuentoTiendaCampaña,
            precioElectricidad,precioCaravanaAgosto,precioCaravana,
            precioBungalow,precioNiñoBungalow,recargoBungalow;
    private int diasParaDescuentoTiendaCampaña, diasMinimosCaravana;
    private final int parcelaTienda, parcelaCaravana, parcelaBungalow;

   
    /*CONSTRUCTOR*/
    public Param(){
        cargarProperties();
        this.parcelaTienda = 10;
        this.parcelaCaravana = 20;
        this.parcelaBungalow = 50;
    }
    
    /*METODOS*/
    
    public void cargarProperties(){
        Properties config = new Properties();
        
        try{
            config.load(new FileInputStream(Ficheros.rutaProperties));
            this.precioTiendaCampaña = Double.valueOf(config.getProperty
                            ("Precio tienda de Campaña"));
            this.descuentoTiendaCampaña = Double.valueOf(config.getProperty
                            ("Descuento tienda de Campaña"));
            this.diasParaDescuentoTiendaCampaña = Integer.valueOf(
                    config.getProperty
                            ("Dias necesarios para descuento en tienda de"
                                    + " Campaña"));
            this.precioElectricidad = Double.valueOf(config.getProperty
                            ("Precio de la electricidad"));
            this.precioCaravanaAgosto = Double.valueOf(config.getProperty
                            ("Precio Caravana en Agosto"));
            this.precioCaravana = Double.valueOf(config.getProperty
                            ("Precio Caravana"));
            this.precioBungalow = Double.valueOf(config.getProperty
                            ("Precio Bungalow por persona"));
            this.precioNiñoBungalow = Double.valueOf(config.getProperty
                            ("Precio Bungalow por niño"));
            this.recargoBungalow = Double.valueOf(config.getProperty
                            ("Recargo Bungalow"));
            this.diasMinimosCaravana = Integer.valueOf(config.getProperty
                            ("Minimo de dias para Caravana"));

        }
        catch(FileNotFoundException e){System.out.println("No Existe el"
                + " archivos");}
        catch(IOException ioe){ioe.printStackTrace();}
    }
    
    
    /*GETTERS Y SETTERS*/

    public double getPrecioTiendaCampaña() {
        return precioTiendaCampaña;
    }

    public double getDescuentoTiendaCampaña() {
        return descuentoTiendaCampaña;
    }

    public double getPrecioElectricidad() {
        return precioElectricidad;
    }

    public double getPrecioCaravanaAgosto() {
        return precioCaravanaAgosto;
    }

    public double getPrecioCaravana() {
        return precioCaravana;
    }

    public double getPrecioBungalow() {
        return precioBungalow;
    }

    public double getPrecioNiñoBungalow() {
        return precioNiñoBungalow;
    }

    public double getRecargoBungalow() {
        return recargoBungalow;
    }

    public int getDiasParaDescuentoTiendaCampaña() {
        return diasParaDescuentoTiendaCampaña;
    }

    public int getDiasMinimosCaravana() {
        return diasMinimosCaravana;
    }
    
    public int getParcelaTienda() {
        return parcelaTienda;
    }

    public int getParcelaCaravana() {
        return parcelaCaravana;
    }

    public int getParcelaBungalow() {
        return parcelaBungalow;
    }
    
}
