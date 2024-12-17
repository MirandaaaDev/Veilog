<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Empresa</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="styles/cadastro.css">
    <link rel="stylesheet" href="styles/edit.css">
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="listempresas.jsp">Lista de Empresas</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <div class="form-container">
        <h2>Editar Empresa</h2>
        <form id="edit-empresa-form" action="./UpdateEmpresaServlet" method="POST">
            <input type="hidden" name="id" id="id"/>

            Nome: <input type="text" id="nome" name="nome" required/><br> 
            CNPJ: <input type="text" id="cnpj" name="cnpj" maxlength="14" required/><br>
            CEP: <input type="text" id="cep" name="cep" maxlength="9" required/><br>
            Rua: <input type="text" id="rua" name="rua" required/><br>
            Bairro: <input type="text" id="bairro" name="bairro" required/><br>
            Cidade: <input type="text" id="cidade" name="cidade" required/><br>
            UF: <input type="text" id="uf" name="uf" maxlength="2" required/><br>
            Número Local: <input type="text" id="numeroLocal" name="numeroLocal" required/><br>
            Telefone: <input type="text" id="telefone" name="telefone" maxlength="15" required/><br>
            Email: <input type="email" id="email" name="email" required/><br>
            Nível de Usuário: <input type="text" id="tipoPessoa" name="tipoPessoa" disabled/><br>
            Novo Tipo Pessoa:
            <select name="tipoPessoa" required>
                <option value="Jurídica">Jurídica</option>
                <option value="Física">Física</option>
            </select><br>
            <button type="submit">Salvar</button>
        </form>
    </div>

    <script>
    $(document).ready(function() {
        var empresaId = new URLSearchParams(window.location.search).get('id');
        
        // Preenche os campos do formulário com os dados do usuário
        $.ajax({
            url: './EditEmpresaServlet?id=' + empresaId,
            type: 'GET',
            dataType: 'json',
            success: function(empresa) {
                $('#id').val(empresa.id);
                $('#nome').val(empresa.nome);
                $('#tipoPessoa').val(empresa.tipoPessoa);
                $('#cnpj').val(empresa.cnpj);
                $('#cep').val(empresa.cep);
                $('#rua').val(empresa.rua);
                $('#bairro').val(empresa.bairro);
                $('#cidade').val(empresa.cidade);
                $('#uf').val(empresa.uf);
                $('#numeroLocal').val(empresa.numeroLocal);
                $('#telefone').val(empresa.telefone);
                $('#email').val(empresa.email);
            },
            error: function(xhr, status, error) {
                alert('Erro ao carregar os dados da empresa.');
            }
        });

        // Ao submeter o formulário de edição
        $('#edit-empresa-form').on('submit', function(e) {
            e.preventDefault();  // Impede o envio do formulário

            var formData = $(this).serialize();  // Serializa os dados do formulário

            // Envia os dados para o UpdateEmpresaServlet via AJAX
            $.ajax({
                url: './UpdateEmpresaServlet',
                type: 'POST',
                data: formData,
                dataType: 'json',
                success: function(response) {
                    alert(response.message);  // Exibe o alerta com a mensagem
                    if (response.status === 'success') {
                        window.location.href = 'listempresas.jsp';  // Redireciona para listempresas.jsp
                    }
                },
                error: function(xhr, status, error) {
                    alert('Erro ao atualizar o usuário.');
                }
            });
        });
    });	
    </script>
</body>
</html>
