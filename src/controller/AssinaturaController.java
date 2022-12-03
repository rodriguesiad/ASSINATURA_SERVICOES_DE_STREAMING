package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Assinante;
import modelo.Assinatura;
import modelo.ServicoStreaming;
import modelo.StatusPagamento;

public class AssinaturaController {
	private static Scanner scan = new Scanner(System.in);
	private static Scanner scanNextLine = new Scanner(System.in);
	private static Integer id = 0;

	private static List<Assinatura> assinaturas = new ArrayList<>();

	public void cadastrar(Assinante assinante, List<ServicoStreaming> servicosSistema) {

		// id(assinaturas.isEmpoty())
		// cargaDados();

		System.out.println("\n - CADASTRAR ASSINATURA - ");

		Assinatura assinatura = new Assinatura(id, assinante, adicionarServicos(servicosSistema));
		assinaturas.add(assinatura);
		id += 1;

		System.out.println("\n Assinatura Cadastrada ");

	}

	private void cargaDados() {
	};

	private List<ServicoStreaming> adicionarServicos(List<ServicoStreaming> servicosSistema) {
		Boolean isTrue = true;
		List<ServicoStreaming> servicos = new ArrayList<>();

		while (isTrue) {

			int indice = lerInteiro("Digite o numero do serviço de streaming desejado:\n(Para sair digite 0)");

			if (indice != 0) {
				try {
					servicos.add(servicosSistema.get(indice - 1));
				} catch (Exception e) {
					System.out.println("Serviço de Streaming não cadastrado");
				}

			} else {
				isTrue = false;
			}
		}
		return servicos;
	}

	private List<ServicoStreaming> removerServicos(List<ServicoStreaming> servicosAssinatura) {
		Boolean isTrue = true;

		while (isTrue) {

			int indice = lerInteiro("Digite o numero do serviço de streaming desejado:\n(Para sair digite 0)");

			if (indice != 0) {
				try {
					servicosAssinatura.remove(indice - 1);
				} catch (Exception e) {
					System.out.println("Serviço de Streaming não cadastrado");
				}

			} else {
				isTrue = false;
			}
		}

		List<ServicoStreaming> servicos = servicosAssinatura;
		return servicos;
	}

	public void editar(Assinatura assinatura, List<ServicoStreaming> servicosSistema) {

		int option = lerInteiro(
				"1 - Adicionar Serviço de Streaming\n2 - Deletar Serviço de Streaming(Para sair digite 0)");

		if (option == 1)
			assinatura.getServicosStreaming().addAll(adicionarServicos(servicosSistema));
		else if (option == 2)
			assinatura.setServicosStreaming(removerServicos(assinatura.getServicosStreaming()));

	}

	public void desativar(Assinatura assinatura) {
		assinatura.desativarAssinatura();
	}

	public void excluir(Assinatura assinatura) {
		assinaturas.remove(assinatura);
	}

	private Integer lerInteiro(String mensagem) {
		Integer valor = null;
		while (valor == null) {
			System.out.print(mensagem);

			try {
				valor = scan.nextInt();

			} catch (Exception e) {
				System.out.println("O valor deve ser do tipo inteiro. Tente novamente");
				scanNextLine.nextLine();
			}
		}

		return valor;
	}

	public void imprimir() {
		assinaturas.forEach(assinatura -> assinatura.imprimir());
	}

	public void filtrarPorStatusPagamento(StatusPagamento statusPagamento) {
		assinaturas.stream().filter(assinatura -> assinatura.getStatusPagamento().equals(statusPagamento))
				.forEach(assinatura -> assinatura.imprimir());
	}

	public void ordenarPorDataVencimento() {
		assinaturas.stream().sorted((assinatura1, assinatura2) -> assinatura1.getDataVencimento().compareTo(assinatura2.getDataVencimento()))
		.forEach(assinatura -> assinatura.imprimir());;
	}

}
