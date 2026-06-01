package pe.edu.upeu.service;

import pe.edu.upeu.model.Cliente;
import pe.edu.upeu.repository.ClienteRepository;

import java.util.List;

public class ClienteServiceImp implements ClienteServiceInter {

    private final ClienteRepository repository;

    public ClienteServiceImp() {
        repository = new ClienteRepository();
    }

    @Override
    public boolean guardar(Cliente cliente) {
        return repository.guardar(cliente);
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        return repository.actualizar(cliente);
    }

    @Override
    public boolean eliminar(int id) {
        return repository.eliminar(id);
    }

    @Override
    public List<Cliente> listar() {
        return repository.listar();
    }
}