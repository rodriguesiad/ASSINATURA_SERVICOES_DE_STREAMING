package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import modelo.Assinatura;
import modelo.ServicoStreaming;
import modelo.StatusPagamento;
import modelo.usuario.Assinante;

public class AssinaturaController {
	private static Scanner scan = new Scanner(System.in);
	private static Scanner scanNextLine = new Scanner(System.in);
	private static Integer id = 1;

	private static ServicosStreamingController servicoController = new ServicosStreamingController();

	private List<Assinatura> assinaturas = new ArrayList<>();

	public void cargaDados(Assinante assinante) {
		assinaturas.add(new Assinatura(id, assinante, LocalDate.of(2022, 11, 01), new ArrayList<ServicoStreaming>(
				Arrays.asList(servicoController.getServicos().get(0), servicoController.getServicos().get(1)))));
		id += 1;
		assinaturas.add(new Assinatura(id, assinante, LocalDate.now(), new ArrayList<ServicoStreaming>(
				Arrays.asList(servicoController.getServicos().get(2), servicoController.getServicos().get(3)))));
		id += 1;
	}

	public Assinatura selecionarAssinatura() {
		imprimir();
		Assinatura assinatura = null;
		int indice;
		
		while (assinatura == null) {

			indice = lerInteiro("\nDigite o id da assinatura desejada: ");
			try {
				assinatura = assinaturas.get(indice - 1);
			} catch (Exception e) {
				System.out.println("Assinatura não cadastrada");
			}
		}
		return assinatura;
	}

	public void cadastrar(Assinante assinante) {

		System.out.println("\n - CADASTRAR ASSINATURA - ");

		Assinatura assinatura = new Assinatura(id, assinante, adicionarServicos());
		assinaturas.add(assinatura);
		id += 1;

		// Adicionando assinatura no perfil do usuario
		assinante.getAssinaturas().add(assinatura);

		System.out.println("\n Assinatura Cadastrada ");

	}

	public void editar(Assinatura assinatura) {
		int option = 0;
		do {
			option = lerInteiro(
					"\n1 - Adicionar Serviço de Streaming\n2 - Deletar Serviço de Streaming(Para sair digite 0)\nDigite: ");

			if (option == 1) {
				assinatura.getServicosStreaming().addAll(adicionarServicos());
				assinatura.setValor();
			} else if (option == 2) {
				assinatura.imprimirServicos();
				assinatura.setServicosStreaming(removerServicos(assinatura.getServicosStreaming()));
				assinatura.setValor();
			} else if (option > 2 || option < 0) {
				System.out.println("\n Escolha inválida! ");
			}
		} while (option != 0);

	}

	private List<ServicoStreaming> adicionarServicos() {
		int indice;
		List<ServicoStreaming> servicos = new ArrayList<>();

		servicoController.imprimir();

		do {

			indice = lerInteiro("\nDigite o numero do serviço de streaming desejado:\n(Para sair digite 0)\nDigite: ");
			if (indice != 0) {
				try {
					if (servicos.contains(servicoController.getServicos().get(indice - 1))) {
						System.out.println("\nServiço de Stream já inserido");
					} else {
						servicos.add(servicoController.getServicos().get(indice - 1));
					}
				} catch (Exception e) {
					System.out.println("Serviço de Streaming não cadastrado");
				}
			}

		} while (indice != 0);
		return servicos;
	}

	private List<ServicoStreaming> removerServicos(List<ServicoStreaming> servicosAssinatura) {
		int indice;
		do {

			indice = lerInteiro("\nDigite o numero do serviço de streaming desejado:\n(Para sair digite 0)\nDigite: ");
			if (indice != 0) {
				try {
					servicosAssinatura.remove(indice - 1);
				} catch (Exception e) {
					System.out.println("Serviço de Streaming não cadastrado");
				}
			}

		} while (indice != 0);

		List<ServicoStreaming> servicos = servicosAssinatura;
		return servicos;
	}

	public void desativar(Assinatura assinatura) {
		assinatura.cancelarAssinatura();
	}
	
	public void ativar(Assinatura assinatura) {
		assinatura.ativarAssinatura();
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
		assinaturas.stream().sorted((assinatura1, assinatura2) -> assinatura1.getDataVencimento()
				.compareTo(assinatura2.getDataVencimento())).forEach(assinatura -> assinatura.imprimir());
		;
	}

	public List<Assinatura> getAssinaturas() {
		return assinaturas;
	}

}
