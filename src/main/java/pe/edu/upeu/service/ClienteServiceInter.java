package pe.edu.upeu.service;

import pe.edu.upeu.model.Cliente;

import java.util.List;

public interface ClienteServiceInter {

    boolean guardar(Cliente cliente);

    boolean actualizar(Cliente cliente);

    boolean eliminar(int id);

    List<Cliente> listar();

}