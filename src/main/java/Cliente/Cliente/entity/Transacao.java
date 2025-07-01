package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.TipoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transacao extends EntidadeDominio {

    private int numero;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Getter
    @Setter
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
