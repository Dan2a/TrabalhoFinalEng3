package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.entity.Transacao;
import Cliente.Cliente.strategy.IStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalcularRankingClienteStrategy implements IStrategy {

    @Override
    public void executar(Cliente cliente) {
        if (cliente.getTransacoes() == null || cliente.getTransacoes().isEmpty()) {
            cliente.setRanking(1); // Sem histórico de compras
            return;
        }

        BigDecimal total = cliente.getTransacoes().stream()
                .map(Transacao::getValor)
                .filter(valor -> valor != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int ranking;

        if (total.compareTo(BigDecimal.valueOf(5000)) >= 0) {
            ranking = 5;
        } else if (total.compareTo(BigDecimal.valueOf(3000)) >= 0) {
            ranking = 4;
        } else if (total.compareTo(BigDecimal.valueOf(1000)) >= 0) {
            ranking = 3;
        } else if (total.compareTo(BigDecimal.valueOf(500)) >= 0) {
            ranking = 2;
        } else {
            ranking = 1;
        }

        cliente.setRanking(ranking);
    }
}


