package modelo.assinatura;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modelo.servicoStreaming.ServicoStreaming;
import modelo.usuario.Assinante;

public class Assinatura {

	private Integer id;
	private LocalDate dataCadastro;
	private LocalDate dataVencimento;
	private StatusPagamento statusPagamento;
	private Double valor;
	private boolean ativo;
	private Assinante assinanteAdministrador;
	private List<ServicoStreaming> servicosStreaming = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public Assinatura(Integer id, Assinante assinanteAdministrador, List<ServicoStreaming> servicosStreaming) {
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

	public Assinante getAssinanteAdministrador() {
		return assinanteAdministrador;
	}

	public void setAssinanteAdministrador(Assinante assinanteAdministrador) {
		this.assinanteAdministrador = assinanteAdministrador;
	}

	public void desativarAssinatura() {
		this.setAtivo(false);
	}

	public void realizarPagamento() {
		this.setStatusPagamento(StatusPagamento.PAGO);
	}

	public void imprimir() {
		System.out.println(this);
		imprimirServicos();
	}

	public void imprimirServicos() {
		System.out.println("/n Serviços de Streaming: ");
		this.servicosStreaming.forEach(servicos -> System.out.println(servicos));
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
		return "Assinatura\nID: " + id + "\nData de Cadastro: "
				+ dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nData de vencimento:"
				+ dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nStatus do pagamento: "
				+ statusPagamento + "\nValor da mensalidade: " + valor + "\nConta ativa: " + ativo + "\nAssinante: "
				+ assinanteAdministrador;
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
