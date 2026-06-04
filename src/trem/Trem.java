package trem;

import locomotiva.Locomotiva;
import vagao.Vagao;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a composição do trem de ferro, agregando uma locomotiva e vagões.
 * Responsável por gerenciar adição/remoção de vagões e delegar operações de
 * movimento para a locomotiva.
 */
public class Trem {
	private Locomotiva locomotiva;
	private List<Vagao> vagoes;

	// Construtor: inicializa trem com uma locomotiva
	public Trem(Locomotiva locomotiva) {
		this.locomotiva = locomotiva;
		this.vagoes = new ArrayList<>();
	}

	// Adicionar vagão: valida limite de capacidade da locomotiva
	public boolean adicionarVagao(Vagao vagao) {
		if (validarCapacidade()) return false;

		vagoes.add(vagao);
		System.out.println("Vagão #" + vagao.getNumero() + " adicionado à composição.");
		return true;
	}

	// Remover vagão: remove vagão por índice
	public boolean removerVagao(int indice) {
		if (!validarIndice(indice)) return false;

		Vagao removido = vagoes.remove(indice);
		System.out.println("Vagão #" + removido.getNumero() + " removido da composição.");
		return true;
	}

	// Obter vagão: retorna vagão por índice
	public Vagao getVagao(int indice) {
		if (!validarIndice(indice)) return null;
		return vagoes.get(indice);
	}

	// Listar vagões: exibe status e conteúdo de todos os vagões
	public void listarVagoes() {
		if (vagoes.isEmpty()) {
			System.out.println("Nenhum vagão na composição.");
			return;
		}
		System.out.println("=== Composição do Trem ===");
		for (Vagao vagao : vagoes) {
			vagao.exibirStatus();
			vagao.listarConteudo();
			System.out.println("-------------------------");
		}
	}

	// Exibir status: mostra dados da locomotiva e quantidade de vagões
	public void exibirStatus() {
		System.out.println("==============================");
		locomotiva.showStatus();
		System.out.println("Vagões na composição: " + vagoes.size() + "/" + locomotiva.getMaxVagoes());
		System.out.println("==============================");
	}

	// Acelerar: delega comando para a locomotiva
	public void acelerar(double incremento) {
		locomotiva.speed(incremento);
	}

	// Desacelerar: delega comando para a locomotiva
	public void desacelerar(double decremento) {
		locomotiva.slow(decremento);
	}

	// Parar: delega comando para a locomotiva
	public void parar() {
		locomotiva.stop();
	}

	// Valida se há espaço para mais um vagão
	private boolean validarCapacidade() {
		if (vagoes.size() >= locomotiva.getMaxVagoes()) {
			System.out.println("Limite de vagões atingido. Máximo: " + locomotiva.getMaxVagoes());
			return true;
		}
		return false;
	}

	// Valida se o índice é válido
	private boolean validarIndice(int indice) {
		if (indice < 0 || indice >= vagoes.size()) {
			System.out.println("Índice inválido.");
			return false;
		}
		return true;
	}
}