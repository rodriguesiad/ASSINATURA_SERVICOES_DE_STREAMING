package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modelo.usuario.Assinante;

public class Assinatura implements Imprimir {

	private Integer id;
	private LocalDate dataCadastro;
	private LocalDate dataVencimento;
	private StatusPagamento statusPagamento;
	private Double valor = 0.0;
	private boolean ativo;
	private Assinante assinante;
	private List<ServicoStreaming> servicosStreaming = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public Assinatura(Integer id, Assinante assinante, List<ServicoStreaming> servicosStreaming) {
		super();
		this.id = id;
		this.assinante = assinante;
		this.dataCadastro = LocalDate.now();
		this.dataVencimento = this.dataCadastro.plusDays(30);
		this.statusPagamento = StatusPagamento.PAGO;
		this.ativo = true;
		this.servicosStreaming = servicosStreaming;
		setValor();
	}

	public Assinatura(Integer id, Assinante assinante, LocalDate dataCadastro,
			List<ServicoStreaming> servicosStreaming) {
		super();
		this.id = id;
		this.assinante = assinante;
		this.dataCadastro = dataCadastro;
		this.dataVencimento = this.dataCadastro.plusDays(30);

		if (dataVencimento.isBefore(LocalDate.now()))
			this.statusPagamento = StatusPagamento.PENDENTE;
		else
			this.statusPagamento = StatusPagamento.PAGO;

		this.ativo = true;
		this.servicosStreaming = servicosStreaming;
		setValor();
	}

	public Assinatura() {
	};

	public List<ServicoStreaming> getServicosStreaming() {
		return servicosStreaming;
	}

	public void setServicosStreaming(List<ServicoStreaming> servicosStreaming) {
		this.servicosStreaming = servicosStreaming;
	}

	public Assinante getAssinante() {
		return assinante;
	}

	public void setAssinante(Assinante assinante) {
		this.assinante = assinante;
	}

	public void cancelarAssinatura() {
		this.setAtivo(false);
	}
	
	public void ativarAssinatura() {
		this.setAtivo(true);
	}

	public void realizarPagamento() {
		this.setStatusPagamento(StatusPagamento.PAGO);
	}

	public void imprimir() {
		System.out.println(this);
		imprimirServicos();
	}

	public void imprimirServicos() {
		System.out.println("Serviços de Streaming: ");
		if (this.statusPagamento.equals(StatusPagamento.PENDENTE))
			System.out.println("Serviços de Stream: Bloqueados");
		else
			this.servicosStreaming.forEach(servicos -> System.out.println(servicos));
	}

	@Override
	public int hashCode() {
		return Objects.hash(assinante, ativo, dataCadastro, dataVencimento, id, servicosStreaming, statusPagamento,
				valor);
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
		return Objects.equals(assinante, other.assinante) && ativo == other.ativo
				&& Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataVencimento, other.dataVencimento) && Objects.equals(id, other.id)
				&& Objects.equals(servicosStreaming, other.servicosStreaming)
				&& statusPagamento == other.statusPagamento && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "\nAssinatura\nID: " + id + "\nData de Cadastro: "
				+ dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nData de vencimento:"
				+ dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nStatus do pagamento: "
				+ statusPagamento + "\nValor da mensalidade: " + valor + "\nConta ativa: " + ativo + "\nAssinante: "
				+ assinante.getEmail();
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
