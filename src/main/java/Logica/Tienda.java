package Logica;

import Ficheros.Ficheros;
import Ficheros.Param;
import java.time.LocalDateTime;

public class Tienda extends Parcela {
    boolean electricidad;
    
    Tienda(int n){
        super(n);
        this.electricidad = false;
    }

    public boolean checkIn(String dni, boolean el){
        super.checkIn(dni);
        if (el) this.electricidad = true;
        else this.electricidad = false;
        Camping.guardarC();
        return true;
    }
    
    @Override
    public double checkOut(Param p){
        double precioTienda, precioElectriciad;
        int dias = this.calcularDias();
        
        precioTienda = p.getPrecioTiendaCampaña();
        precioElectriciad = p.getPrecioElectricidad();
        
        if(dias > 7 ) precioTienda -= (p.getDescuentoTiendaCampaña() * precioTienda) ;

        
        if(this.electricidad){

            Ficheros.GuardarLineaFactura(dni, numero, "Tienda",
            this.fechaEntrada.toString(), LocalDateTime.now().toString(),
            ((precioTienda + precioElectriciad) * dias));
            
            this.desocuparParcela();
            
            Camping.guardarC();
            
            return (precioTienda + precioElectriciad) * dias;
        }
        else {
            Ficheros.GuardarLineaFactura(dni, numero, "Tienda",
            this.fechaEntrada.toString(), LocalDateTime.now().toString(),
            (precioTienda * dias));
            
            this.desocuparParcela();
            
            Camping.guardarC();            
            
            return precioTienda * dias;
        }
    }
    
    @Override
    public void desocuparParcela(){
        this.dni = null;
        this.ocupado = false;
        this.fechaEntrada = null;
        this.electricidad = false;
    }
}
