package pe.edu.upeu.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private static final String URL =
            "jdbc:sqlite:src/main/resources/database/encomiendas.db";

    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void crearTablas() {

        String sql = """
                CREATE TABLE IF NOT EXISTS encomienda(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    codigo TEXT UNIQUE,
                    remitente TEXT,
                    destinatario TEXT,
                    origen TEXT,
                    destino TEXT,
                    descripcion TEXT,
                    peso REAL,
                    precio REAL,
                    estado TEXT,
                    fecha_envio TEXT
                );
                """;


        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

            System.out.println("Tabla encomienda creada correctamente");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}