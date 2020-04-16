package Logica;

import Ficheros.Param;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Parcela implements iAlquilable, Serializable {
    public boolean ocupado;
    public int numero;
    public String dni;
    public LocalDateTime fechaEntrada;

    @Override
    public abstract double checkOut(Param param);
    
    Parcela(int n){
        this.numero = n;
        this.ocupado = false;
        this.fechaEntrada = LocalDateTime.now();
    }
    Parcela(int n, String dni){
        this.numero = n;
        this.ocupado = true;
        this.fechaEntrada = LocalDateTime.now();
    }
    
    /*METODOS*/
    
    @Override
    public boolean checkIn(String dniHuesped){
        this.ocupado = true;
        this.dni = dniHuesped;
        this.fechaEntrada = LocalDateTime.now();
        Camping.guardarC();
        return true;
    }
    
    public int calcularDias(){
        LocalDateTime fechaHoy = LocalDateTime.now();
        int dias =(int) ChronoUnit.SECONDS.between(fechaEntrada, fechaHoy);
        dias = Math.abs(dias);
        return dias;
    }
    
    public void desocuparParcela(){
        this.dni = null;
        this.ocupado = false;
        this.fechaEntrada = null;
    }
        
        
}
