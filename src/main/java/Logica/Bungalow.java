package Logica;

import Ficheros.Ficheros;
import Ficheros.Param;
import java.time.LocalDateTime;

public class Bungalow extends Parcela {

    public int adultos;

    Bungalow(int n) {
        super(n);
    }

    public boolean checkIn(String dniHuesped, int ad) {
        this.ocupado = true;
        this.dni = dniHuesped;
        this.fechaEntrada = LocalDateTime.now();
        this.adultos = ad;
        Camping.guardarC();
        return true;
    }

    @Override
    public double checkOut(Param p) {
        int dias = this.calcularDias();
        double precioBung;

        if (dias <= 2) {
            precioBung = p.getPrecioBungalow();
            precioBung += (p.getPrecioBungalow() * p.getRecargoBungalow());
            System.out.println("precioBung"+precioBung);
        } else {
            precioBung = p.getPrecioBungalow();
            System.out.println("precioBung"+precioBung);
        }

        Ficheros.GuardarLineaFactura(dni, numero, "Bungalow",
                this.fechaEntrada.toString(), LocalDateTime.now().toString(),
                (precioBung * dias * this.adultos));
        int adF = this.adultos;
        this.desocuparParcela();
        Camping.guardarC();
        return (precioBung * dias * adF);
    }

    @Override
    public void desocuparParcela() {
        this.dni = null;
        this.ocupado = false;
        this.fechaEntrada = null;
        this.adultos = 0;
    }
}
