package unlar.edu.ar.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {
    private final TipoTransaccion tipo; 
    private final BigDecimal monto; 
    private final LocalDateTime fechaHora; 
    private final String descripcion;

    public Transaccion(TipoTransaccion tipo, BigDecimal monto, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.descripcion = descripcion;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    
}
