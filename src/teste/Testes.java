package teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import aplicacao.Principal;
import modelo.usuario.Assinante;
import modelo.usuario.Funcionario;

class Testes {

	@Test
	void assinanteValido() {
		Principal principal = new Principal();
		principal.cargaDadosUsuarios();
		Assinante assinante = principal.validarLoginAssinante("assinante@email", "Assinante123");
		assertNotNull(assinante);
	}

	@Test
	void assinanteInvalido() {
		Principal principal = new Principal();
		principal.cargaDadosUsuarios();
		Assinante assinante = principal.validarLoginAssinante("assinante@email", "123");
		assertNull(assinante);
	}
	
	@Test
	void funcionarioValido() {
		Principal principal = new Principal();
		principal.cargaDadosUsuarios();
		Funcionario funcionario = principal.validarLoginFuncionario("adm@email", "Adm123");
		assertNotNull(funcionario);
	}
	
	@Test
	void funcionarioInvalido() {
		Principal principal = new Principal();
		principal.cargaDadosUsuarios();
		Funcionario funcionario = principal.validarLoginFuncionario("funcionario@email", "Adm123");
		assertNull(funcionario);
	}

}
