package unlar.edu.ar.model;

import java.time.LocalDateTime;

public class Transaccion {
    private TipoTransaccion tipo; 
    private double monto; 
    private LocalDateTime fechaHora; 
    private String descripcion;

    public Transaccion(TipoTransaccion tipo, double monto, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.descripcion = descripcion;
    }
}
