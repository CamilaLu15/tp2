package unlar.edu.ar.service;
import unlar.edu.ar.exception.*;
import unlar.edu.ar.model.CuentaBancaria;
import unlar.edu.ar.model.Transaccion;
import unlar.edu.ar.model.TipoTransaccion;
import unlar.edu.ar.util.LoggerUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CajeroService {

    private List<CuentaBancaria> cuentas;  //lista para guardar todas las cuentas

    public CajeroService() {
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta){
        this.cuentas.add(cuenta);   //metodo para registrar las cuentas en el banco
    }

    public void depositar(CuentaBancaria cuenta, BigDecimal monto) throws CuentaInactivaException {
        if (!cuenta.isActiva()) throw new CuentaInactivaException("La cuenta está desactivada.");
        if (monto.compareTo(BigDecimal.ZERO) <=0) return;  //compara si el monto es menor o igual a cero
        
        cuenta.setSaldo(cuenta.getSaldo().add(monto));  // suma el monto al saldo actual

        Transaccion transaccion = new Transaccion(TipoTransaccion.DEPOSITO, monto, "Déposito por cajero");

        String log = LoggerUtil.formatearLog(transaccion.getTipo().name(), monto.doubleValue(), cuenta.getSaldo().doubleValue());
        
        cuenta.agregarTransaccionAlHistorial(log);
    }


    public void extraer(CuentaBancaria cuenta, BigDecimal monto) 
            throws SaldoInsuficienteException, LimiteExtraccionExcedidoException, CuentaInactivaException {
        
        if (!cuenta.isActiva()) throw new CuentaInactivaException("Cuenta inactiva."); 

        BigDecimal limite = new BigDecimal("10000");  // se define el limite de 10.000 con BigDecimal

        // si el monto es mayor al limite
        if (monto.compareTo(limite) > 10000) throw new LimiteExtraccionExcedidoException("Máximo $10,000 por operación."); 
        // si el monto es mayor al saldo disponible
        if (monto.compareTo(cuenta.getSaldo()) > 0) throw new SaldoInsuficienteException("Saldo insuficiente."); 

        cuenta.setSaldo(cuenta.getSaldo().subtract(monto)); // se resta el monto del saldo

        Transaccion transaccion = new Transaccion(TipoTransaccion.EXTRACCION, monto, "Extracción por cajero.");
        String log = LoggerUtil.formatearLog(transaccion.getTipo().name(), monto.doubleValue(), cuenta.getSaldo().doubleValue());
        cuenta.agregarTrasaccionAlHistorial(log);
    }


    public void transferir(CuentaBancaria origen, CuentaBancaria destino, BigDecimal monto) 
            throws SaldoInsuficienteException, CuentaInactivaException, LimiteExtraccionExcedidoException {
        
        this.extraer(origen, monto);

        this.depositar(destino, monto); 
        
     
    }
}
