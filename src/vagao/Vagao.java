package vagao;

/**
 * Classe abstrata que define o contrato para todos os tipos de vagão.
 * Responsável por gerenciar número, capacidade máxima e operações polimórficas
 * (embarcar, desembarcar, buscar, listar conteúdo).
 */
public abstract class Vagao {
	private int numero;
	private int capacidadeMaxima;

	// Construtor: inicializa vagão com número e capacidade máxima
	public Vagao(int numero, int capacidadeMaxima) {
		this.numero = numero;
		this.capacidadeMaxima = capacidadeMaxima;
	}

	// Embarcar item: contrato obrigatório para todas as subclasses
	public abstract boolean embarcar(Object item);

	// Desembarcar item: contrato obrigatório para todas as subclasses
	public abstract boolean desembarcar(Object item);

	// Buscar item por identificador: contrato obrigatório para todas as subclasses
	public abstract Object buscar(String identificador);

	// Listar conteúdo: contrato obrigatório para todas as subclasses
	public abstract void listarConteudo();

	// Exibe status do vagão
	public void exibirStatus() {
		System.out.println("Vagão #" + numero + " | Capacidade: " + capacidadeMaxima);
	}

	// Obtém o número do vagão
	public int getNumero() { return numero; }

	// Obtém a capacidade máxima do vagão
	public int getCapacidadeMaxima() { return capacidadeMaxima; }
}