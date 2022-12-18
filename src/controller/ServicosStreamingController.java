package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Assinatura;
import modelo.Categoria;
import modelo.ServicoStreaming;

public class ServicosStreamingController {
	private static Scanner scan = new Scanner(System.in);
	private static Scanner scanNextLine = new Scanner(System.in);
	private static Integer id = 1;

	private static AssinaturaController assinaturaController = new AssinaturaController();

	private static List<ServicoStreaming> servicos = new ArrayList<>();

	public void cargaDados() {
		getServicos().add(new ServicoStreaming(id, "Netfliz", 12.50, "Servico Streaming", Categoria.FILME_SERIE));
		id += 1;
		getServicos().add(new ServicoStreaming(id, "Prime Video", 15.50, "Servico Streaming", Categoria.FILME_SERIE));
		id += 1;
		getServicos().add(new ServicoStreaming(id, "Youtube Music", 12.50, "Servico Streaming", Categoria.MUSICA));
		id += 1;
	}

	public void cadastrar() {
		System.out.println("\n - CADASTRAR SERVICO DE STREAMING - ");

		System.out.print("Nome: ");
		String nome = scanNextLine.nextLine();

		Double preco = lerDouble("Preço Unitário: ");

		System.out.print("Descrição: ");
		String descricao = scanNextLine.nextLine();

		ServicoStreaming servico = new ServicoStreaming(id, nome, preco, descricao, selecionarCategoria());
		id += 1;
		servicos.add(servico);

		System.out.println("Serviço cadastrado");
	}

	public ServicoStreaming selecionarServico() {
		imprimir();
		ServicoStreaming servico = null;
		int indice;

		while (servico == null) {

			indice = lerInteiro("\nDigite o id do serviço de streaming desejado: ");
			try {
				servico = servicos.get(indice - 1);
			} catch (Exception e) {
				System.out.println("Serviço de Streaming não cadastrado");
			}
		}
		return servico;
	}

	public void editar(ServicoStreaming servico) {

		System.out.println("Escreva apenas nos campos que deseja editar, caso contrário, apenas dê enter");

		System.out.print("Nome: ");
		String nome = scanNextLine.nextLine();

		if (nome != "")
			servico.setNome(nome);

		System.out.print("Preço Unitário: ");
		String preco = scanNextLine.nextLine();

		if (preco != "") {
			try {
				Double precoDouble = Double.valueOf(preco);
				servico.setPrecoUnitario(precoDouble);
			} catch (Exception e) {
				System.out.println("O dado inserido não é numérico");
			}
		}

		System.out.print("Descrição: ");
		String descricao = scanNextLine.nextLine();

		if (descricao != "")
			servico.setDescricao(descricao);

		System.out.print("Categoria(Caso queira editar, digite qualquer caractere): ");
		String categoria = scanNextLine.nextLine();

		if (categoria != "")
			servico.setCategoria(selecionarCategoria());
	}

	public Categoria selecionarCategoria() {

		int indice = 0;
		Categoria categoria = null;
		for (Categoria categoriaF : Categoria.values()) {
			System.out.println("\n" + categoriaF.getIndice() + " - " + categoriaF.getLabel());
		}
		do {
			indice = lerInteiro("\nDigite o numero da categoria desejada: ");

			categoria = Categoria.valueOf(indice);
			if (categoria != null)
				return categoria;
			else
				System.out.println("Categoria não cadastrada");

		} while (categoria == null);

		return null;
	}

	public void excluir(ServicoStreaming servico) {
		servicos.remove(servico);

		for (Assinatura assinatura : assinaturaController.getAssinaturas()) {
			if (assinatura.getServicosStreaming().contains(servico))
				assinatura.getServicosStreaming().remove(servico);
			assinatura.setValor();
		}

	}

	private Integer lerInteiro(String mensagem) {
		Integer valor = null;
		while (valor == null) {
			System.out.print(mensagem);

			try {
				valor = scan.nextInt();

			} catch (Exception e) {
				System.out.println("O valor deve ser do tipo inteiro. Tente novamente");
				scan.nextLine();
			}
		}

		return valor;
	}

	private Double lerDouble(String mensagem) {
		Double valor = null;
		while (valor == null) {
			System.out.print(mensagem);

			try {
				valor = scan.nextDouble();

			} catch (Exception e) {
				System.out.println("O valor deve ser do tipo double. Tente novamente");
				scan.nextLine();
			}
		}
		return valor;
	}

	public void imprimir() {
		getServicos().stream().sorted((s1, s2) -> s1.getNome().compareTo(s2.getNome()))
				.forEach(servico -> servico.imprimir());
	}

	public void ordenarPorPreco() {
		getServicos().stream().sorted((s1, s2) -> s1.getPrecoUnitario().compareTo(s2.getPrecoUnitario()))
				.forEach(servico -> servico.imprimir());
	}

	public void filtrarPorCategoria(Categoria categoria) {
		getServicos().stream().sorted((s1, s2) -> s1.getNome().compareTo(s2.getNome()))
				.filter(servico -> servico.getCategoria().equals(categoria)).forEach(servico -> servico.imprimir());
	}

	public List<ServicoStreaming> getServicos() {
		return servicos;
	}

}
