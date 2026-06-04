package vagao;

import modelo.Carga;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de Vagao para transporte de cargas.
 * Responsável por gerenciar embarque, desembarque e busca de cargas,
 * validando peso máximo permitido além da capacidade de itens.
 */
public class VagaoCarga extends Vagao {
	private List<Carga> cargas;
	private double pesoMaximo;
	private double pesoAtual;

	// Construtor: inicializa vagão de carga com peso máximo permitido
	public VagaoCarga(int numero, int capacidadeMaxima, double pesoMaximo) {
		super(numero, capacidadeMaxima);
		this.cargas = new ArrayList<>();
		this.pesoMaximo = pesoMaximo;
		this.pesoAtual = 0;
	}

	// Embarcar: tenta adicionar uma carga, validando tipo, capacidade e peso
	@Override
	public boolean embarcar(Object item) {
		if (!validarItem(item)) return false;

		Carga carga = (Carga) item;
		if (validarCapacidade()) return false;
		if (!validarPeso(carga)) return false;

		cargas.add(carga);
		pesoAtual += carga.getPeso();
		System.out.println("Carga " + carga.getDescricao() + " (" + carga.getPeso() + "kg) embarcada no vagão #" + getNumero());
		return true;
	}

	// Desembarcar: tenta remover uma carga
	@Override
	public boolean desembarcar(Object item) {
		if (!validarItem(item)) return false;

		Carga carga = (Carga) item;
		boolean removido = cargas.remove(carga);
		if (removido) {
			pesoAtual -= carga.getPeso();
			System.out.println("Carga " + carga.getDescricao() + " desembarcada do vagão #" + getNumero());
		} else {
			System.out.println("Carga não encontrada no vagão #" + getNumero());
		}
		return removido;
	}

	// Buscar: procura uma carga pelo código
	@Override
	public Object buscar(String identificador) {
		for (Carga c : cargas) {
			if (c.getCodigo().equalsIgnoreCase(identificador)) {
				return c;
			}
		}
		System.out.println("Carga com código " + identificador + " não encontrada.");
		return null;
	}

	// Listar: exibe todas as cargas embarcadas
	@Override
	public void listarConteudo() {
		System.out.println("--- Vagão de Carga #" + getNumero() + " ---");
		if (cargas.isEmpty()) {
			System.out.println("Nenhuma carga embarcada.");
			return;
		}
		for (Carga c : cargas) {
			System.out.println("  - [" + c.getCodigo() + "] " + c.getDescricao() + " | Peso: " + c.getPeso() + "kg");
		}
		System.out.println("Peso: " + pesoAtual + "kg / " + pesoMaximo + "kg");
		System.out.println("Ocupação: " + cargas.size() + "/" + getCapacidadeMaxima());
	}

	@Override
	public void exibirStatus() {
		super.exibirStatus();
		System.out.println("Tipo: Carga | Peso: " + pesoAtual + "/" + pesoMaximo + "kg | Ocupação: " + cargas.size() + "/" + getCapacidadeMaxima());
	}

	// Valida se item é uma carga
	private boolean validarItem(Object item) {
		if (!(item instanceof Carga)) {
			System.out.println("Item inválido. Este vagão transporta apenas cargas.");
			return false;
		}
		return true;
	}

	// Valida se o vagão atingiu capacidade máxima de itens
	private boolean validarCapacidade() {
		if (cargas.size() >= getCapacidadeMaxima()) {
			System.out.println("Vagão lotado. Capacidade máxima: " + getCapacidadeMaxima() + " itens.");
			return true;
		}
		return false;
	}

	// Valida se a carga excederia o peso máximo permitido
	private boolean validarPeso(Carga carga) {
		if (pesoAtual + carga.getPeso() > pesoMaximo) {
			System.out.println("Peso excedido. Disponível: " + (pesoMaximo - pesoAtual) + "kg | Carga: " + carga.getPeso() + "kg");
			return false;
		}
		return true;
	}
}