package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.TiposLogradouros;
import Cliente.Cliente.entity.enums.TiposResidencias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends EntidadeDominio {

    @Enumerated(EnumType.STRING)
    private TiposResidencias tipoResidencia;

    @Enumerated(EnumType.STRING)
    private TiposLogradouros tipoLogradouro;

    private String tipoEndereco; // ENTREGA, COBRANCA, etc
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}