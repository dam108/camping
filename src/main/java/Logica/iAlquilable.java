package Logica;

import Ficheros.Param;

public interface iAlquilable {
    //checin marca la parcela como ocupada
    boolean checkIn(String dniHuesped);
    
    //checkout marca la parcela como libre y 
    //calcula el importe a pagar en función
    double checkOut(Param param);
}
