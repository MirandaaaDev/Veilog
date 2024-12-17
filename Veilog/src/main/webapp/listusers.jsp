<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Usuários</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/table.js"></script>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/table.css">
</head>
<body>
	<%
    	// Previne o cache das páginas
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    	response.setDateHeader("Expires", 0); // Para o proxy.
	%>
	<%
        // Recuperar atributos da sessão
        String email = (String) session.getAttribute("email");
        String nivelUsuario = (String) session.getAttribute("nivelUsuario");
        if (nivelUsuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
     %>
    <header>
        <nav>
            <ul>
            	<li><a href="home.jsp">Home</a></li>
                <li><a href="caduser.jsp">Cadastro de Usuários</a></li>
                <li><a href="logout.jsp" id="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </header>
    	<section>
        <h1>Lista de Usuários</h1>
        <div class="tbl-header">
        <table id="users-table" cellpadding="0" cellspacing="0" border="0">
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
                    <th>UF</th>
                    <th>Telefone</th>
                    <th>Status</th>
                    <th>Email</th>
                    <th>Senha</th>
                    <th>Nível de Usuário</th>
                    <th>Ações</th>
                </tr>
            </thead>
            </table>
            </div>
        <table id="users-table" cellpadding="0" cellspacing="0" border="0"> 
            <tbody>
                <!-- Os dados dos usuários serão inseridos aqui -->
            </tbody>
        </table>
    </section>
    
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
                                      '<td>' + user.uf + '</td>' +
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
                            window.location.href = 'edituser.jsp?id=' + userId;
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
