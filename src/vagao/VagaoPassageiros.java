package vagao;

import modelo.Passageiro;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de Vagao para transporte de passageiros.
 * Responsável por gerenciar embarque, desembarque e busca de passageiros.
 */
public class VagaoPassageiros extends Vagao {
	private List<Passageiro> passageiros;

	// Construtor: inicializa vagão de passageiros
	public VagaoPassageiros(int numero, int capacidadeMaxima) {
		super(numero, capacidadeMaxima);
		this.passageiros = new ArrayList<>();
	}

	// Embarcar: tenta adicionar um passageiro, validando tipo e capacidade
	@Override
	public boolean embarcar(Object item) {
		if (!validarItem(item)) return false;
		if (validarCapacidade()) return false;

		Passageiro passageiro = (Passageiro) item;
		passageiros.add(passageiro);
		System.out.println("Passageiro " + passageiro.getNome() + " embarcou no vagão #" + getNumero());
		return true;
	}

	// Desembarcar: tenta remover um passageiro
	@Override
	public boolean desembarcar(Object item) {
		if (!validarItem(item)) return false;

		Passageiro passageiro = (Passageiro) item;
		boolean removido = passageiros.remove(passageiro);
		if (removido) {
			System.out.println("Passageiro " + passageiro.getNome() + " desembarcou do vagão #" + getNumero());
		} else {
			System.out.println("Passageiro não encontrado no vagão #" + getNumero());
		}
		return removido;
	}

	// Buscar: procura um passageiro pelo CPF
	@Override
	public Object buscar(String identificador) {
		for (Passageiro p : passageiros) {
			if (p.getCpf().equals(identificador)) {
				return p;
			}
		}
		System.out.println("Passageiro com CPF " + identificador + " não encontrado.");
		return null;
	}

	// Listar: exibe todos os passageiros embarcados
	@Override
	public void listarConteudo() {
		System.out.println("--- Vagão de Passageiros #" + getNumero() + " ---");
		if (passageiros.isEmpty()) {
			System.out.println("Nenhum passageiro embarcado.");
			return;
		}
		for (Passageiro p : passageiros) {
			System.out.println("  - " + p.getNome() + " | CPF: " + p.getCpf());
		}
		System.out.println("Ocupação: " + passageiros.size() + "/" + getCapacidadeMaxima());
	}

	@Override
	public void exibirStatus() {
		super.exibirStatus();
		System.out.println("Tipo: Passageiros | Ocupação: " + passageiros.size() + "/" + getCapacidadeMaxima());
	}

	// Valida se item é um passageiro
	private boolean validarItem(Object item) {
		if (!(item instanceof Passageiro)) {
			System.out.println("Item inválido. Este vagão transporta apenas passageiros.");
			return false;
		}
		return true;
	}

	// Valida se o vagão atingiu capacidade máxima
	private boolean validarCapacidade() {
		if (passageiros.size() >= getCapacidadeMaxima()) {
			System.out.println("Vagão lotado. Capacidade máxima: " + getCapacidadeMaxima());
			return true;
		}
		return false;
	}
}