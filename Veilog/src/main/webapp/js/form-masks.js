// Máscara para CPF
        function mascaraCPF(campo) {
            let valor = campo.value.replace(/\D/g, ""); // Remove tudo que não for número
            valor = valor.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca o primeiro ponto
            valor = valor.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca o segundo ponto
            valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1-$2"); // Coloca o traço
            campo.value = valor;
        }
		
// Máscara para CNPJ
		function mascaraCNPJ(campo) {
		    let valor = campo.value.replace(/\D/g, ""); // Remove tudo que não for número
		    valor = valor.replace(/^(\d{2})(\d)/, "$1.$2"); // Coloca o primeiro ponto
		    valor = valor.replace(/(\d{3})(\d)/, "$1.$2"); // Coloca o segundo ponto
		    valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1/$2"); // Coloca a barra
		    valor = valor.replace(/(\d{4})(\d{2})$/, "$1-$2"); // Coloca o traço
		    campo.value = valor;
		}


// Máscara para CEP
        function mascaraCEP(campo) {
            let valor = campo.value.replace(/\D/g, ""); // Remove tudo que não for número
            valor = valor.replace(/(\d{5})(\d)/, "$1-$2"); // Coloca o traço
            campo.value = valor;
        }

// Máscara para Telefone
        function mascaraTelefone(campo) {
            let valor = campo.value.replace(/\D/g, ""); // Remove tudo que não for número
            valor = valor.replace(/(\d{2})(\d)/, "($1) $2"); // Adiciona parênteses no DDD
            valor = valor.replace(/(\d{4,5})(\d{4})$/, "$1-$2"); // Adiciona o traço
            campo.value = valor;
        }
        
// Força letras maiúsculas para o campo Estado
        function validarEstado(campo) {
            let valor = campo.value.toUpperCase(); // Converte para maiúsculas
            campo.value = valor.replace(/[^A-Z]/g, "").substr(0, 2); // Remove caracteres inválidos e limita a 2
        }