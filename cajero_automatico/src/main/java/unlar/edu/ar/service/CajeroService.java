package unlar.edu.ar.service;
import unlar.edu.ar.exception.*;
import unlar.edu.ar.model.CuentaBancaria;


public class CajeroService {


    public void depositar(CuentaBancaria cuenta, double monto) throws CuentaInactivaException {
        if (!cuenta.isActiva()) throw new CuentaInactivaException("La cuenta está desactivada.");
        if (monto <= 0) return;
        
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuenta.registrarTransaccion("DEPÓSITO", monto);
    }

    public void extraer(CuentaBancaria cuenta, double monto) 
            throws SaldoInsuficienteException, LimiteExtraccionExcedidoException, CuentaInactivaException {
        
        if (!cuenta.isActiva()) throw new CuentaInactivaException("Cuenta inactiva."); 
        if (monto > 10000) throw new LimiteExtraccionExcedidoException("Máximo $10,000 por operación."); 
        if (monto > cuenta.getSaldo()) throw new SaldoInsuficienteException("Saldo insuficiente."); 

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        cuenta.registrarTransaccion("EXTRACCIÓN", monto);
    }
    public void transferir(CuentaBancaria origen, CuentaBancaria destino, double monto) 
            throws SaldoInsuficienteException, CuentaInactivaException, LimiteExtraccionExcedidoException {
        
        this.extraer(origen, monto);

        this.depositar(destino, monto); 
        
     
    }
}
