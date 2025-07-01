package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.TipoEndereco;
import Cliente.Cliente.entity.enums.TiposLogradouros;
import Cliente.Cliente.entity.enums.TiposResidencias;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(length = 30)
    private TiposResidencias tipoResidencia;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private TiposLogradouros tipoLogradouro;

    private TipoEndereco tipoEndereco;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String observacoes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}