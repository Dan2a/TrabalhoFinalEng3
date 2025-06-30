// MODAL CLIENTE
function abrirModalCliente() {
    const modal = document.getElementById('modalCliente');
    modal.classList.add('active');
}

function fecharModalCliente() {
    const modal = document.getElementById('modalCliente');
    modal.classList.remove('active');
}

// MODAL CONSULTA
function abrirModalConsulta() {
    const modal = document.getElementById('modalConsulta');
    modal.classList.add('active');
}

function fecharModalConsulta() {
    const modal = document.getElementById('modalConsulta');
    modal.classList.remove('active');
}

// Fecha o modal clicando fora dele
document.addEventListener('click', function(event) {
    const modalCliente = document.getElementById('modalCliente');
    const modalConsulta = document.getElementById('modalConsulta');

    if (modalCliente.classList.contains('active') && !modalCliente.querySelector('.modal').contains(event.target) && !event.target.closest('.btnAdd')) {
        fecharModalCliente();
    }

    if (modalConsulta.classList.contains('active') && !modalConsulta.querySelector('.modal').contains(event.target) && !event.target.closest('.btnAdd')) {
        fecharModalConsulta();
    }
});
