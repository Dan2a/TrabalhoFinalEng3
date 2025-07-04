package Cliente.Cliente.controller;

import Cliente.Cliente.entity.*;
import Cliente.Cliente.entity.enums.Status;
import Cliente.Cliente.facade.ClienteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired private ClienteFacade clienteFacade;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteFacade.criarCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteFacade.editarCliente(id, cliente));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Status> alterarStatus(@PathVariable Long id) {
        Status statusAtualizado = clienteFacade.alterarStatusCliente(id);
        return ResponseEntity.ok(statusAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(clienteFacade.listarClientes(nome));
    }

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<List<Transacao>> listarTransacoes(@PathVariable Long id) {
        return ResponseEntity.ok(clienteFacade.listarTransacoes(id));
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<Endereco>> listarEnderecos(@PathVariable Long id) {
        return ResponseEntity.ok(clienteFacade.listarEnderecos(id));
    }

    @GetMapping("/{id}/cartoes")
    public ResponseEntity<List<CartaoCredito>> listarCartoes(@PathVariable Long id) {
        return ResponseEntity.ok(clienteFacade.listarCartoes(id));
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<Cliente>> consultarComFiltro(@RequestBody Cliente filtro) {
        List<Cliente> clientes = clienteFacade.consultarComFiltro(filtro);
        return ResponseEntity.ok(clientes);
    }
}