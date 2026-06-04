# Sistema Trem de Ferro

## Descrição do Projeto

Um sistema orientado a objetos que simula a operação de um trem de ferro. O projeto demonstra conceitos fundamentais de POO como **herança**, **polimorfismo**, **encapsulamento** e **composição** através de um domínio realista: uma locomotiva que puxa diferentes tipos de vagões (passageiros, animais e carga).

## Estrutura de Pastas

```
src/
├── Main.java                    # Classe principal que demonstra o sistema
├── locomotiva/
│   └── Locomotiva.java         # Representa a locomotiva que puxa o trem
├── vagao/
│   ├── Vagao.java              # Classe abstrata que define o contrato de vagão
│   ├── VagaoPassageiros.java   # Implementação para transporte de passageiros
│   ├── VagaoAnimais.java       # Implementação para transporte de animais (com validação de espécie)
│   └── VagaoCarga.java         # Implementação para transporte de cargas (com validação de peso)
├── modelo/
│   ├── Passageiro.java         # Classe de modelo: representa um passageiro
│   ├── Animal.java             # Classe de modelo: representa um animal
│   └── Carga.java              # Classe de modelo: representa uma carga
└── trem/
    └── Trem.java               # Classe principal que agrega locomotiva e vagões
```

## Diagrama de Classes e Relacionamentos

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                      ┌──────────────────┐                       │
│                      │   Locomotiva     │                       │
│                      ├──────────────────┤                       │
│                      │ - modelo: String │                       │
│                      │ - velAtual: dbl  │                       │
│                      │ - velMax: dbl    │                       │
│                      │ - maxVagoes: int │                       │
│                      ├──────────────────┤                       │
│                      │ + speed(dbl)     │                       │
│                      │ + slow(dbl)      │                       │
│                      │ + stop()         │                       │
│                      └────────┬─────────┘                       │
│                               │ compõe                          │
│                               │ (1)                             │
│                      ┌────────▼─────────┐                       │
│                      │      Trem        │                       │
│                      ├──────────────────┤                       │
│                      │ - locomotiva     │                       │
│                      │ - vagoes: List   │◄─────┐               │
│                      ├──────────────────┤      │               │
│                      │ + adicionar()    │      │               │
│                      │ + remover()      │      │               │
│                      │ + listar()       │      │               │
│                      │ + acelerar()     │      │               │
│                      └──────────────────┘      │               │
│                                                │               │
│                    Hierarquia de Vagões       │               │
│                                                │               │
│        ┌──────────────────────────────────────┤               │
│        │                                      │               │
│   ┌────▼────────────┐ (abstrato)             │               │
│   │      Vagao      │                        │               │
│   ├─────────────────┤                        │               │
│   │ - numero: int   │                        │               │
│   │ - capMax: int   │                        │               │
│   ├─────────────────┤                        │               │
│   │ + embarcar()    │                        │               │
│   │ + desembarcar() │                        │               │
│   │ + buscar()      │                        │               │
│   │ + listar()      │                        │               │
│   └────┬──────┬──────┬───────────────────────┘               │
│        │      │      │                                       │
│        │ herda de    │ herda de    │ herda de               │
│        │      │      │                                       │
│ ┌──────▼──┐ ┌─▼──────────┐  ┌────────────┐                 │
│ │VagaoPass│ │VagaoAnimais│  │VagaoCarga  │                 │
│ ├────────┤ ├────────────┤  ├────────────┤                 │
│ │-pass   │ │-animais    │  │-cargas     │                 │
│ │: List  │ │: List      │  │: List      │                 │
│ │        │ │-especie    │  │-pesoMax    │                 │
│ ├────────┤ │-Permitida  │  │-pesoAtual  │                 │
│ │impl.de │ ├────────────┤  ├────────────┤                 │
│ │abstrato│ │impl.de     │  │impl.de     │                 │
│ └────────┘ │abstrato    │  │abstrato    │                 │
│            └────────────┘  └────────────┘                 │
│                                                             │
│            Composição com Objetos de Modelo               │
│                                                             │
│    ┌────────────────┐                                      │
│    │  Passageiro    │  ┌────────────────┐                 │
│    ├────────────────┤  │     Animal     │                 │
│    │ - nome: String │  ├────────────────┤                 │
│    │ - cpf: String  │  │ - nome: String │                 │
│    └────────────────┘  │ - especie: Str │                 │
│     embarcado em ▲     │ - peso: double │                 │
│  VagaoPassageiros│     └────────────────┘                 │
│                        embarcado em ▲                      │
│                       VagaoAnimais   │                     │
│                                                             │
│                 ┌────────────────┐                         │
│                 │     Carga      │                         │
│                 ├────────────────┤                         │
│                 │ - codigo: Str  │                         │
│                 │ - desc: String │                         │
│                 │ - peso: double │                         │
│                 └────────────────┘                         │
│                  embarcado em ▲                            │
│                    VagaoCarga │                            │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

## Descrição das Classes

### Pacote `modelo`

Contém classes que representam objetos do domínio do trem:

- **Passageiro.java**: Representa um passageiro. Imutável (atributos finais). 
  - Atributos: `nome`, `cpf`
  - Responsabilidade: Modelar dados de um passageiro identificado por CPF

