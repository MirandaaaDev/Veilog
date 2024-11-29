<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="repository.DBConnection" %> <!-- Certifique-se de que o pacote está correto -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teste de Conexão com o Banco</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .success {
            color: green;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Teste de Conexão com o Banco de Dados</h1>
    <%
        DBConnection dbConnection = new DBConnection(); // Instância da classe

        try {
            // Tentando obter a conexão
            Connection connection = dbConnection.getConnection();

            if (connection != null && !connection.isClosed()) {
                out.println("<p class='success'>Conexão com o banco de dados foi bem-sucedida!</p>");
            } else {
                out.println("<p class='error'>Falha ao conectar ao banco de dados.</p>");
            }

            // Fechando a conexão
            dbConnection.close();
        } catch (SQLException e) {
            // Exibindo erros, se ocorrerem
            out.println("<p class='error'>Erro ao conectar ao banco de dados:</p>");
            out.println("<pre>" + e.getMessage() + "</pre>");
        }
    %>
</body>
</html>