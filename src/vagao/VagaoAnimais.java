package vagao;

import modelo.Animal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de Vagao para transporte de animais.
 * Responsável por gerenciar embarque, desembarque e busca de animais,
 * validando espécie permitida para este vagão.
 */
public class VagaoAnimais extends Vagao {
	private List<Animal> animais;
	private String especiePermitida;

	// Construtor: inicializa vagão de animais com espécie permitida
	public VagaoAnimais(int numero, int capacidadeMaxima, String especiePermitida) {
		super(numero, capacidadeMaxima);
		this.animais = new ArrayList<>();
		this.especiePermitida = especiePermitida;
	}

	// Embarcar: tenta adicionar um animal, validando tipo, espécie e capacidade
	@Override
	public boolean embarcar(Object item) {
		if (!validarItem(item)) return false;

		Animal animal = (Animal) item;
		if (!validarEspecie(animal)) return false;
		if (validarCapacidade()) return false;

		animais.add(animal);
		System.out.println("Animal " + animal.getNome() + " (" + animal.getEspecie() + ") embarcou no vagão #" + getNumero());
		return true;
	}

	// Desembarcar: tenta remover um animal
	@Override
	public boolean desembarcar(Object item) {
		if (!validarItem(item)) return false;

		Animal animal = (Animal) item;
		boolean removido = animais.remove(animal);
		if (removido) {
			System.out.println("Animal " + animal.getNome() + " desembarcou do vagão #" + getNumero());
		} else {
			System.out.println("Animal não encontrado no vagão #" + getNumero());
		}
		return removido;
	}

	// Buscar: procura um animal pelo nome
	@Override
	public Object buscar(String identificador) {
		for (Animal a : animais) {
			if (a.getNome().equalsIgnoreCase(identificador)) {
				return a;
			}
		}
		System.out.println("Animal com nome " + identificador + " não encontrado.");
		return null;
	}

	// Listar: exibe todos os animais embarcados
	@Override
	public void listarConteudo() {
		System.out.println("--- Vagão de Animais #" + getNumero() + " ---");
		if (animais.isEmpty()) {
			System.out.println("Nenhum animal embarcado.");
			return;
		}
		for (Animal a : animais) {
			System.out.println("  - " + a.getNome() + " | Espécie: " + a.getEspecie() + " | Peso: " + a.getPeso() + "kg");
		}
		System.out.println("Ocupação: " + animais.size() + "/" + getCapacidadeMaxima());
	}

	@Override
	public void exibirStatus() {
		super.exibirStatus();
		System.out.println("Tipo: Animais | Espécie permitida: " + especiePermitida + " | Ocupação: " + animais.size() + "/" + getCapacidadeMaxima());
	}

	// Valida se item é um animal
	private boolean validarItem(Object item) {
		if (!(item instanceof Animal)) {
			System.out.println("Item inválido. Este vagão transporta apenas animais.");
			return false;
		}
		return true;
	}

	// Valida se a espécie do animal é permitida
	private boolean validarEspecie(Animal animal) {
		if (!animal.getEspecie().equalsIgnoreCase(especiePermitida)) {
			System.out.println("Espécie não permitida. Este vagão aceita apenas: " + especiePermitida);
			return false;
		}
		return true;
	}

	// Valida se o vagão atingiu capacidade máxima
	private boolean validarCapacidade() {
		if (animais.size() >= getCapacidadeMaxima()) {
			System.out.println("Vagão lotado. Capacidade máxima: " + getCapacidadeMaxima());
			return true;
		}
		return false;
	}
}