- **Animal.java**: Representa um animal a ser transportado. Imutável.
  - Atributos: `nome`, `especie`, `peso`
  - Responsabilidade: Modelar dados de um animal com validação de espécie

- **Carga.java**: Representa uma carga a ser transportada. Imutável.
  - Atributos: `codigo`, `descricao`, `peso`
  - Responsabilidade: Modelar dados de uma carga identificada por código

### Pacote `locomotiva`

- **Locomotiva.java**: Representa a locomotiva que puxa o trem.
  - Atributos: `modelo`, `velocidadeAtual`, `velocidadeMaxima`, `maxVagoes`
  - Responsabilidade: Gerenciar aceleração, desaceleração, parada e capacidade de vagões

### Pacote `vagao`

Implementa a hierarquia de vagões usando herança e polimorfismo:

- **Vagao.java** (abstrata): Define o contrato que todos os vagões devem seguir.
  - Atributos: `numero`, `capacidadeMaxima`
  - Métodos abstratos: `embarcar()`, `desembarcar()`, `buscar()`, `listarConteudo()`
  - Responsabilidade: Estabelecer interface comum para todos os tipos de vagão

- **VagaoPassageiros.java**: Transporte de passageiros com limite de quantidade.
  - Atributos adicionais: `passageiros: List<Passageiro>`
  - Validações: Tipo de item, capacidade máxima
  - Responsabilidade: Gerenciar embarque/desembarque de passageiros

- **VagaoAnimais.java**: Transporte de animais com restrição de espécie.
  - Atributos adicionais: `animais: List<Animal>`, `especiePermitida: String`
  - Validações: Tipo de item, espécie permitida, capacidade máxima
  - Responsabilidade: Gerenciar embarque/desembarque de animais com validação de espécie

- **VagaoCarga.java**: Transporte de cargas com limite de peso.
  - Atributos adicionais: `cargas: List<Carga>`, `pesoMaximo`, `pesoAtual`
  - Validações: Tipo de item, capacidade de itens, limite de peso total
  - Responsabilidade: Gerenciar embarque/desembarque de cargas com controle de peso

### Pacote `trem`

- **Trem.java**: Classe que agrega a locomotiva e os vagões.
  - Atributos: `locomotiva: Locomotiva`, `vagoes: List<Vagao>`
  - Responsabilidade: Gerenciar composição do trem, adicionar/remover vagões, delegar comandos de movimento

### Classe Principal

- **Main.java**: Demonstra o sistema em funcionamento.
  - Cria instâncias de todos os componentes
  - Testa operações de embarque com validações
  - Demonstra polimorfismo ao listar vagões
  - Testa limites de capacidade e peso

## Como Compilar e Executar

### Compilação

Abra um terminal na raiz do projeto e execute:

```bash
javac -d bin src/**/*.java
```

Ou, se você estiver usando Windows PowerShell:

```powershell
Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { javac -d bin $_.FullName }
```

### Execução

```bash
java -cp bin Main
```

## Exemplos de Saída

O sistema demonstra:

1. **Criação de locomotiva e trem** - Exibe capacidade máxima de vagões
2. **Adição de vagões** - Tentativa de adicionar mais vagões que o permitido (falha controlada)
3. **Controle de velocidade** - Aceleração, desaceleração e parada com validação de velocidade máxima
4. **Embarque de passageiros** - Tentativa de exceder capacidade (falha controlada)
5. **Embarque de animais** - Validação de espécie permitida (falha controlada)
6. **Embarque de cargas** - Validação de peso máximo (falha controlada)
7. **Busca de itens** - Recuperação de passageiro por CPF, animal por nome, carga por código
8. **Desembarque** - Remoção de itens
9. **Listagem final** - Exibição de toda a composição usando polimorfismo

## Conceitos de POO Demonstrados

- **Encapsulamento**: Atributos privados com getters públicos
- **Herança**: Classe abstrata `Vagao` com três subclasses especializadas
- **Polimorfismo**: Cada vagão implementa `embarcar()`, `desembarcar()`, `buscar()` de forma específica
- **Composição**: `Trem` contém `Locomotiva` e `List<Vagao>`; `Vagao` contém `List<T>` de diferentes tipos
- **Imutabilidade**: Classes modelo (`Passageiro`, `Animal`, `Carga`) com atributos finais
- **Abstração**: Interface comum através da classe abstrata `Vagao`
- **Validação de negócio**: Limites de capacidade, tipos de item, pesos

## Tecnologia Utilizada

- **Java puro**: Sem frameworks externos
- **JDK 21** (compatível com versões anteriores)
- **Padrões de design**: Template Method (através de classe abstrata), Strategy (implícito nas subclasses)
- **Collections**: `ArrayList` para armazenar vagões e itens

## Melhorias Implementadas

- ✅ Limpeza de código (getters em uma linha, sem blocos vazios)
- ✅ Documentação com Javadoc e comentários funcionais
- ✅ Separação de responsabilidades (métodos auxiliares privados para validações)
- ✅ README.md completo com diagrama e instruções
- ✅ Código segue princípios SOLID e boas práticas de POO
