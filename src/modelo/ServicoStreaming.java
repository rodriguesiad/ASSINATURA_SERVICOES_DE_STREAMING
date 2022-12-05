package modelo;

public class ServicoStreaming implements Imprimir {

	private Double valor;

	public ServicoStreaming(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public void imprimir() {
		System.out.println(this);
		
	}

	@Override
	public String toString() {
		return "\nvalor: " + valor;
	}
	
}
