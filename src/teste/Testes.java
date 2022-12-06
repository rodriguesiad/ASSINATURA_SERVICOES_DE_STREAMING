package teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import aplicacao.Principal;
import modelo.usuario.Assinante;

class Testes {

	@Test
	void AssinanteValido() {
		Principal principal = new Principal();
		principal.cargaDadosUsuarios();
		Assinante assinante = principal.validarLoginAssinante("assinante@email", "Assinante123");
		assertNotNull(assinante);
	}
	
	@Test
	void AssinanteInvalido() {
		Principal principal = new Principal();
		principal.cargaDadosUsuarios();
		Assinante assinante = principal.validarLoginAssinante("assinante@email", "123");
		assertNull(assinante);
	}

}
