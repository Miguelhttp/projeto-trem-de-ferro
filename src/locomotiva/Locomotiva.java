package locomotiva;

/**
 * Representa a locomotiva que puxa o trem de ferro.
 * Responsável por gerenciar velocidade, limite de vagões e operações de movimento.
 */
public class Locomotiva {
	private String modelo;
	private double velocidadeAtual;
	private double velocidadeMaxima;
	private int maxVagoes;

	// Construtor: inicializa uma nova locomotiva com modelo, velocidade máxima e capacidade de vagões
	public Locomotiva(String modelo, double velocidadeMaxima, int maxVagoes) {
		this.modelo = modelo;
		this.velocidadeMaxima = velocidadeMaxima;
		this.velocidadeAtual = 0;
		this.maxVagoes = maxVagoes;
	}

	// Acelera a locomotiva incrementando velocidade, limitada pela velocidade máxima
	public void speed(double incremento) {
		velocidadeAtual = Math.min(velocidadeAtual + incremento, velocidadeMaxima);
	}

	// Desacelera a locomotiva decrementando velocidade, limitada a zero
	public void slow(double decremento) {
		velocidadeAtual = Math.max(velocidadeAtual - decremento, 0);
	}

	// Exibe o status atual da locomotiva
	public void showStatus() {
		System.out.println("Modelo: " + modelo);
		System.out.println("Velocidade Atual: " + velocidadeAtual + " km/h");
		System.out.println("Velocidade Máxima: " + velocidadeMaxima + " km/h");
		System.out.println("Capacidade Máxima de Vagoes: " + maxVagoes);
	}

	// Para a locomotiva definindo velocidade para zero
	public void stop() {
		velocidadeAtual = 0;
	}

	// Obtém a velocidade atual
	public double getVelocidadeAtual() { return velocidadeAtual; }

	// Obtém a velocidade máxima
	public double getVelocidadeMaxima() { return velocidadeMaxima; }

	// Obtém a capacidade máxima de vagões
	public int getMaxVagoes() { return maxVagoes; }

	// Obtém o modelo da locomotiva
	public String getModelo() { return modelo; }
}