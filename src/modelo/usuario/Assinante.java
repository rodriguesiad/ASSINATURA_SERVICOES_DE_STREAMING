package modelo.usuario;

public class Assinante implements Perfil {

	private String email;
	private String senha;

	// private TipoPerfil tipo;

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

}
