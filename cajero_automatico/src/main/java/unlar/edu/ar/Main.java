package unlar.edu.ar;

import unlar.edu.ar.model.CuentaBancaria;
import unlar.edu.ar.service.CajeroService;
import unlar.edu.ar.ui.CajeroUI;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){

        CajeroService servicio = new CajeroService();  // se inicializa el sistema

        // se crean 3 cuentas
        CuentaBancaria cuenta1 = new CuentaBancaria("101", "Javier", new BigDecimal("50000.00"));
        CuentaBancaria cuenta2 = new CuentaBancaria("102", "Maria", new BigDecimal("15000.00"));
        CuentaBancaria cuenta3 = new CuentaBancaria("103", "Fernando", new BigDecimal("1000.00"));

        // agrega las cuentas
        servicio.agregarCuenta(cuenta1);
        servicio.agregarCuenta(cuenta2);
        servicio.agregarCuenta(cuenta3);

        System.out.println("---- Iniciando simulación de 15 transacciones ----");

        try {
            // para la cuenta 1
            servicio.depositar(cuenta1, new BigDecimal("500.00"));
            servicio.extraer(cuenta1, new BigDecimal("200.00"));
            servicio.extraer(cuenta1, new BigDecimal("1000.00"));
            servicio.transferir(cuenta1,cuenta2, new BigDecimal("2000.00"));

            // cuenta 2
            servicio.depositar(cuenta2, new BigDecimal("3000.00"));
            servicio.extraer(cuenta2, new BigDecimal("4000.00"));
            servicio.transferir(cuenta2, cuenta3, new BigDecimal("1200.00"));
            servicio.extraer(cuenta2, new BigDecimal("800.00"));

            // cuenta 3
            servicio.depositar(cuenta3, new BigDecimal("10000.00"));
            servicio.extraer(cuenta3, new BigDecimal("500.00"));
            servicio.transferir(cuenta3, cuenta2, new BigDecimal("2500.00"));

            try {
                servicio.extraer(cuenta3, new BigDecimal("20000.00"));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage()); // una excepcion de limite excedido
            }

            try {
                servicio.extraer(cuenta1, new BigDecimal("15000.00"));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            servicio.depositar(cuenta2, new BigDecimal("7000.00"));
            servicio.transferir(cuenta1, cuenta3, new BigDecimal("3400"));


            // se inicia la interfaz de usuario para el usuario final
            CajeroUI interfaz = new CajeroUI(servicio);
            interfaz.iniciarMenu(cuenta1, cuenta3);

        } catch (Exception e) {
            System.err.println("Error crítico en el sistema: " + e.getMessage());
        }
    }

}
