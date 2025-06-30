package Cliente.Cliente.facade;

import Cliente.Cliente.entity.*;
import Cliente.Cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteFacade {

    @Autowired private ClienteService clienteService;

    public Cliente criarCliente(Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    public Cliente editarCliente(Long id, Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }

    public void inativarCliente(Long id) {
        clienteService.inativar(id);
    }

    public List<Cliente> listarClientes(String nome) {
        return (nome != null && !nome.isBlank()) ?
                clienteService.buscarPorNome(nome) :
                clienteService.listarTodos();
    }

    public List<Endereco> listarEnderecos(Long clienteId) {
        return clienteService.listarEnderecos(clienteId);
    }

    public List<CartaoCredito> listarCartoes(Long clienteId) {
        return clienteService.listarCartoes(clienteId);
    }

    public List<Transacao> listarTransacoes(Long clienteId) {
        return clienteService.listarTransacoes(clienteId);
    }
}
