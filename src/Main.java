import locomotiva.Locomotiva;
import modelo.Animal;
import modelo.Carga;
import modelo.Passageiro;
import trem.Trem;
import vagao.VagaoAnimais;
import vagao.VagaoCarga;
import vagao.VagaoPassageiros;

/**
 * Classe de demonstração do sistema de trem de ferro.
 * Ilustra a criação e operação de uma composição de trem com diferentes tipos de vagões
 * e demonstra validações de capacidade, tipo de item e outras regras de negócio.
 */
public class Main {
	public static void main(String[] args) {
		// Inicializar locomotiva e trem
		Locomotiva locomotiva = new Locomotiva("O Trem Infinito", 120.0, 3);
		Trem trem = new Trem(locomotiva);

		System.out.println("=== SISTEMA TREM DE FERRO ===\n");
		trem.exibirStatus();

		// Criar vagões especializados
		VagaoPassageiros vagaoPassageiros = new VagaoPassageiros(1, 4);
		VagaoAnimais vagaoAnimais = new VagaoAnimais(2, 3, "Cachorro");
		VagaoCarga vagaoCarga = new VagaoCarga(3, 5, 1000.0);

		// Adicionar vagões à composição (tentativa de exceder limite)
		System.out.println("\n=== ADICIONANDO VAGÕES ===\n");
		trem.adicionarVagao(vagaoPassageiros);
		trem.adicionarVagao(vagaoAnimais);
		trem.adicionarVagao(vagaoCarga);
		VagaoPassageiros vagaoExtra = new VagaoPassageiros(4, 2);
		trem.adicionarVagao(vagaoExtra);

		// Controlar movimento da locomotiva
		System.out.println("\n=== CONTROLANDO O TREM ===\n");
		trem.acelerar(60.0);
		trem.exibirStatus();
		trem.acelerar(80.0);
		trem.exibirStatus();
		trem.desacelerar(30.0);
		trem.exibirStatus();
		trem.parar();
		trem.exibirStatus();

		// Embarcar passageiros (tentativa de exceder capacidade)
		System.out.println("\n=== EMBARCANDO PASSAGEIROS ===\n");
		Passageiro p1 = new Passageiro("João Victor", "111.111.111-11");
		Passageiro p2 = new Passageiro("Miguel Braga", "222.222.222-22");
		Passageiro p3 = new Passageiro("Kauan Rodrigues", "333.333.333-33");
		Passageiro p4 = new Passageiro("Akira", "444.444.444-44");
		Passageiro p5 = new Passageiro("Kyojuro Rengoku", "555.555.555-55");

		vagaoPassageiros.embarcar(p1);
		vagaoPassageiros.embarcar(p2);
		vagaoPassageiros.embarcar(p3);
		vagaoPassageiros.embarcar(p4);
		vagaoPassageiros.embarcar(p5);

		// Embarcar animais (validação de espécie)
		System.out.println("\n=== EMBARCANDO ANIMAIS ===\n");
		Animal a1 = new Animal("Jake", "Cachorro", 30.0);
		Animal a2 = new Animal("Falcão", "Cachorro", 25.0);
		Animal a3 = new Animal("Garfield", "Gato", 5.0);

		vagaoAnimais.embarcar(a1);
		vagaoAnimais.embarcar(a2);
		vagaoAnimais.embarcar(a3);

		// Embarcar cargas (validação de peso máximo)
		System.out.println("\n=== EMBARCANDO CARGAS ===\n");
		Carga c1 = new Carga("CRG-001", "Caixas de Eletrônicos", 300.0);
		Carga c2 = new Carga("CRG-002", "Sacos de Cimento", 400.0);
		Carga c3 = new Carga("CRG-003", "Tambores de Combustível", 500.0);

		vagaoCarga.embarcar(c1);
		vagaoCarga.embarcar(c2);
		vagaoCarga.embarcar(c3);

		// Buscar itens específicos
		System.out.println("\n=== BUSCANDO ITENS ===\n");
		System.out.println(vagaoPassageiros.buscar("111.111.111-11"));
		System.out.println(vagaoAnimais.buscar("Rex"));
		System.out.println(vagaoCarga.buscar("CRG-002"));

		// Desembarcar itens
		System.out.println("\n=== DESEMBARCANDO ITENS ===\n");
		vagaoPassageiros.desembarcar(p1);
		vagaoAnimais.desembarcar(a1);
		vagaoCarga.desembarcar(c1);

		// Exibir composição final (polimorfismo em ação)
		System.out.println("\n=== LISTAGEM FINAL DA COMPOSIÇÃO ===\n");
		trem.listarVagoes();
	}
}