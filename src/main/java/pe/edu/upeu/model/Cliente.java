package pe.edu.upeu.model;

public class Cliente {

    private int id;
    private String nombreCompleto;
    private String dni;
    private String telefono;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int id, String nombreCompleto,
                   String dni, String telefono,
                   String direccion) {

        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Generar Getters y Setters
}