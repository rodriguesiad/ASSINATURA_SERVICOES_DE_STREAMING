package modelo;

import java.util.Objects;

public class ServicoStreaming implements Imprimir {

	private Integer id;
	private String nome;
	private Double valor;
	private Double precoUnitario;
	private String descricao;
	private Categoria categoria;

	public ServicoStreaming(Integer id, String nome, Double valor, Double precoUnitario, String descricao,
			Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.precoUnitario = precoUnitario;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void imprimir() {
		System.out.println(this);

	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, descricao, id, precoUnitario, valor);
	}

	@Override
	public String toString() {
		return "\n" + nome + "\nId: " + id + "\nValor: " + valor + "\nPreço Unitário: " + precoUnitario
				+ "\nDescrição: " + descricao + "\nCategoria: " + categoria.getLabel();
	}

}
