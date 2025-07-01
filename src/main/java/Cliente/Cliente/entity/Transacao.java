package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.TipoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(length = 20)
    private TipoStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
