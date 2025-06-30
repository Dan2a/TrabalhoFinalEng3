package Cliente.Cliente.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntidadeDominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dtCadastro;
}
