package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ConfirmacaoSenhaStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getConfirmarSenha() == null || !cliente.getSenha().equals(cliente.getConfirmarSenha())) {
            System.out.println("Senha: " + cliente.getSenha());
            System.out.println("ConfirmarSenha: " + cliente.getConfirmarSenha());
            throw new StrategyExecption("A confirmação de senha não confere.");
        }
    }
}
