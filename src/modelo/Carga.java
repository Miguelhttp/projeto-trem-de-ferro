package modelo;

/**
 * Representa uma carga no sistema de trem de ferro.
 * Imutável: uma vez criada, uma carga não pode ter seus dados alterados.
 */
public final class Carga {
	private final String codigo;
	private final String descricao;
	private final double peso;

	// Construtor: inicializa uma nova carga com código, descrição e peso
	public Carga(String codigo, String descricao, double peso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.peso = peso;
	}

	// Obtém o código único da carga
	public String getCodigo() { return codigo; }

	// Obtém a descrição da carga
	public String getDescricao() { return descricao; }

	// Obtém o peso da carga em kg
	public double getPeso() { return peso; }

	@Override
	public String toString() {
		return "Carga{codigo='" + codigo + "', descricao='" + descricao + "', peso=" + peso + "}";
	}
}