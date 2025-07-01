package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarConfirmacaoSenhaStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getConfirmarSenha() == null || !cliente.getSenha().equals(cliente.getConfirmarSenha())) {
            throw new StrategyExecption("A confirmação de senha não confere.");
        }
    }
}
