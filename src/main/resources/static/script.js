// MODAL CLIENTE
function abrirModalCliente() {
  const modal = document.getElementById("modalCliente");
  modal.classList.add("active");
}

function fecharModalCliente() {
  const modal = document.getElementById("modalCliente");
  modal.classList.remove("active");
}

// MODAL CONSULTA
function abrirModalConsulta() {
  const modal = document.getElementById("modalConsulta");
  modal.classList.add("active");
}

function fecharModalConsulta() {
  const modal = document.getElementById("modalConsulta");
  modal.classList.remove("active");
}

// Fecha o modal clicando fora dele
document.addEventListener("click", function (event) {
  const modalCliente = document.getElementById("modalCliente");
  const modalConsulta = document.getElementById("modalConsulta");

  if (
    modalCliente.classList.contains("active") &&
    !modalCliente.querySelector(".modal").contains(event.target) &&
    !event.target.closest(".btnAdd")
  ) {
    fecharModalCliente();
  }

  if (
    modalConsulta.classList.contains("active") &&
    !modalConsulta.querySelector(".modal").contains(event.target) &&
    !event.target.closest(".btnAdd")
  ) {
    fecharModalConsulta();
  }
});

// Adicionar Telefones
let telefoneIndex = 0;

function adicionarTelefone() {
  const container = document.getElementById("telefonesContainer");

  const div = document.createElement("div");
  div.classList.add("telefoneLinha");

  div.innerHTML = `
    <div class="form-group">
      <label>Tipo</label>
      <select class="modalInput" name="telefones[${telefoneIndex}].tipo" required>
        <option value="">Tipo</option>
        <option value="CELULAR">Celular</option>
        <option value="FIXO">Fixo</option>
        <option value="COMERCIAL">Comercial</option>
      </select>
    </div>

    <div class="form-group">
      <label>DDD</label>
      <input class="modalInput" type="text" name="telefones[${telefoneIndex}].ddd" placeholder="DDD" required>
    </div>

    <div class="form-group">
      <label>Número</label>
      <input class="modalInput" type="text" name="telefones[${telefoneIndex}].numero" placeholder="Número" required>
    </div>

    <button class="btnRemoverTel" aria-label="Fechar" onclick="removerTelefone(this, event)">
        <i class='bx bx-x'></i>
    </button>
  `;

  container.appendChild(div);
  telefoneIndex++;
}

function removerTelefone(botao, event) {
  event.stopPropagation();
  botao.parentElement.remove();
}

//Adicionar Endereços

let enderecoIndex = 0;

function adicionarEndereco() {
  const container = document.getElementById("enderecosContainer");

  const div = document.createElement("div");
  div.classList.add("enderecoLinha");
  div.style.border = "1px solid #ccc";
  div.style.padding = "10px";
  div.style.marginBottom = "10px";
  div.style.borderRadius = "5px";

  div.innerHTML = `
    <div>
      <label for="enderecos[${enderecoIndex}].tipoResidencia">Tipo de Residência</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].tipoResidencia" required>
    </div>
    <div>
      <label for="enderecos[${enderecoIndex}].tipoLogradouro">Tipo de Logradouro</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].tipoLogradouro" required>
    </div>
    <div class="formColFull">
      <label for="enderecos[${enderecoIndex}].logradouro">Logradouro</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].logradouro" required>
    </div>
    <div>
      <label for="enderecos[${enderecoIndex}].numero">Número</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].numero" required>
    </div>
    <div>
      <label for="enderecos[${enderecoIndex}].bairro">Bairro</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].bairro" required>
    </div>
    <div>
      <label for="enderecos[${enderecoIndex}].cep">CEP</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].cep" required>
    </div>
    <div>
      <label for="enderecos[${enderecoIndex}].cidade">Cidade</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].cidade" required>
    </div>
    <div>
      <label for="enderecos[${enderecoIndex}].estado">Estado</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].estado" required>
    </div>
    <div class="formColFull">
      <label for="enderecos[${enderecoIndex}].pais">País</label>
      <input class="modalInput" type="text" name="enderecos[${enderecoIndex}].pais" required>
    </div>
    <div class="formColFull">
      <label for="enderecos[${enderecoIndex}].observacoes">Observações</label>
      <textarea class="modalInput" name="enderecos[${enderecoIndex}].observacoes"></textarea>
    </div>

    <button type="button" class="btnRemoverTel" onclick="removerEndereco(this)">🗑️ Remover Endereço</button>
  `;

  container.appendChild(div);
  enderecoIndex++;
}

function removerEndereco(botao) {
  botao.parentElement.remove();
}

document.getElementById("btnBuscarCep").addEventListener("click", function () {
  const cepInput = document.getElementById("cep");
  const cep = cepInput.value.replace(/\D/g, '');
  if (cep.length === 8) {
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
      .then(response => response.json())
      .then(data => {
        if (!data.erro) {
          // Preencher os campos
          // Note que ViaCEP não retorna tipoLogradouro, então usuário deve selecionar manualmente
          document.getElementById("logradouro").value = data.logradouro || "";
          document.getElementById("bairro").value = data.bairro || "";
          document.getElementById("cidade").value = data.localidade || "";
          document.getElementById("estado").value = data.uf || "";
          document.getElementById("pais").value = "Brasil";
        } else {
          alert("CEP não encontrado.");
        }
      })
      .catch(() => alert("Erro ao buscar o CEP."));
  } else {
    alert("Por favor, digite um CEP válido.");
    cepInput.focus();
  }
});


