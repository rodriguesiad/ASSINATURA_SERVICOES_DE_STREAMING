package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Assinatura;
import modelo.ServicoStreaming;

public class ServicosStreamingController {
	private static Scanner scan = new Scanner(System.in);
	private static Scanner scanNextLine = new Scanner(System.in);
	private static Integer id = 0;

	private static AssinaturaController assinaturaController = new AssinaturaController();
	
	private static List<ServicoStreaming> servicos = new ArrayList<>();

	public void menu() {
		int option;
		System.out.println("\n MENU SERVICOS");
		do {

			option = lerInteiro("\n 1 - Cadastrar(Para sair digite 0):");

			if (option == 1) {
				cadastrar();
			} else if (option > 2 || option < 0) {
				System.out.println("\n Escolha inválida. Digite novamente");
			}
		} while (option != 0);
	}
	
	public void cargaDados() {
		getServicos().add(new ServicoStreaming(12.50));
		getServicos().add(new ServicoStreaming(24.50));
		getServicos().add(new ServicoStreaming(15.50));
		getServicos().add(new ServicoStreaming(13.00));
	}

	public void cadastrar() {
		System.out.println("\n - CADASTRAR ASSINATURA - ");

		Double valor = lerDouble("\nDigite o valor: ");
		ServicoStreaming servico = new ServicoStreaming(valor);
		getServicos().add(servico);
		id += 1;

		System.out.println("\n Servico Cadastrado ");
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
		
		System.out.print("Valor: ");
		String valor = scanNextLine.nextLine();
		
		if(valor != "") {
			try {
				Double valorDouble = Double.valueOf(valor);
				servico.setValor(valorDouble);
			}catch (Exception e) {
				System.out.println("O dado inserido não é numérico");
			}
			
		}
		
		//ServicoStreaming servicoEditar
	}
	
	public void excluir(ServicoStreaming servico) {
		servicos.remove(servico);
		
		for (Assinatura assinatura : assinaturaController.getAssinaturas()) {
			if(assinatura.getServicosStreaming().contains(servico))
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
				scanNextLine.nextLine();
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
				System.out.println("O valor deve ser do tipo inteiro. Tente novamente");
				scanNextLine.nextLine();
			}
		}
		return valor;
	}
	
	public void imprimir() {
		getServicos().stream().sorted((s1, s2) -> s1.getValor().compareTo(s2.getValor())).forEach(servico -> servico.imprimir());
	}

	public List<ServicoStreaming> getServicos() {
		return servicos;
	}

}
