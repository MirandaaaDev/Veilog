<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuário</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="styles/cadastro.css">
    <link rel="stylesheet" href="styles/edit.css">
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="listusers.jsp">Lista de Usuários</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <div class="form-container">
        <h2>Editar Usuário</h2>
        <form id="edit-user-form" action="./UpdateUserServlet" method="POST">
            <input type="hidden" name="id" id="id"/>

            Nome: <input type="text" id="nome" name="nome" required/><br>
            Data de Nascimento: <input type="date" id="dataNascimento" name="dataNascimento" required/><br>
            CPF: <input type="text" id="cpf" name="cpf" maxlength="14" required/><br>
            CEP: <input type="text" id="cep" name="cep" maxlength="9" required/><br>
            Rua: <input type="text" id="rua" name="rua" required/><br>
            Número Local: <input type="text" id="numeroLocal" name="numeroLocal" required/><br>
            Bairro: <input type="text" id="bairro" name="bairro" required/><br>
            Cidade: <input type="text" id="cidade" name="cidade" required/><br>
            Estado: <input type="text" id="uf" name="uf" maxlength="2" required/><br>
            Telefone: <input type="text" id="telefone" name="telefone" maxlength="15" required/><br>
            Email: <input type="email" id="email" name="email" required/><br>
            Senha: <input type="password" id="senha" name="senha" required/><br>
            Nível de Usuário: <input type="text" id="nivelUsuario" name="nivelUsuario" disabled/><br>
            Novo Nível de Usuário:
            <select name="nivelUsuario" required>
                <option value="Administrador">Administrador</option>
                <option value="Funcionário">Funcionário</option>
            </select><br>
            <button type="submit">Salvar</button>
        </form>
    </div>

    <script>
    $(document).ready(function() {
        var userId = new URLSearchParams(window.location.search).get('id');
        
        // Preenche os campos do formulário com os dados do usuário
        $.ajax({
            url: './EditUserServlet?id=' + userId,
            type: 'GET',
            dataType: 'json',
            success: function(user) {
                $('#id').val(user.id);
                $('#nome').val(user.nome);
                const dataParts = new Date(user.dataNascimento).toISOString().split('T')[0];
                $('#dataNascimento').val(dataParts);
                $('#cpf').val(user.cpf);
                $('#cep').val(user.cep);
                $('#rua').val(user.rua);
                $('#numeroLocal').val(user.numeroLocal);
                $('#bairro').val(user.bairro);
                $('#cidade').val(user.cidade);
                $('#uf').val(user.uf);
                $('#telefone').val(user.telefone);
                $('#email').val(user.email);
                $('#senha').val(user.senha);
                $('#nivelUsuario').val(user.nivelUsuario);
            },
            error: function(xhr, status, error) {
                alert('Erro ao carregar os dados do usuário.');
            }
        });

        // Ao submeter o formulário de edição
        $('#edit-user-form').on('submit', function(e) {
            e.preventDefault();  // Impede o envio do formulário

            var formData = $(this).serialize();  // Serializa os dados do formulário

            // Envia os dados para o UpdateUserServlet via AJAX
            $.ajax({
                url: './UpdateUserServlet',
                type: 'POST',
                data: formData,
                dataType: 'json',
                success: function(response) {
                    alert(response.message);  // Exibe o alerta com a mensagem
                    if (response.status === 'success') {
                        window.location.href = 'listusers.jsp';  // Redireciona para listusers.jsp
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
