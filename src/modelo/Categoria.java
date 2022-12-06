package modelo;

public enum Categoria {
	MUSICA(1, "Música"), FILME_SERIE(2, "Filme/Serie"), LIVRO(3, "Livro");

	private String label;
	private Integer indice;

	public String getLabel() {
		return label;
	}

	public Integer getIndice() {
		return indice;
	}

	private Categoria(Integer indice, String label) {
		this.label = label;
		this.indice = indice;
	}

	public static Categoria valueOf(Integer indice) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.indice == indice) {
				return categoria;
			}
		}
		return null;
	}

}
