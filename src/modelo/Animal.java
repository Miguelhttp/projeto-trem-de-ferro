package modelo;

/**
 * Representa um animal no sistema de trem de ferro.
 * Imutável: uma vez criado, um animal não pode ter seus dados alterados.
 */
public final class Animal {
	private final String nome;
	private final String especie;
	private final double peso;

	// Construtor: inicializa um novo animal com nome, espécie e peso
	public Animal(String nome, String especie, double peso) {
		this.nome = nome;
		this.especie = especie;
		this.peso = peso;
	}

	// Obtém o nome do animal
	public String getNome() { return nome; }

	// Obtém a espécie do animal
	public String getEspecie() { return especie; }

	// Obtém o peso do animal em kg
	public double getPeso() { return peso; }

	@Override
	public String toString() {
		return "Animal{nome='" + nome + "', especie='" + especie + "', peso=" + peso + "}";
	}
}
