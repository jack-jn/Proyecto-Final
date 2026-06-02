package pe.edu.upeu.service;

import pe.edu.upeu.model.Encomienda;

import java.util.List;

public interface EncomiendaServiceInter {

    boolean actualizarEstado(int id, String estado);

    boolean guardar(Encomienda encomienda);

    boolean actualizar(Encomienda encomienda);

    boolean eliminar(int id);

    List<Encomienda> listar();

    Encomienda buscarPorCodigo(String codigo);

    int contarTotal();

    int contarPorEstado(String estado);
}