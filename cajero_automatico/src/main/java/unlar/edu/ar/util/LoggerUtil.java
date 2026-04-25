package unlar.edu.ar.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatearLog(String tipo, double monto, double saldoResultante) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(formatter.format(LocalDateTime.now())).append("] ");
        sb.append(tipo).append(": $").append(String.format("%.2f", monto));
        sb.append(" | saldo: $").append(String.format("%.2f", saldoResultante));
        return sb.toString();
    }
}