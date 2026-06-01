package pe.edu.upeu.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorCodigo {

    public static String generarCodigo() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "yyyyMMddHHmmss"
                );

        return "ENC-" +
                LocalDateTime.now()
                        .format(formato);
    }
}