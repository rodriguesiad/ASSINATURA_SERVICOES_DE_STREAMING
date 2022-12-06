package modelo;

public enum Categoria {
	MUSICA("Música"), FILME_SERIE("Filme/Serie"), LIVRO("Livro");

	private String label;

	public String getLabel() {
		return label;
	}

	private Categoria(String label) {
		this.label = label;
	}
}
