package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.TipoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transacao extends EntidadeDominio {

    private int numero;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private TipoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
