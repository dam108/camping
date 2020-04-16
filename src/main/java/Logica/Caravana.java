package Logica;

import Ficheros.Ficheros;
import Ficheros.Param;
import java.time.LocalDateTime;

public class Caravana extends Parcela {

    Caravana(int n){
        super(n);
    }
    
    @Override
    public double checkOut(Param p){
       int mes = LocalDateTime.now().getMonthValue();
       int dias = this.calcularDias();
       double precioCaravana;
       
       if (dias <= p.getDiasMinimosCaravana()) {
           return -1;
       }
       else {
            if (mes == 8){
               precioCaravana = p.getPrecioCaravanaAgosto();
            }
            else {
                precioCaravana = p.getPrecioCaravana();
            }
            Ficheros.GuardarLineaFactura(dni, numero, "Caravana",
                    this.fechaEntrada.toString(), LocalDateTime.now().toString(),
                    (precioCaravana * dias));
            super.desocuparParcela();
            
            Camping.guardarC();
            
            return precioCaravana * dias;
       }
    }

}
