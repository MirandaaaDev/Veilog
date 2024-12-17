<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listagem de Empresas</title>
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
                <li><a href="cadempresa.jsp">Cadastro de Empresas</a></li>
				<li><a href="logout.jsp" id="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </header>
    	<section>
        <h1>Lista de Empresas</h1>
        <div class="tbl-header">
        <table id="empresas-table" cellpadding="0" cellspacing="0" border="0">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>CNPJ</th>
            <th>Tipo de Pessoa</th>
            <th>CEP</th>
            <th>Rua</th>
            <th>Número Local</th>
            <th>Bairro</th>
            <th>Cidade</th>
            <th>UF</th>
            <th>Telefone</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
    </thead>
    
</table>
            </div>
        <table id="empresas-table" cellpadding="0" cellspacing="0" border="0">
            <tbody>
                <!-- Os dados das empresas serão inseridos aqui -->
            </tbody>
        </table>
    	</section>
    <script>
        $(document).ready(function() {
            // Requisição AJAX para buscar as empresas
            $.ajax({
                url: './ListEmpresasServlet', 
                type: 'GET',
                dataType: 'json',
                success: function(response) {
                    if (response.length > 0) {
                        // Preencher a tabela com os dados recebidos
                        response.forEach(function(empresa) {
                            var row = '<tr>' +
                                      '<td>' + empresa.id + '</td>' +
                                      '<td>' + empresa.nome + '</td>' +
                                      '<td>' + empresa.cnpj + '</td>' +
                                      '<td>' + empresa.tipoPessoa + '</td>' +
                                      '<td>' + empresa.cep + '</td>' +
                                      '<td>' + empresa.rua + '</td>' +
                                      '<td>' + empresa.numeroLocal + '</td>' +
                                      '<td>' + empresa.bairro + '</td>' +
                                      '<td>' + empresa.cidade + '</td>' +
                                      '<td>' + empresa.uf + '</td>' +
                                      '<td>' + empresa.telefone + '</td>' +
                                      '<td>' + empresa.email + '</td>' +
                                      '<td><button class="edit-btn" data-id="' + empresa.id + '">Editar</button></td>' +
                                      '</tr>';
                            $('#empresas-table tbody').append(row);
                        });

                        // Ação para editar a empresa
                        $('.edit-btn').click(function() {
                            var empresaId = $(this).data('id');
                            window.location.href = 'editempresa.jsp?id=' + empresaId;
                        });

                    } else {
                        $('#empresas-table tbody').append('<tr><td colspan="13">Nenhuma empresa encontrada</td></tr>');
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
