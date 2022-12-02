package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Assinatura {

	private Integer id;
	private LocalDate dataCadastro;
	private LocalDate dataVencimento;
	private StatusPagamento statusPagamento;
	private Double valor;
	private boolean ativo;
	private AssinanteAdministrador assinanteAdministrador;
	private List<ServicoStreaming> servicosStreaming = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public Assinatura(Integer id, AssinanteAdministrador assinanteAdministrador,
			List<ServicoStreaming> servicosStreaming) {
		super();
		this.id = id;
		this.assinanteAdministrador = assinanteAdministrador;
		this.dataCadastro = LocalDate.now();
		this.dataVencimento = LocalDate.now().plusDays(30);
		this.statusPagamento = StatusPagamento.PAGO;
		setValor();
		this.ativo = true;
		this.servicosStreaming = servicosStreaming;
	}

	public List<ServicoStreaming> getServicosStreaming() {
		return servicosStreaming;
	}

	public void setServicosStreaming(List<ServicoStreaming> servicosStreaming) {
		this.servicosStreaming = servicosStreaming;
	}

	public AssinanteAdministrador getAssinanteAdministrador() {
		return assinanteAdministrador;
	}

	public void setAssinanteAdministrador(AssinanteAdministrador assinanteAdministrador) {
		this.assinanteAdministrador = assinanteAdministrador;
	}

	public void desativarAssinatura() {
		this.setAtivo(false);
	}

	public void realizarPagamento() {
		this.setStatusPagamento(StatusPagamento.PAGO);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assinanteAdministrador, ativo, dataCadastro, dataVencimento, id, servicosStreaming,
				statusPagamento, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assinatura other = (Assinatura) obj;
		return Objects.equals(assinanteAdministrador, other.assinanteAdministrador) && ativo == other.ativo
				&& Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataVencimento, other.dataVencimento) && Objects.equals(id, other.id)
				&& Objects.equals(servicosStreaming, other.servicosStreaming)
				&& statusPagamento == other.statusPagamento && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "Assinatura [id=" + id + ", dataCadastro=" + dataCadastro + ", dataVencimento=" + dataVencimento
				+ ", statusPagamento=" + statusPagamento + ", valor=" + valor + ", ativo=" + ativo
				+ ", assinanteAdministrador=" + assinanteAdministrador + ", servicosStreaming=" + servicosStreaming
				+ "]";
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor() {
		if (!this.servicosStreaming.isEmpty()) {
			this.servicosStreaming.forEach((servico) -> {
				this.valor += servico.getValor();
			});
		}
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
