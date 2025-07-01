package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.CartaoCredito;
import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarComposicaoCartaoStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getCartoesCredito() == null || cliente.getCartoesCredito().isEmpty()) {
            throw new StrategyExecption("O cliente deve possuir ao menos um cartão de crédito.");
        }

        for (CartaoCredito cartao : cliente.getCartoesCredito()) {
            if (isBlank(cartao.getNumeroCartao())) {
                throw new StrategyExecption("Número do cartão é obrigatório.");
            }

            if (isBlank(cartao.getNomeImpresso())) {
                throw new StrategyExecption("Nome impresso no cartão é obrigatório.");
            }

            if (cartao.getBandeira() == null) {
                throw new StrategyExecption("Bandeira do cartão é obrigatória.");
            }

            if (isBlank(cartao.getCodigoSeguranca())) {
                throw new StrategyExecption("Código de segurança é obrigatório.");
            }
        }
    }

    private boolean isBlank(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
