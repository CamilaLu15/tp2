package unlar.edu.ar.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class CuentaBancaria {

    private final String numeroCuenta;  // final porque debe ser inmutable
    private BigDecimal saldo;
    private String titular;
    private boolean activa;
    private List<String> historialTransacciones;

    public CuentaBancaria(String numeroCuenta, String titular, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.activa = true;
        this.historialTransacciones = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }


    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<String> getHistorialTransacciones() {
        return new ArrayList<>(historialTransacciones);
    }

    
    public void agregarTransaccionAlHistorial(String log) {
        if (this.historialTransacciones.size() >= 10) {

            this.historialTransacciones.remove(0);   // elimina la mas antigua

        }  
        this.historialTransacciones.add(log);
    }
}
