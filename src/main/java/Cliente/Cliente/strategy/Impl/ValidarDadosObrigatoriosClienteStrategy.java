package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.entity.Telefone;
import Cliente.Cliente.strategy.StrategyExecption;
import Cliente.Cliente.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidarDadosObrigatoriosClienteStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new StrategyExecption("O nome do cliente é obrigatório.");
        }

        if (cliente.getGenero() == null) {
            throw new StrategyExecption("O gênero do cliente é obrigatório.");
        }

        if (cliente.getDataNascimento() == null) {
            throw new StrategyExecption("A data de nascimento do cliente é obrigatória.");
        }

        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new StrategyExecption("O CPF do cliente é obrigatório.");
        }

        for (Telefone tel : cliente.getTelefones()) {
            if (tel.getTipo() == null || tel.getDdd() == null || tel.getDdd().isBlank()
                    || tel.getNumero() == null || tel.getNumero().isBlank()) {
                throw new StrategyExecption("Tipo, DDD e número do telefone são obrigatórios.");
            }
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new StrategyExecption("O e-mail do cliente é obrigatório.");
        }

        if (cliente.getSenha() == null || cliente.getSenha().isBlank()) {
            throw new StrategyExecption("A senha do cliente é obrigatória.");
        }
    }
}
