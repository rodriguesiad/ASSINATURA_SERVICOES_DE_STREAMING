package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AssinaturaController;
import controller.ServicosStreamingController;
import modelo.StatusPagamento;
import modelo.usuario.Assinante;
import modelo.usuario.Usuario;

public class Principal {
	private static Scanner scan = new Scanner(System.in);
	private static Scanner scanNextLine = new Scanner(System.in);

	private static AssinaturaController assinaturaController = new AssinaturaController();
	private static ServicosStreamingController servicoStreamingController = new ServicosStreamingController();

	private static List<Usuario> usuarios = new ArrayList<>();
	
	public static void main(String[] args) {
		Principal principal = new Principal();
		Assinante assinate = new Assinante("email@email", "senha");
		
		principal.cargaDados();
		
		Principal.assinaturaController.cadastrar(assinate);
		Principal.assinaturaController.cadastrar(assinate);
		Principal.assinaturaController.imprimir();
		Principal.assinaturaController.editar(assinaturaController.getAssinaturas().get(0));
		Principal.assinaturaController.imprimir();
		Principal.assinaturaController.filtrarPorStatusPagamento(StatusPagamento.PAGO);
		Principal.assinaturaController.ordenarPorDataVencimento();
		
		
	}
	
	private void cargaDados() {
		if(servicoStreamingController.getServicos().isEmpty())
			servicoStreamingController.cargaDados();
//		if(assinaturaController.getAssinaturas().isEmpty())
//			assinaturaController.cargaDados();
	}


//	public void menu() {
//		int option;
//		System.out.println("\n MENU ");
//		do {
//
//			option = lerInteiro("\n 1 - Assinaturas\n 2 - Perfil\n(Para sair digite 0):");
//			
//			if(option == 1) {
//				
//			}
//			else if(option == 2) {}
//
//			else if(option > 2 || option < 0) {
//				System.out.println("\n Escolha inválida. Digite novamente");
//			}
//		} while (option != 0);
//
//	}
//	
//	private Integer lerInteiro(String mensagem) {
//		Integer valor = null;
//		while (valor == null) {
//			System.out.print(mensagem);
//
//			try {
//				valor = scan.nextInt();
//
//			} catch (Exception e) {
//				System.out.println("O valor deve ser do tipo inteiro. Tente novamente");
//				scanNextLine.nextLine();
//			}
//		}
//
//		return valor;
//	}

}
