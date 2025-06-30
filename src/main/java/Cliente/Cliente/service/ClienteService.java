package Cliente.Cliente.service;

import Cliente.Cliente.entity.*;
import Cliente.Cliente.entity.enums.*;
import Cliente.Cliente.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired private ClienteRepository clienteRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private CartaoCreditoRepository cartaoCreditoRepository;
    @Autowired private TransacaoRepository transacaoRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Cliente salvar(Cliente cliente) {
        cliente.setStatus(Status.ATIVO);
        cliente.setDtCadastro(System.currentTimeMillis());
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));

        if (cliente.getEnderecos() == null || cliente.getEnderecos().isEmpty()) {
            throw new IllegalArgumentException("Cliente deve ter ao menos um endereço cadastrado.");
        }

        if (cliente.getCartoesCredito() == null || cliente.getCartoesCredito().isEmpty()) {
            throw new IllegalArgumentException("Cliente deve ter ao menos um cartão de crédito cadastrado.");
        }

        cliente.getEnderecos().forEach(e -> e.setCliente(cliente));
        cliente.getCartoesCredito().forEach(c -> c.setCliente(cliente));

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente existente = clienteRepository.findById(id).orElseThrow();
        clienteAtualizado.setId(id);
        clienteAtualizado.setDtCadastro(existente.getDtCadastro());
        return clienteRepository.save(clienteAtualizado);
    }

    public void inativar(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setStatus(Status.INATIVO);
        clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Endereco> listarEnderecos(Long clienteId) {
        return enderecoRepository.findByClienteId(clienteId);
    }

    public List<CartaoCredito> listarCartoes(Long clienteId) {
        return cartaoCreditoRepository.findByClienteId(clienteId);
    }

    public List<Transacao> listarTransacoes(Long clienteId) {
        return transacaoRepository.findByClienteId(clienteId);
    }
}