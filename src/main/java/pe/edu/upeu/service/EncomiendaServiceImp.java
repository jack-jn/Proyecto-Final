package pe.edu.upeu.service;

import pe.edu.upeu.model.Encomienda;
import pe.edu.upeu.repository.EncomiendaRepository;

import java.util.List;

public class EncomiendaServiceImp implements EncomiendaServiceInter {

    private final EncomiendaRepository repository;

    public EncomiendaServiceImp() {
        repository = new EncomiendaRepository();
    }

    @Override
    public boolean guardar(Encomienda encomienda) {
        return repository.guardar(encomienda);
    }

    @Override
    public boolean actualizar(Encomienda encomienda) {
        return repository.actualizar(encomienda);
    }

    @Override
    public boolean eliminar(int id) {
        return repository.eliminar(id);
    }

    @Override
    public List<Encomienda> listar() {
        return repository.listar();
    }

    @Override
    public Encomienda buscarPorCodigo(String codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    @Override
    public boolean actualizarEstado(int id, String estado) {
        return repository.actualizarEstado(id, estado);
    }

    @Override
    public int contarTotal() {
        return repository.contarTotal();
    }

    @Override
    public int contarPorEstado(String estado) {
        return repository.contarPorEstado(estado);
    }
}