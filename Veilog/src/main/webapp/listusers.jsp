<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Usuários</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="user-list-container">
        <h2>Lista de Usuários</h2>
        <table id="users-table" border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Data de Nascimento</th>
                    <th>CPF</th>
                    <th>CEP</th>
                    <th>Rua</th>
                    <th>Número Local</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>Estado</th>
                    <th>Telefone</th>
                    <th>Status</th>
                    <th>Email</th>
                    <th>Senha</th>
                    <th>Nível de Usuário</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <!-- Os dados dos usuários serão inseridos aqui -->
            </tbody>
        </table>
    </div>

    <script>
        $(document).ready(function() {
            // Requisição AJAX para buscar os usuários
            $.ajax({
                url: './ListUsersServlet', 
                type: 'GET',
                dataType: 'json',
                success: function(response) {
                    if (response.length > 0) {
                        // Preencher a tabela com os dados recebidos
                        response.forEach(function(user) {
                            var row = '<tr>' +
                                      '<td>' + user.id + '</td>' +
                                      '<td>' + user.nome + '</td>' +
                                      '<td>' + user.dataNascimento + '</td>' +
                                      '<td>' + user.cpf + '</td>' +
                                      '<td>' + user.cep + '</td>' +
                                      '<td>' + user.rua + '</td>' +
                                      '<td>' + user.numeroLocal + '</td>' +
                                      '<td>' + user.bairro + '</td>' +
                                      '<td>' + user.cidade + '</td>' +
                                      '<td>' + user.estado + '</td>' +
                                      '<td>' + user.telefone + '</td>' +
                                      '<td>' + user.status + '</td>' +
                                      '<td>' + user.email + '</td>' +
                                      '<td>' + user.senha + '</td>' +
                                      '<td>' + user.nivelUsuario + '</td>' +
                                      '<td><button class="edit-btn" data-id="' + user.id + '">Editar</button></td>' +
                                      '</tr>';
                            $('#users-table tbody').append(row);
                        });

                        // Ação para editar o usuário
                        $('.edit-btn').click(function() {
                            var userId = $(this).data('id');
                            window.location.href = './EditUserServlet?id=' + userId;
                        });

                    } else {
                        $('#users-table tbody').append('<tr><td colspan="15">Nenhum usuário encontrado</td></tr>');
                    }
                },
                error: function(xhr, status, error) {
                    alert('Erro ao processar a requisição.');
                }
            });
        });
    </script>
</body>
</html>
