<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Cadastro de Empresa</title>
    <script src="js/table.js"></script>
    <script src="js/form-masks.js"></script>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/cadastro.css">
    
    <!-- ViaCEP -->
     <script>
        $(document).ready(function() {

            function limpa_formulário_cep() {
                // Limpa valores do formulário de cep.
                $("#rua").val("");
                $("#bairro").val("");
                $("#cidade").val("");
                $("#uf").val("");
            }
            
            //Quando o campo cep perde o foco.
            $("#cep").blur(function() {

                //Nova variável "cep" somente com dígitos.
                var cep = $(this).val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep != "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#rua").val("...");
                        $("#bairro").val("...");
                        $("#cidade").val("...");
                        $("#uf").val("...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#rua").val(dados.logradouro);
                                $("#bairro").val(dados.bairro);
                                $("#cidade").val(dados.localidade);
                                $("#uf").val(dados.uf);
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
        });

    </script>
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
                <li><a href="listempresas.jsp">Lista de Empresas</a></li>
                <li><a href="logout.jsp" id="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </header>

    <div class="form-container">
        <h2>Cadastro de Empresa</h2>
        <form id="cad-empresa-form" action="CadEmpresaServlet" method="POST">
            Nome: <input type="text" id="nome" name="nome" required><br>
            CNPJ: <input type="text" id="cnpj" name="cnpj" maxlength="18" oninput="mascaraCNPJ(this)" required><br>
            Tipo de Pessoa:
            <select id="tipoPessoa" name="tipoPessoa" required>
                <option value="Jurídica">Jurídica</option>
                <option value="Física">Física</option>
            </select><br>
            
            <label>CEP:
	        <input name="cep" type="text" id="cep"  value="" size="10" maxlength="9" /></label><br />
	        <label>Rua:
	        <input name="rua" type="text" id="rua" size="60" /></label><br />
	        <label>Bairro:
	        <input name="bairro" type="text" id="bairro"  size="40" /></label><br />
	        <label>Cidade:
	        <input name="cidade" type="text" id="cidade"  size="40" /></label><br />
	        <label>Estado:
	        <input name="uf" type="text" id="uf" size="2" /></label><br />
            Número Local: <input type="number" id="numeroLocal" name="numeroLocal" required><br>
            
            <!-- CEP: <input type="text" id="cep" name="cep" maxlength="9" oninput="mascaraCEP(this)" required><br>
            Endereço: <input type="text" id="rua" name="rua" required><br>
            Número Local: <input type="number" id="numeroLocal" name="numeroLocal" required><br>
            Bairro: <input type="text" id="bairro" name="bairro" required><br>
            Cidade: <input type="text" id="cidade" name="cidade" required><br>
            Estado: <input type="text" id="estado" name="estado" maxlength="2" oninput="validarEstado(this)" required><br> -->
            
            Telefone: <input type="text" id="telefone" name="telefone" maxlength="15" oninput="mascaraTelefone(this)" required><br>
            Email: <input type="email" id="email" name="email" required><br>
            <button type="submit">Cadastrar</button>
        </form>
    </div>
     <script>
     $(document).ready(function () {
    	 $('#cad-empresa-form').submit(function(event) {
 	        event.preventDefault();
 	        
    	 var formData = {
 	            nome: $('#nome').val(),
 	            cnpj: $('#cnpj').val(),
 	            tipoPessoa: $('#tipoPessoa').val(),
 	            cep: $('#cep').val(),
 	            rua: $('#rua').val(),
 	            numeroLocal: $('#numeroLocal').val(),
 	            bairro: $('#bairro').val(),
 	            cidade: $('#cidade').val(),
 	            uf: $('#uf').val(),
 	            telefone: $('#telefone').val(),
 	            email: $('#email').val(),
 	     };
    	 
    	 $.ajax({
             url: 'CadEmpresaServlet', 
             type: 'POST',
             data: formData, 
             dataType: 'json', 
             success: function(response) {
                 
                 alert(response.message);  
                 if (response.status === 'success') {
                     window.location.href = 'listempresas.jsp';  
                 }
             },
             error: function(xhr, status, error) {
                
                 alert('Erro ao processar a solicitação.');
             }
         });
     });
 });
    </script>
</body>
</html>
