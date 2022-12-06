package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.AssinaturaController;
import controller.ServicosStreamingController;
import modelo.Assinatura;
import modelo.StatusPagamento;
import modelo.usuario.Assinante;
import modelo.usuario.UsuarioAdministrador;

public class Principal {
	private static Scanner scan = new Scanner(System.in);
	private static Scanner scanNextLine = new Scanner(System.in);

	private static AssinaturaController assinaturaController = new AssinaturaController();
	private static ServicosStreamingController servicoStreamingController = new ServicosStreamingController();

	private static List<UsuarioAdministrador> usuariosAdministrador = new ArrayList<>();
	private static List<Assinante> assinantes = new ArrayList<>();

	public static void main(String[] args) {
		Principal principal = new Principal();

		principal.cargaDadosUsuarios();
		principal.cargaDados(Principal.assinantes.get(0));

		int option = 0;
		do {
			option = principal.lerInteiro(
					"\nLogar como:\n1 - Assinante \n2 - Usuário Administrador\n(Para sair digite 0)\nDigite: ");

			if (option == 1) {
				principal.loginAssinante();
			} else if (option == 2) {

			} else if (option > 2 || option < 0) {
				System.out.println("\n Escolha inválida! ");
			}
		} while (option != 0);

		System.out.println("Finalizando...Fim");
	}

	private void cargaDadosUsuarios() {
		usuariosAdministrador.add(new UsuarioAdministrador("adm@email", "Adm123"));
		assinantes.add(new Assinante("assinante@email", "Assinante123"));
	}

	private void cargaDados(Assinante assinante) {
		if (servicoStreamingController.getServicos().isEmpty())
			servicoStreamingController.cargaDados();
		if (assinaturaController.getAssinaturas().isEmpty())
			assinaturaController.cargaDados(assinante);
	}

	public Assinante validarLoginAssinante(String email, String senha) {
		for (Assinante assinante : assinantes) {
			if (assinante.getEmail().equals(email) && assinante.getSenha().equals(senha))
				return assinante;
		}
		return null;
	}

	public void loginAssinante() {
		int option = 0;
		Assinante assinante;

		do {

			System.out.println("-- LOGIN --");
			System.out.print("\nEmail: ");
			String email = scan.next();

			System.out.print("\nSenha: ");
			String senha = scan.next();

			assinante = validarLoginAssinante(email, senha);

			if (assinante != null) {
				menuAssinante(assinante);

			} else {
				option = lerInteiro(
						"Email ou senha incorretos, assinante não cadastrado.\n(Para sair digite 0 ou outro numero para fazer login novamente):");
			}

		} while (option != 0);
	}

	public void loginUsuario() {
	}

	public void menuAssinante(Assinante assinante) {
		int option = 0;
		do {
			option = lerInteiro(
					"\n1 - Cadastrar nova \n2 - Selecionar Assinatura \n3 - Imprimir minhas assinaturas\n(Para sair digite 0)\nDigite: ");

			if (option == 1)
				assinaturaController.cadastrar(assinante);
			else if (option == 2)
				menuSelecioarAssinatura(assinante);
			else if (option == 3)
				menuImprimirAssinaturas(assinante);
			else if (option > 3 || option < 0)
				System.out.println("\n Escolha inválida! ");
		} while (option != 0);
	}

	public void menuSelecioarAssinatura(Assinante assinante) {
		Assinatura assinatura = assinaturaController.selecionarAssinatura(assinante);

		int option = 0;
		do {
			assinatura.imprimir();

			option = lerInteiro(
					"\n1 - Editar \n2 - Desativar\n3- Ativar\n4 - Realizar Pagamento\n(Para sair digite 0)\nDigite: ");

			if (option == 1)
				assinaturaController.editar(assinatura);
			else if (option == 2)
				assinaturaController.desativar(assinatura);
			else if (option == 3)
				assinaturaController.ativar(assinatura);
			else if (option == 4)
				assinatura.realizarPagamento();

			else if (option > 4 || option < 0)
				System.out.println("\n Escolha inválida! ");

		} while (option != 0);

	}

	public void menuImprimirAssinaturas(Assinante assinante) {
		assinaturaController.imprimir(assinante);

		int option = 0;
		do {
			option = lerInteiro(
					"\n1 - Listar por dada de validade \n2 - Filtrar por status de pagamento\n(Para sair digite 0)\nDigite: ");

			if (option == 1) {
				assinaturaController.ordenarPorDataVencimento(assinante);
			} else if (option == 2) {

				assinaturaController.filtrarPorStatusPagamento(selecionarStatusPagamento(), assinante);

			} else if (option > 2 || option < 0) {
				System.out.println("\n Escolha inválida! ");
			}
		} while (option != 0);
	}

	public StatusPagamento selecionarStatusPagamento() {

		int option = 0;
		do {
			option = lerInteiro("\n1 - Pago \n2 - Pendente\n(Para sair digite 0)\nDigite: ");

			if (option == 1) {
				return StatusPagamento.PAGO;
			} else if (option == 2) {
				return StatusPagamento.PENDENTE;
			} else if (option > 2 || option < 0) {
				System.out.println("\n Escolha inválida! ");
			}
		} while (option != 0);

		return null;

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

}
