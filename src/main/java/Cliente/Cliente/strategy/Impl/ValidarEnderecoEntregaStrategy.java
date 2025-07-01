package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.entity.enums.TipoEndereco;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarEnderecoEntregaStrategy implements IStrategy {
    @Override
    public void executar(Cliente cliente) {
        boolean possuiEntrega = cliente.getEnderecos() != null &&
                cliente.getEnderecos().stream()
                        .anyMatch(end -> end.getTipoEndereco() == TipoEndereco.ENTREGA);

        if (!possuiEntrega) {
            throw new StrategyExecption("Cliente deve possuir ao menos um endereço de entrega.");
        }
    }
}
