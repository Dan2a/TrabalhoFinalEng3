package Cliente.Cliente.entity;

import Cliente.Cliente.entity.enums.Genero;
import Cliente.Cliente.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends EntidadeDominio {

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Genero genero;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;

    private String email;
    private String senha;
    private int ranking;

    @Transient
    private String confirmarSenha;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartaoCredito> cartoesCredito;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;
}
