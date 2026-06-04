package modelo;

/**
 * Representa um passageiro no sistema de trem de ferro.
 * Imutável: uma vez criado, um passageiro não pode ter seus dados alterados.
 */
public final class Passageiro {
	private final String nome;
	private final String cpf;

	// Construtor: inicializa um novo passageiro com nome e CPF
	public Passageiro(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	// Obtém o nome do passageiro
	public String getNome() { return nome; }

	// Obtém o CPF do passageiro (usado como identificador único)
	public String getCpf() { return cpf; }

	@Override
	public String toString() {
		return "Passageiro{nome='" + nome + "', cpf='" + cpf + "'}";
	}
}

