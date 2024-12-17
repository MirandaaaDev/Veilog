<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/home.css">
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
    %>
	<header>
        <nav>
            <ul>
                <li><a href="logout.jsp" id="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main class="main-content">
    <div class="container">  
        <% if (nivelUsuario != null && !nivelUsuario.equals("Funcionario")) { %>
            <div class="btn"><a href="listusers.jsp">Gerenciar Usuários</a></div>
        <% } %>
        <% if (nivelUsuario != null) { %>
        <div class="btn"><a href="listempresas.jsp">Gerenciar Empresas</a></div>
        <% } %>
        <% if (nivelUsuario == null) { %>
        <div class="btn"><a href="login.jsp">Realizar Login</a></div>
        <% } %>
    </div>
    </main>      
</body>
</html>
