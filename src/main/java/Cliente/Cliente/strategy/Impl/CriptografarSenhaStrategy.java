package Cliente.Cliente.strategy.Impl;

import Cliente.Cliente.entity.Cliente;
import Cliente.Cliente.strategy.IStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CriptografarSenhaStrategy implements IStrategy {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void executar(Cliente cliente) {
        String senhaOriginal = cliente.getSenha();

        // Verifica se já está criptografada para evitar duplas criptografias
        if (!senhaOriginal.startsWith("$2a$")) {
            String senhaCriptografada = encoder.encode(senhaOriginal);
            cliente.setSenha(senhaCriptografada);
        }
    }
}
