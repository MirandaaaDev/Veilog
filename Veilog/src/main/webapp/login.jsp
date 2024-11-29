<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="style.css"> <!-- Opcional: Adicione seu CSS -->
</head>
<body>
    <div class="login-container">
        <form id="login-form">
            <h2>Login</h2>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Digite seu email" required>
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" placeholder="Digite sua senha" required>
            <button type="submit">Entrar</button>
        </form>
    </div>

    <script>
        $(document).ready(function() {
            $('#login-form').submit(function(event) {
                event.preventDefault();

                $.ajax({
                    url: './LoginServlet', 
                    type: 'POST',
                    data: { 
                        email: $('#email').val(), 
                        senha: $('#senha').val()
                    },
                    dataType: 'json',
                    success: function(response) {
                        if (response.status === 'success') {
                            alert('Bem-vindo, ' + response.nome);
                            // Redireciona para home.jsp após login
                            window.location.href = 'home.jsp';
                        } else {
                            alert(response.message);
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Erro ao processar a requisição.');
                    }
                });
            });
        });
    </script>
</body>
</html>