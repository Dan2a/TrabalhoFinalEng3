package Cliente.Cliente.service;

import Cliente.Cliente.entity.*;
import Cliente.Cliente.entity.enums.*;
import Cliente.Cliente.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;

@Service
public class ClienteService {

    @Autowired private ClienteRepository clienteRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private CartaoCreditoRepository cartaoCreditoRepository;
    @Autowired private TransacaoRepository transacaoRepository;

    public Cliente salvar(Cliente cliente) {
        cliente.setStatus(Status.ATIVO);
        cliente.setDtCadastro(System.currentTimeMillis());
        cliente.getEnderecos().forEach(e -> e.setCliente(cliente));
        cliente.getTelefones().forEach(t -> t.setCliente(cliente));
        cliente.getCartoesCredito().forEach(c -> c.setCliente(cliente));

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        // Atualizar atributos simples
        existente.setNome(clienteAtualizado.getNome());
        existente.setGenero(clienteAtualizado.getGenero());
        existente.setCpf(clienteAtualizado.getCpf());
        existente.setEmail(clienteAtualizado.getEmail());
        existente.setSenha(clienteAtualizado.getSenha());
        existente.setRanking(clienteAtualizado.getRanking());
        existente.setDataNascimento(clienteAtualizado.getDataNascimento());
        existente.setStatus(clienteAtualizado.getStatus());

        // Telefones
        if (clienteAtualizado.getTelefones() != null) {
            existente.getTelefones().clear();
            for (Telefone telefone : clienteAtualizado.getTelefones()) {
                telefone.setCliente(existente);
                existente.getTelefones().add(telefone);
            }
        }

        // Endereços
        if (clienteAtualizado.getEnderecos() != null) {
            existente.getEnderecos().clear();
            for (Endereco endereco : clienteAtualizado.getEnderecos()) {
                endereco.setCliente(existente);
                existente.getEnderecos().add(endereco);
            }
        }

        // Cartões de Crédito
        if (clienteAtualizado.getCartoesCredito() != null) {
            existente.getCartoesCredito().clear();
            for (CartaoCredito cartao : clienteAtualizado.getCartoesCredito()) {
                cartao.setCliente(existente);
                existente.getCartoesCredito().add(cartao);
            }
        }

        return clienteRepository.save(existente);
    }

    public Status alterarStatus(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if (cliente.getStatus() == Status.ATIVO) {
            cliente.setStatus(Status.INATIVO);
        } else {
            cliente.setStatus(Status.ATIVO);
        }

        clienteRepository.save(cliente);
        return cliente.getStatus();
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

    public List<Cliente> consultarComFiltro(Cliente filtro) {
        Specification<Cliente> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (filtro.getGenero() != null) {
                predicates.add(cb.equal(root.get("genero"), filtro.getGenero()));
            }

            if (filtro.getDataNascimento() != null) {
                predicates.add(cb.equal(root.get("dataNascimento"), filtro.getDataNascimento()));
            }

            if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
                predicates.add(cb.equal(root.get("cpf"), filtro.getCpf()));
            }

            if (filtro.getEmail() != null && !filtro.getEmail().isEmpty()) {
                predicates.add(cb.equal(root.get("email"), filtro.getEmail()));
            }

            if (filtro.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filtro.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return clienteRepository.findAll(spec);
    }

}