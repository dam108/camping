package Logica;

import Ficheros.Ficheros;
import Ficheros.Param;
import java.time.LocalDateTime;

public class Bungalow extends Parcela {
    public int adultos;
    
    Bungalow(int n){
        super(n);
    }

    public boolean checkIn(String dni, int ad){
       super.checkIn(dni);
       this.adultos = ad;
       Camping.guardarC();
       return true;
    }
    
    @Override
    public double checkOut(Param p){
        int dias = this.calcularDias();
        double precioBung;
        
        if (dias <= 2){
            precioBung = p.getPrecioBungalow();
            precioBung += (p.getPrecioBungalow() * p.getRecargoBungalow());
        }
        else {
            precioBung = p.getPrecioBungalow();
        }
        
        Ficheros.GuardarLineaFactura(dni, numero, "Bungalow",
                this.fechaEntrada.toString(), LocalDateTime.now().toString(),
                (precioBung * dias * this.adultos));
        
        this.desocuparParcela();
        Camping.guardarC();
        return precioBung * dias * this.adultos;
    }
    
    @Override
    public void desocuparParcela(){
        this.dni = null;
        this.ocupado = false;
        this.fechaEntrada = null;
        this.adultos = 0;
    }
}
