package pe.edu.upeu.model;

import pe.edu.upeu.enums.EstadoEncomienda;

public class Encomienda {

    private int id;
    private String codigo;
    private String remitente;
    private String destinatario;
    private String origen;
    private String destino;
    private String descripcion;
    private double peso;
    private double precio;
    private EstadoEncomienda estado;
    private String fechaEnvio;

    public Encomienda() {

        this.codigo = codigo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.origen = origen;
        this.destino = destino;
        this.descripcion = descripcion;
        this.peso = peso;
        this.precio = precio;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
    }
    public boolean esEntregada() {
        return estado ==
                EstadoEncomienda.ENTREGADO;
    }

    public Encomienda(int id, String codigo, String remitente,
                      String destinatario, String origen,
                      String destino, String descripcion,
                      double peso, double precio,
                      EstadoEncomienda estado,
                      String fechaEnvio) {

        this.id = id;
        this.codigo = codigo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.origen = origen;
        this.destino = destino;
        this.descripcion = descripcion;
        this.peso = peso;
        this.precio = precio;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoEncomienda getEstado() {
        return estado;
    }

    public void setEstado(EstadoEncomienda estado) {
        this.estado = estado;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Override
    public String toString() {
        return "Encomienda{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", remitente='" + remitente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", precio=" + precio +
                ", estado=" + estado +
                '}';
    }

}