package Cliente.Cliente.strategy;

import Cliente.Cliente.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorRegrasCliente {

    private final List<IStrategy> regras;

    public ValidadorRegrasCliente(List<IStrategy> regras) {
        this.regras = regras;
    }

    public void validar(Cliente cliente) {
        for (IStrategy regra : regras) {
            regra.executar(cliente);
        }
    }
}
