package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.entity.Endereco;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarComposicaoEnderecoStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getEnderecos() == null || cliente.getEnderecos().isEmpty()) {
            throw new StrategyExecption("O cliente deve possuir ao menos um endereço.");
        }

        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.getTipoResidencia() == null) {
                throw new StrategyExecption("Tipo de residência é obrigatório.");
            }

            if (endereco.getTipoLogradouro() == null) {
                throw new StrategyExecption("Tipo de logradouro é obrigatório.");
            }

            if (isBlank(endereco.getLogradouro())) {
                throw new StrategyExecption("Logradouro é obrigatório.");
            }

            if (isBlank(endereco.getNumero())) {
                throw new StrategyExecption("Número do endereço é obrigatório.");
            }

            if (isBlank(endereco.getBairro())) {
                throw new StrategyExecption("Bairro é obrigatório.");
            }

            if (isBlank(endereco.getCep())) {
                throw new StrategyExecption("CEP é obrigatório.");
            }

            if (isBlank(endereco.getCidade())) {
                throw new StrategyExecption("Cidade é obrigatória.");
            }

            if (isBlank(endereco.getEstado())) {
                throw new StrategyExecption("Estado é obrigatório.");
            }

            if (isBlank(endereco.getPais())) {
                throw new StrategyExecption("País é obrigatório.");
            }
        }
    }

    private boolean isBlank(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
