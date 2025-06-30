package Cliente.Cliente.repository;

import Cliente.Cliente.entity.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
    List<CartaoCredito> findByClienteId(Long clienteId);
}
