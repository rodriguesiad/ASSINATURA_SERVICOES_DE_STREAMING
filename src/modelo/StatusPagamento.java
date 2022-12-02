package modelo;

public enum StatusPagamento {
	PENDENTE(0), PAGO(1);
	
	private Integer indice; 
	
	private StatusPagamento(Integer indice) {
		this.indice = indice;
	}

	public StatusPagamento valueOf(Integer indice) {
		for(StatusPagamento status : StatusPagamento.values()) {
			if(status.indice == indice) {
				return status;
			}
		}
		return null;
	}
}
