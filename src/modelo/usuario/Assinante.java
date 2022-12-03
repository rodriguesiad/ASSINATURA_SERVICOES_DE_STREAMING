package modelo.usuario;

import java.util.ArrayList;
import java.util.List;

import modelo.assinatura.Assinatura;

public class Assinante implements Perfil {

	private String email;
	private String senha;

	// private TipoPerfil tipo;
	
	private List<Assinatura> assinatura = new ArrayList<>();

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getSenha() {
		return this.senha;
	}

	@Override
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Assinatura> getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(List<Assinatura> assinatura) {
		this.assinatura = assinatura;
	}

}
