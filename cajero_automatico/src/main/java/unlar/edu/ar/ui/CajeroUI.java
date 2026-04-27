package unlar.edu.ar.ui;

import unlar.edu.ar.model.CuentaBancaria;
import unlar.edu.ar.service.CajeroService;
import unlar.edu.ar.exception.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigDecimal;

public class CajeroUI {

    private final CajeroService servicio = new CajeroService();
    private final Scanner sc = new Scanner(System.in);

    public void iniciarMenu(CuentaBancaria cuentaActual, CuentaBancaria cuentaDestino) {
        int opcion = 0;

        do {
            System.out.println("\n===============================");
            System.out.println("   CAJERO AUTOMÁTICO UNLaR");
            System.out.println("===============================");
            System.out.println("Titular: " + cuentaActual.getTitular());
            // Formato de moneda: $XXX,XXX.00 
            System.out.println("Saldo: $" + String.format("%,.2f", cuentaActual.getSaldo()));
            System.out.println("-------------------------------");
            System.out.println("1. Depositar dinero");
            System.out.println("2. Extraer dinero");
            System.out.println("3. Transferir dinero");
            System.out.println("4. Ver historial (últimos 10)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = sc.nextInt();

                // Uso de switch expression 
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese monto a depositar: ");
                        BigDecimal monto = sc.nextBigDecimal();
                        servicio.depositar(cuentaActual, monto);
                        System.out.println("Depósito exitoso.");
                    }
                    case 2 -> {
                        System.out.print("Ingrese monto a extraer: ");
                        BigDecimal monto = sc.nextBigDecimal();
                        servicio.extraer(cuentaActual, monto);
                        System.out.println("Extracción exitosa.");
                    }
                    case 3 -> {
                        System.out.print("Ingrese monto a transferir: ");
                        BigDecimal monto = sc.nextBigDecimal();
                        servicio.transferir(cuentaActual, cuentaDestino, monto);
                        System.out.println("Transferencia exitosa.");
                    }
                    case 4 -> {
                        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
                        // Muestra las últimas 10 transacciones 
                        cuentaActual.getHistorialTransacciones().forEach(System.out::println);
                    }
                    case 5 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida.");
                }

            } catch (InputMismatchException e) {
                // Validación de entrada numérica 
                System.err.println("Error: Debe ingresar un número válido.");
                sc.nextLine();   // Limpia el buffer
            } catch (SaldoInsuficienteException | LimiteExtraccionExcedidoException | CuentaInactivaException e) {
                // Manejo de excepciones personalizadas 
                System.err.println("Error en la operación: " + e.getMessage());
            }

        } while (opcion != 5); // Bucle principal con opción de salida 
    }
}

