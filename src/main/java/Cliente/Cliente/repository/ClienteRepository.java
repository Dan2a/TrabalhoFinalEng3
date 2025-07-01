package Cliente.Cliente.repository;

import Cliente.Cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>,JpaSpecificationExecutor<Cliente> {
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
