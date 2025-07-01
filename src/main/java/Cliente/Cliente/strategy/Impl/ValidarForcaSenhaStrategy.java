package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarForcaSenhaStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        String senha = cliente.getSenha();
        if (senha == null || senha.length() < 8 ||
                !senha.matches(".*[a-z].*") || // minúscula
                !senha.matches(".*[A-Z].*") || // maiúscula
                !senha.matches(".*[^a-zA-Z0-9].*")) { // caractere especial

            throw new StrategyExecption("A senha deve ter ao menos 8 caracteres, com letras maiúsculas, minúsculas e um caractere especial.");
        }
    }
}
