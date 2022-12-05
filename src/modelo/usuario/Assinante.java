package modelo.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modelo.Assinatura;

public class Assinante extends Usuario {

	private List<Assinatura> assinaturas = new ArrayList<>();

	public List<Assinatura> getAssinaturas() {
		return assinaturas;
	}

	public void setAssinaturas(List<Assinatura> assinatura) {
		this.assinaturas = assinatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assinaturas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assinante other = (Assinante) obj;
		return Objects.equals(assinaturas, other.assinaturas);
	}

	@Override
	public String toString() {
		return "Assinante [getAssinaturas()=" + getAssinaturas() + ", hashCode()=" + hashCode() + "]";
	}

	public Assinante(String email, String senha) {
		super(email, senha);
	}
	
	

}
