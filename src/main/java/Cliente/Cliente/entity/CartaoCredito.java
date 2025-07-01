package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.BandeiraCartao;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCredito extends EntidadeDominio {

    private String numero;
    private String nomeImpresso;
    private BandeiraCartao bandeira;
    private String codigoSeguranca;
    private Boolean preferencial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
