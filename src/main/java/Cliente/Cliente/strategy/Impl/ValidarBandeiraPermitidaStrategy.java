package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.CartaoCredito;
import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.entity.enums.BandeiraCartao;
import Cliente.Cliente.strategy.IStrategy;
import Cliente.Cliente.strategy.StrategyExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarBandeiraPermitidaStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getCartoesCredito() == null || cliente.getCartoesCredito().isEmpty()) {
            return; // já foi validado em outra strategy
        }

        for (CartaoCredito cartao : cliente.getCartoesCredito()) {
            BandeiraCartao bandeira = cartao.getBandeira();

            if (bandeira == null) {
                throw new StrategyExecption("Bandeira do cartão não pode ser nula.");
            }

            boolean bandeiraPermitida = false;

            for (BandeiraCartao permitida : BandeiraCartao.values()) {
                if (bandeira == permitida) {
                    bandeiraPermitida = true;
                    break;
                }
            }

            if (!bandeiraPermitida) {
                throw new StrategyExecption("Bandeira do cartão não é permitida: " + bandeira);
            }
        }
    }
}
