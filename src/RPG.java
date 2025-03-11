// Grupo 9 - Turma A
// Camile Vitória Rosa Santos
// Jaine Jesus Costa
// Leonardo Stolf Guzzardi
// Pedro Henrique de Assis

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/* Rota de navegação do código:
 * - Menu
 * - Historia
 * - Cidade dos doces
 * - Casa Abandonada
 * - Texto animado
 */

public class RPG {
    public static String companheiroEscolhido;
    public static String personagem;
    private static int posicao = 0; // para caso o jogador usar a habilidade voltar ao lugar certo da historia
    private static String pilula;
    private static int progresso2 = 0; // para direcionar a dica a questão certa
    private static boolean habilidadeUsada = false; // para não utilizar a habilidade mais de uma vez
    private static boolean cronometroAtivo = true;
    private static Timer timer;

    public static void menu(String nomeJogo) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("**********************************************\n");
        String apresentacaoJogo = "Bem-vindo ao JOGO DOS DESTINOS\n";
        TextoAnimado.aparecerTexto(apresentacaoJogo, 10);
        System.out.println("**********************************************");
        TextoAnimado.aparecerTexto("Escolha suas ações e determine o seu futuro.", 10);

        boolean continuar = true;

        do {
            System.out.println(" 1) Jogar\n 2) Regras\n 3) Créditos\n 4) Sair ");
            System.out.println("**********************************************");

            int opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    historia();
                    continuar = false;
                    break;
                case 2:
                    exibirRegras(entrada);
                    break;
                case 3:
                    exibirCreditos(entrada);
                    break;
                case 4:
                    System.out.println("Adeus, até a próxima aventura!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (continuar);

        entrada.close();
    }

    private static void exibirRegras(Scanner entrada) {
        System.out.println("\nResponda todas as questões dentro de 2 minutos.");
        System.out.println(
                "Utilize as habilidades de seu companheiro com sabedoria, pois só pode ser utilizada uma vez.");
        System.out.println("Para chamar a habilidade basta clicar a inicial do seu companheiro");
        System.out.println("Existe somente uma alternativa correta.");

        System.out.println("Pressione Enter para voltar ao menu...");
        entrada.nextLine();
    }

    private static void exibirCreditos(Scanner entrada) {
        System.out.println("Turma A - Grupo 9");
        System.out.println("Feito por:");
        System.out.println("Jaíne");
        System.out.println("Camile");
        System.out.println("Leonardo");
        System.out.println("Pedro Assis");

        System.out.println("Pressione Enter para voltar ao menu...");
        entrada.nextLine();
    }

    public static void voltarAoMenu() {
        System.out.println("\nVoltando ao menu principal...");
        menu("Jogo dos Destinos"); // Chama o método de menu novamente
    }

    public static void historia() {
        Scanner entrada = new Scanner(System.in);

        TextoAnimado.aparecerTexto("Iniciando o jogo...", 150);

        String texto1 = "\nVocê se vê em uma situação difícil, após o misterioso desaparecimento de seus pais.\n"
                + "Ao se mudar para a casa de sua tia, você encontra um antigo videogame escondido no sótão empoeirado da casa.\n"
                + "Intrigado com o objeto desconhecido, você decide jogá-lo. Ao iniciar o jogo, você personaliza seu personagem\n"
                + "escolhendo um nome e um companheiro de aventura.\n";
        TextoAnimado.aparecerTexto(texto1, 5);

        System.out.print("Qual o nome do seu personagem: ");
        personagem = entrada.nextLine();
        escolhaCompanheiro(entrada);
        historiaPrincipal();
        desafio01(entrada);
        caminho(entrada);
        entrada.close();
    }

    public static void historiaPrincipal() {
        String texto = "\nAo escolher o personagem, uma luz intensa surge do console, te sugando para um mundo mágico. "
                + "\nDe forma confusa, você logo percebe que está dentro do jogo. O ambiente é uma mistura de paisagens"
                + "\nbizarras e criaturas excêntricas. Você não sabe como sair e isso te preocupa.\n"
                + "\n======================================================================================================="
                + "\nObservando, encontra uma longa estrada de tijolos amarelos e decide segui-la. A estrada adentra "
                + "\numa floresta escura e sombria, ao final, você encontra uma porta com um enigma e uma voz ecoa:"
                + "\n" + personagem
                + "! Estávamos empolgados em ver você por aqui. Que bom que você decidiu seguir o seu destino... ou não."
                + "\nMas hey, pelo menos agora temos alguém para culpar quando as coisas derem errado! \" A floresta estava falando com você: "
                + "\n\"Mas enfim, com certeza você deve estar se perguntando como sair desse lugar. Durante a sua trajetória, terão 5 desafios"
                + "\nde lógica que te levarão para fora do jogo, claro, isso se você conseguir continuar."
                + "\nA primeira tarefa era abrir a porta trancada, marcada por símbolos estranhos. Ao descobrir um papel com um código, você "
                + "\nrapidamente percebeu que precisava destrancar a porta.\n"
                + "\n                             ________                      "
                + "\n                            |        |"
                + "\n     ^              ^       |  ____  |                                       "
                + "\n    ^^^            ^^^      | |    | |"
                + "\n   ^^^^^          ^^^^^     | |____| |"
                + "\n  ^^^^^^^        ^^^^^^^    |  ____ *|"
                + "\n ^^^^^^^^^      ^^^^^^^^^   | |    | |"
                + "\n^^^^^^^^^^^    ^^^^^^^^^^^  | |____| |"
                + "\n    ||              ||      |________|";
        TextoAnimado.aparecerTexto(texto, 2);
    }

    public static String escolhaCompanheiro(Scanner entrada) {
        int companheiro = 0;

        do {
            System.out.println(
                    "===================================================================================================");
            System.out.println("Escolha seu companheiro de aventura: ");
            System.out.print(
                    "\n* 1) Orion - Habilidoso em lógica. (Pode receber dicas em um desafio.) *\n" +
                            "* 2) Kira - Criativa e imprevisível. (Muda o desafio podendo retornar um mais fácil OU complicado) *\n");
            System.out.println(
                    "====================================================================================================");

            companheiro = entrada.nextInt();

            switch (companheiro) {
                case 1:
                    companheiroEscolhido = "Orion";
                    System.out.println(
                            "Ótima escolha! Se prepare para embarcar nessa aventura com " + companheiroEscolhido
                                    + "\nsua habilidade pode ser ativada pressionando a letra \"O\"");
                    System.out.println("==================================================================");
                    break;
                case 2:
                    companheiroEscolhido = "Kira";
                    System.out.println(
                            "Ótima escolha! Se prepare para embarcar nessa aventura com " + companheiroEscolhido
                                    + "\nsua habilidade pode ser ativada pressionando a letra \"K\"");
                    System.out.println("==================================================================");
                    break;
                default:
                    System.out.print("Companheiro não identificado!\n");
                    break;
            }

        } while (companheiro > 2);

        TextoAnimado.aparecerTexto(
                "\nAo seu lado, está " + companheiroEscolhido + ", o seu companheiro que acabou escolhendo quando "
                        + "\niniciou o jogo e que irá te ajuda durante a jornada",
                0);

        return companheiroEscolhido;
    }

    public static void desafio01(Scanner entrada) {
        progresso2 = 1;
        String desafio1 = "\nPara iniciar sua jornada é preciso destrancar a porta, então leia atentamente. "
                + "\nUtilizando o laço \"FOR\" para obter os quatro primeiros números pares que são múltiplos de 4. "
                + "\nQual das opções abaixo o levará ao tesouro da programação?\n"
                + "\nA)\n"
                + "    for (int i = 1; i <= 16; i++) { \n"
                + "        if (i % 4 == 0) { \n"
                + "            System.out.println(i); \n"
                + "        } \n"
                + "    }\n"
                + "\nB)\n"
                + "    for (int i = 4; i <= 16; i += 4) { \n"
                + "        System.out.println(i); \n"
                + "    }\n"
                + "\nC)\n"
                + "    for (int i = 2; i <= 8; i++) { \n"
                + "        System.out.println(i * 2);\n"
                + "    }\n"
                + "\nD)\n"
                + "    for (int i = 0; i < 4; i++) { \n"
                + "        System.out.println(i * 8); \n"
                + "    }\n";

        TextoAnimado.aparecerTexto(desafio1, 5);

        posicao = 1;
        int chancesDesafio1 = 0;
        boolean respostaCorreta1 = false;
        cronometro();

        do {
            System.out.println("Digite a resposta: ");
            String respDesafio1 = entrada.next().toUpperCase();

            // Verificando se a resposta é uma das opções válidas
            if (respDesafio1.equals("K") || respDesafio1.equals("O")) {
                pararCronometro();
                verificarChamandoCompanheiro(companheiroEscolhido, respDesafio1);
            } else if (!respDesafio1.equalsIgnoreCase("A") && !respDesafio1.equalsIgnoreCase("B") && !respDesafio1.equalsIgnoreCase("C") && !respDesafio1.equalsIgnoreCase("D")) {

                System.out.println("Escolha entre as alternativas disponíveis");
                System.out.println(desafio1); // Reexibe o desafio

            } else { // Caso a resposta seja válida
                if (respDesafio1.equals("B")) {
                    pararCronometro();
                    respostaCorreta1 = true;

                    TextoAnimado.aparecerTexto(
                            "Após alguns momentos de reflexão, você grita: \"A resposta correta é B!\""
                                    + "A porta então se abre lentamente... ",
                            5);
                } else {
                    // Incrementa o número de chances se a resposta não for correta
                    chancesDesafio1++;
                    System.out.println(
                            "Você tem só mais uma chance! ");

                    if (chancesDesafio1 >= 2) {
                        pararCronometro();
                        System.out.println("Não foi dessa vez!");
                        voltarAoMenu();
                    }
                }
            }
        } while (!respostaCorreta1);
    }

    public static void caminho(Scanner entrada) {
        TextoAnimado.aparecerTexto(
                "Ao atravessá-la ela some inesperadamente e agora você e seu companheiro se vêm cercados por duas escolhas:\n"
                        + "\nA) Um caminho íngreme que leva a uma montanha coberta de neve. Os ecos de vozes perdidas ressoam nas cavernas.\n"
                        + "\nB) Uma antiga estrutura coberta de musgo, que se estende sobre um abismo profundo.\n",
                5);

        boolean respostaCorreta2 = false;
        do {
            System.out.println("Escolha qual caminho seguir");
            String caminho = entrada.next().toUpperCase();

            switch (caminho) {
                case "A":
                    cidadeDoces(personagem, entrada);
                    respostaCorreta2 = true;

                    break;
                case "B":
                    casaAbandonada(personagem, companheiroEscolhido);
                    respostaCorreta2 = true;
                    break;
                default:
                    System.out.println("Caminho não identificado");
                    break;
            }
        } while (!respostaCorreta2);
    }

    public static void cidadeDoces(String personagem, Scanner entrada) {
        String texto = "\nVocê decide subir a montanha coberta de neve, está muito frio e do alto você avista uma Cidade dos Doces."
                + "\nAo descer você observa uma pequena cabana feita de chocolate, então, inesperadamente aparece um ser muito curioso  "
                + "\nque vai até você e te dá boas vindas \"Olá " + personagem
                + ", seja bem vindo a minha cidade, eu sou o Chapeleiro Louco,"
                + "\nresponsável por todas a doçuras desse mundo mágico, acredito que você seja o nosso predestinado, posso te ajudar"
                + "\na passar para o próximo desafio, porém terá que resolver um enigma\" ele te passa o seguinte enigma:\n";

        TextoAnimado.aparecerTexto(texto, 5);

        // Funções que vai continuar a historia
        CidadeDosDoces_Desafio01();
        gatoSorridente(entrada);
        desafio3escolherPilula(entrada);
    }

    public static void CidadeDosDoces_Desafio01() {

        progresso2 = 2;
        Scanner entrada = new Scanner(System.in);

        String desafio = "Eu sou um número que:\n" +
                "Quando você me divide por 2, o resto é 1.\n" +
                "Quando você me divide por 3, o resto é 0.\n" +
                "Sou menor que 10.\n" +
                "Pergunta: Que número sou eu?";
        TextoAnimado.aparecerTexto(desafio, 5);

        int tentativa = 1;
        cronometro();

        while (tentativa <= 2) {

            String resp = entrada.nextLine().toLowerCase();
            resp = verificarResposta(resp,entrada);

            try {
                int respInt = Integer.parseInt(resp); // tentativa de converter a resposta em um número
                if (respInt == 3) {
                    pararCronometro();
                    System.out.print("Parabéns!Resposta correta\n");
                    break;
                } else if (respInt != 3 && tentativa == 2) {
                    pararCronometro();
                    System.out.println("Não foi dessa vez!");
                    voltarAoMenu();
                } else {
                    System.out.print(
                            "Você tem só mais uma chance! Se não ficará preso para sempre na Cidade Dos Doces\n");
                    tentativa++;
                }
            } catch (NumberFormatException e) { // Se a entrada não for um número entra nesse bloco
                pararCronometro();
                verificarChamandoCompanheiro(companheiroEscolhido, resp);
            }

        }
    }

    public static void gatoSorridente(Scanner entrada) {
        posicao = 3; // posição do jogador
        progresso2 = 7;
        String enunciado = "A escuridão e o silêncio se instalam ao seu redor, ao abrir os olhos, encontra paredes cobertas"
                + "\npor códigos e equações. Você estava na Caverna do Gato Sorridente. Uma voz rouca ecoa do fim caverna dizendo"
                + "\n\"Bom, imaginava que te veria em algum momento, mas sejamos breves.\" O Gato, com seu sorriso enigmático surgi"
                + "\ne inesperadamente passa a seguinte mensagem: \"Você terá que resolver um desafio, e se você conseguir resolvê-lo"
                + "\nterá que escolher entre duas pílulas\" diante desse cenário ele manda você para uma biblioteca enorme onde o seu"
                + "\nobjeto é encontrar o livro de feitiços que irá te levar para uma sala onde encontrará as pílulas."
                + "\n"
                + "\n                                    /\\_/\\"
                + "\n                                   ( o.o )"
                + "\n                                    > ^ <"
                + "\n"
                + "Para encontrar o livro dos feitiços, some todos os números de 3 até 15, pulando de 3 em 3.\nUtilize um loop do-while para encontrar a resposta?\n"
                +
                "A)     int i = 3, soma = 0; \n     do { \n     soma += i;\n        i += 3;\n   } while (i <= 15);\n    System.out.println(\"A soma é: \" + soma);\n"
                +
                "\nB)   int i = 3, soma = 0; \n     do { \n     soma += i;\n        i++;\n      } while (i <= 15);\n    System.out.println(\"A soma é: \" + soma);\n"
                +
                "\nC)   int i = 3, soma = 0; \n     do { \n     soma += i;\n        i += 3;\n    } while (i < 15);\n     System.out.println(\"A soma é: \" + soma);\n"
                +
                "\nD)   int i = 3, soma = 0; \n     do { \n     soma += i;\n        i += 2;\n    } while (i <= 15);\n    System.out.println(\"A soma é: \" + soma);\n";

        TextoAnimado.aparecerTexto(enunciado, 5);


        int tentativa = 1;
        cronometro();

        do {

            System.out.print("Digite sua resposta: ");
            String resp = entrada.next().toLowerCase();

            if (resp.equals("a")) {
                pararCronometro();
                System.out.print("Resposta correta!\n");
                break;
            } else if (!resp.equals("a") && tentativa == 2) {
                if (resp.equals("k") || resp.equals("o")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, resp);
                }
                pararCronometro();
                System.out.println("Não foi dessa vez! Voltando ao menu...");
                voltarAoMenu();
            } else {
                if (resp.equals("k") || resp.equals("o")) { // se digitar a inicial do companheiro entra nesse bloco
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, resp);
                }
                System.out
                        .println("Você tem só mais uma chance! Se não ficará preso para sempre na Cidade Dos Doces\n");
                tentativa++;
            }

        } while (tentativa <= 2);
    }

    public static String desafio3escolherPilula(Scanner entrada) {
        System.out.println("1. Pílula Azul");
        System.out.println("2. Pílula Vermelha");

        int escolha;

        while (true) {
            System.out.print("Digite o número da sua escolha: ");

            if (entrada.hasNextInt()) {
                escolha = entrada.nextInt();
                entrada.nextLine();

                if (escolha == 1) {
                    pilula = "Azul";
                    desafio3PílulaAzul(entrada);
                } else if (escolha == 2) {
                    pilula = "Vermelho";
                    desafio3PílulaVermelha(entrada);
                } else {
                    System.out.println("Escolha inválida! Digite 1 para Pílula Azul ou 2 para Pílula Vermelha.");
                }
            }
        }
    }

    public static String desafio3PílulaVermelha(Scanner entrada) {
        String narrativa = "\nVocê toma a pílula vermelha e no mesmo instante começa a ter visões sobre tudo o que aconteceu, então você\n"
                +
                "enxerga a obscuridade por trás daquele mundo mágico.\n"
                +
                "Há muito tempo, uma bruxa chamada Astrid utilizou sua magia para criar um jogo amaldiçoado, projetado para aprisionar\n"
                +
                "aqueles que não conseguissem completar os 5 desafios de seu jogo. Seu objetivo era simples: garantir que as almas dos\n"
                +
                "jogadores ficassem presas para sempre, condenadas a enfrentar seus medos e anseios em um labirinto sem fim. Ao mergulhar\n"
                +
                "nas visões proporcionadas pela pílula vermelha, você descobre a verdade devastadora: seus pais, pessoas que você amava\n"
                +
                "profundamente, foram atraídos por essa armadilha traiçoeira. Suas almas agora estão aprisionadas dentro do jogo, vagando\n"
                +
                "em um mundo distorcido, cercadas por ilusões que os impedem de se libertar."
                +
                "A única forma de libertar todas essas almas é se o tão esperado predestinado sacrificar suas memórias por todos.\n\n"
                +
                "O que você escolhe?\n" +

                "A) Terminar de completar todos os desafios e destruir o jogo para sempre.\n" +
                "B) Salvar os seus pais e todas as almas presas dentro do jogo, em troca sacrificar sua memória.\n";

        TextoAnimado.aparecerTexto(narrativa, 5);

        while (true) {
            System.out.print("Digite sua escolha (A ou B): ");
            char escolha = entrada.next().toUpperCase().charAt(0);

            if (escolha == 'A') {
                desafio3PílulaAzul(entrada);
            } else if (escolha == 'B') {
                String textoFinal = "O peso dessa revelação se torna uma chama ardente dentro de você, alimentando uma determinação inabalável\n"
                        +
                        "de resgatar seus pais e confrontar a bruxa que os condenou a essa existência sombria. Neste momento, você entende o porquê\n"
                        +
                        "de ser o predestinado.\n"
                        +
                        "Depois de absorver toda essa revelação, você se levanta, sentindo uma nova força pulsar dentro de você.\n"
                        +
                        "Com a pílula vermelha ainda ecoando em sua mente, você decide que não pode perder tempo.\n"
                        +
                        "Ao olhar ao seu redor, uma sensação de vertigem toma conta de você. A escuridão se desfez em um turbilhão de luz,\n"
                        +
                        "e, quando seus olhos se abriram novamente, você se viu à beira de um lago sereno.\n" +
                        "As águas refletiam a luz de um céu estrelado, e uma brisa suave acariciava seu rosto.\n" +
                        "Mas a beleza do cenário era ofuscada por uma sensação de tristeza profunda; ali era um lago de almas perdidas.\n\n";

                TextoAnimado.aparecerTexto(textoFinal, 5);
                desafioLago(entrada);
                desafioEspelhos(entrada);
                desafio05PilulaVermelha(entrada);

            } else {
                System.out.println(
                        "Escolha inválida! Digite A para completar os desafios ou B para salvar seus pais.");
            }

        }

    }

    public static void desafioLago(Scanner entrada) {
        posicao = 6;
        progresso2 = 9;
        String desafioLago = "Fico feliz por você ter chegado até aqui\". Você se demonstrava muito nervoso, pois aquele lugar continha uma energia muito pesada."
                + "\n\"Não precisa ficar nervoso, estamos no lago das almas perdidas. Todas as pessoas que estão aqui tentaram vencer"
                + "\no jogo e não conseguiram. Como consequência, suas almas estão presas nesse mundo para sempre.\"\n"
                + "\nVocê questiona a alma se não há nenhum jeito de tirá-las dali, e a alma então responde: \"Sim, mas suas escolhas"
                + "\nimpactam tudo o que acontece nesse mundo, e não posso te falar mais nada sobre isso. Como você chegou até a penúltima"
                + "\netapa, vou te orientar sobre o próximo desafio.\""
                + "\nVocê fica se questionando o que teria acontecido se tivesse tomado a pílula vermelha, se teria alguma forma de"
                + "\nsalvar aquelas pobres almas. Mas é interrompido pela alma, que te entrega o enunciado do desafio:"
                + "\n\"Você tem um array de números inteiros. Sua tarefa é contar quantos números pares existem nesse array."
                + "\nComo você implementaria o algoritmo para contar os números pares no array?\n"
                + "\nA) Usando um laço for para percorrer cada elemento do array e contar os números divisíveis por 2."
                + "\nB) Usando recursão para verificar cada elemento do array."
                + "\nC) Usando uma função que ordena o array antes de contar os números pares."
                + "\nD) Usando uma técnica de busca binária para procurar os números pares.";

        TextoAnimado.aparecerTexto(desafioLago, 5);

        boolean respostaCorreta1 = false;
        int contador = 0;
        cronometro();

        do {
            System.out.println("\nDigite a resposta");
            String respostaLago = entrada.next().toUpperCase();

            if (respostaLago.equals("K") || respostaLago.equals("O")) {
                pararCronometro();
                verificarChamandoCompanheiro(companheiroEscolhido, respostaLago);
            } else if (!respostaLago.equals("A") && !respostaLago.equals("B") && !respostaLago.equals("C") && !respostaLago.equals("D")) {
                System.out.println("Escolha entre as alternativas disponíveis");
                System.out.println(desafioLago);
            } else {
                if (respostaLago.equals("A")) {
                    pararCronometro();
                    respostaCorreta1 = true;

                    TextoAnimado.aparecerTexto("De forma convicta você diz, a letra correta é a letra A", 5);

                } else {
                    if (contador >= 2) {
                        pararCronometro();
                        System.out.println("Não foi dessa vez!");
                        voltarAoMenu();
                    }
                    contador++;
                    System.out.println("Você tem só mais uma chance!");

                }
            }

        } while (!respostaCorreta1);

    }

    public static void desafio3PílulaAzul(Scanner entrada) {
        String introducao = "\n********************************************************************************************************************"
                + "\nVocê fica imaginando o que poderia acontecer com você depois daquela escolha. Ao tomar a pílula azul,"
                + "\numa sensação de vertigem tomou conta de você. A escuridão se desfez em um turbilhão de luz, e, quando "
                + "\nseus olhos se abriram novamente, você se viu à beira de um lago sereno. As águas refletiam a luz de um céu"
                + "\nestrelado, e uma brisa suave acariciava seu rosto, Mas a beleza do cenário era ofuscada por uma sensação"
                + "\nde tristeza profunda; ali era um lago de almas perdidas.\n"
                + "\nDe repente, uma das almas começa a falar com você:";

        TextoAnimado.aparecerTexto(introducao, 5);
        desafioLago(entrada);
        desafioEspelhos(entrada);
        desafio05PilulaAzul(entrada);
    }

    public static void desafioEspelhos(Scanner entrada) {
        posicao = 7;
        String salaDosEspelhos = "\nVocê acerta o desafio e aquela alma então diz \"Boa sorte com a última etapa do jogo!\", de repente"
                + "\nvocê se vê dentro de um túnel onde há uma luz muito intensa no fim dele. Quando você termina de passar"
                + "\no túnel, sai dentro de um salão de um lindo castelo onde é recebido por uma multidão de seres mágicos.\n"
                + "\nNo trono desse castelo está uma linda rainha, e algum tipo de magia acaba te levando até ela.\n"
                + "\nAo chegar, ela te fala: \"Parabéns por escolher seguir com os desafios, você já está quase no final do jogo,"
                + "\nmas prepare-se, as coisas vão complicar. Sua próxima sala tem um conjunto de espelhos antigos, cada um com"
                + "\numa aura única e enigmática. A escolha incorreta te leva a um desaparecimento instantâneo para um mundo desconhecido.\n"
                + "\nA escolha correta, por sua vez, apresenta um desafio intelectual: uma pergunta que, se respondida corretamente, "
                + "\na uma recompensa grandiosa. Mas, se respondida incorretamente, desencadeia consequências imprevisíveis.\"\n\n"
                + "\nEm um passe de mágica você se encontra dentro da Sala de Espelhos\n";

        TextoAnimado.aparecerTexto(salaDosEspelhos, 5);
        progresso2 = 10;
        String perguntaPrimeiroEspelho = "\nQuestão do primeiro espelho:\n"
                + "Qual das seguintes sintaxes está correta para um laço for em Java?\n"
                + "A) for (int i = 0; i < 10; i++) { ... }\n" + "B) for (i = 0; i < 10; i++) { ... }\n"
                + "C) for (int i = 0; i <= 10; i--) { ... }\n" + "D) for (int i = 10; i > 0; i++) { ... }";

        TextoAnimado.aparecerTexto(perguntaPrimeiroEspelho, 5);
        boolean respostaCorreta2 = false;
        int tentativas = 0;
        cronometro();

        do {
            System.out.println("\nDigite a resposta");
            String respostaEspelho1 = entrada.next().toUpperCase();

            if (respostaEspelho1.equals("K") || respostaEspelho1.equals("O")) {
                pararCronometro();
                verificarChamandoCompanheiro(companheiroEscolhido, respostaEspelho1);
            }else if (!respostaEspelho1.equals("A") && !respostaEspelho1.equals("B") && !respostaEspelho1.equals("C") && !respostaEspelho1.equals("D")) {
                System.out.println("Escolha entre as alternativas disponíveis");
                System.out.println(perguntaPrimeiroEspelho);
            } else {
                if (respostaEspelho1.equals("A")) {
                    pararCronometro();
                    respostaCorreta2 = true;
                    System.out.println("Resposta correta! Você passa para o próximo desafio.");
                } else {
                    tentativas++;
                    System.out.println("Resposta incorreta! Você tem só mais ma chance");

                    if (tentativas >= 2) {
                        pararCronometro();
                        System.out.println("Não foi dessa vez!...");
                        voltarAoMenu();
                    }
                }
            }
        } while (!respostaCorreta2);
        progresso2 = 11;
        String perguntaSegundoEspelho = "\n\nQuestão do segundo espelho:\n"
                + "Em um laço while, o que acontece quando a condição se torna falsa?\n"
                + "A) O laço continua indefinidamente.\n"
                + "B) O laço é encerrado.\n"
                + "C) O programa é encerrado.\n"
                + "D) Ocorre um erro de compilação.";

        TextoAnimado.aparecerTexto(perguntaSegundoEspelho, 5);
        boolean respostaCorreta3 = false;
        int tentativas3 = 0;
        cronometro();

        do {
            System.out.println("\nDigite a resposta");
            String respostaEspelho2 = entrada.next().toUpperCase();

            if (respostaEspelho2.equals("K") || respostaEspelho2.equals("O")) {
                pararCronometro();
                verificarChamandoCompanheiro(companheiroEscolhido, respostaEspelho2);
            }else if (!respostaEspelho2.equals("A") && !respostaEspelho2.equals("B") && !respostaEspelho2.equals("C") && !respostaEspelho2.equals("D")) {
                System.out.println("Escolha entre as alternativas disponíveis");
                System.out.println(perguntaSegundoEspelho);
            } else {
                if (respostaEspelho2.equals("B")) {
                    pararCronometro();
                    respostaCorreta3 = true;

                    System.out.println("Resposta correta! Você passa para o próximo desafio.");

                } else {
                    if (tentativas3 >= 2) {
                        pararCronometro();
                        System.out.println("Não foi dessa vez!...");
                        voltarAoMenu();

                    }
                    tentativas3++;
                    System.out.println("Resposta incorreta! Você tem só mais ma chance");
                }
            }

        } while (!respostaCorreta3);
        progresso2 = 12;
        String perguntaTerceiroEspelho = "\n\nQuestão do terceiro espelho:\n"
                + "Como você implementaria o algoritmo de busca binária utilizando um laço?\n"
                + "A) Utilizando um laço while para dividir o intervalo de busca a cada iteração.\n"
                + "B) Utilizando um laço for para percorrer todo o array.\n"
                + "C) Utilizando um laço do-while para garantir que o elemento seja encontrado.\n"
                + "D) Utilizando recursão.";

        TextoAnimado.aparecerTexto(perguntaTerceiroEspelho, 5);
        boolean respostaCorreta4 = false;
        int tentativas4 = 0;
        cronometro();

        do {
            System.out.println("\nDigite a resposta");
            String respostaEspelho4 = entrada.next().toUpperCase();

            if (respostaEspelho4.equals("K") || respostaEspelho4.equals("O")) {
                pararCronometro();
                verificarChamandoCompanheiro(companheiroEscolhido, respostaEspelho4);
            }else if (!respostaEspelho4.equals("A") && !respostaEspelho4.equals("B") && !respostaEspelho4.equals("C") && !respostaEspelho4.equals("D")) {
                System.out.println("Escolha entre as alternativas disponíveis");
                System.out.println(perguntaTerceiroEspelho);
            } else {
                if (respostaEspelho4.equals("A")) {
                    pararCronometro();
                    respostaCorreta4 = true;

                    System.out.println("Resposta correta! Você passa para o próximo desafio.");
                    String textoFinalEspelho = "\n\nAo finalizar o desafio, a rainha volta até você e diz:\n"
                            + "\"Parabéns por ter chegado à última fase do jogo!!. Nessa etapa, você terá que resolver um enigma;\n"
                            + "você terá que resolver o horário do jantar.\"\n"
                            + "Você pensa como assim um jantar? Cadê aquela pessoa tão má que prendeu aquelas almas aqui dentro?\n\n";

                    TextoAnimado.aparecerTexto(textoFinalEspelho, 5);
                    System.out.println("\nParabéns! Você completou o desafio dos espelhos.\n\n");
                    break;

                } else {
                    if (tentativas4 >= 2) {
                        pararCronometro();
                        System.out.println("Não foi dessa vez!...");
                        voltarAoMenu();
                    }
                    tentativas4++;
                    System.out.println("Resposta incorreta! Você tem só mais ma chance");
                }
            }

        } while (!respostaCorreta4);

    }

    public static void casaAbandonada(String personagem, String companheiroEscolhido) {
        progresso2 = 3;
        Scanner entrada = new Scanner(System.in);
        String texto = "\nAo passar pela estrutura coberta de musgo, você se depara com um caminho estreito, cercado por árvores retorcidas "
                + "\ne cobertas de uma neblina densa. De repente, você avista uma casa abandonada e de dentro dela sai um ser muito assustador,"
                + "\nera o Homem das Sombras."
                + "\n                                                   /\\"
                + "\n                                                  /  \\"
                + "\n                                                 /    \\"
                + "\n                                                /______\\"
                + "\n                                               |   __   |"
                + "\n                                               |  |  |  |"
                + "\n                                               |__|__|__|"
                + "\n\"" + personagem
                + ", finalmente você chegou\". Ele diz, sua voz ecoando com um tom sussurrante e ameaçador.\n"
                + "\n\"Acredito que você seja o nosso escolhido. Estou aqui para ajudá-lo a seguir para o próximo desafio, mas primeiro,"
                + "\nvocê terá que resolver um enigma. Mas cuidado, pois o que está em jogo pode ser mais do que você imagina.\""
                + "\nEle, então, se inclina para mais perto, seus olhos penetrantes fixados em você, te entrega uma pedra e diz: \"A pedra "
                + "\nguarda a chave para a próxima porta. Você deve encontrar a sequência correta de símbolos para conseguir abrir\". O Homem "
                + "\ndas Sombras mostrou-lhes a sequência de símbolos, mas cada símbolo estava incompleto. Para completar a sequência, você"
                + "\nprecisava resolver um desafio, acertar qual o código correto verifica se um número é primo."
                + "\n\nA) "
                + "\npublic static boolean isPrimo(int num) {"
                + "\n    if (num <= 1) {"
                + "\n        return false;"
                + "\n    }"
                + "\n    for (int i = 2; i <= num / 2; i++) {"
                + "\n        if (num % i == 0) {"
                + "\n            return false;"
                + "\n        }"
                + "\n    }"
                + "\n    return true;"
                + "\n}"
                + "\n\nB)"
                + "\npublic static boolean isPrimo(int num) {"
                + "\n    if (num <= 1) {"
                + "\n        return false;"
                + "\n    }"
                + "\n    for (int i = 2; i < num; i++) {"
                + "\n        if (num % i == 0) {"
                + "\n            return false;"
                + "\n        }"
                + "\n    }"
                + "\n    return true;"
                + "\n}"
                + "\n\nC)"
                + "\npublic static boolean isPrimo(int num) {"
                + "\n    if (num <= 1) {"
                + "\n        return false;"
                + "\n    }"
                + "\n    for (int i = 2; i * i <= num; i++) {"
                + "\n        if (num % i == 0) {"
                + "\n            return false;"
                + "\n        }"
                + "\n    }"
                + "\n    return true;"
                + "\n}"
                + "\n\nD) Todas as respostas estão corretas";

        TextoAnimado.aparecerTexto(texto, 5);

        CasaAbandonada_Desafio02(entrada);
        desafioBuracoNegro(entrada);
        gatoSorridente(entrada);
        desafio3escolherPilula(entrada);
    }

    public static void CasaAbandonada_Desafio02(Scanner entrada) {

        posicao = 4;
        int caminhoB = 1;
        cronometro();
        do {
            System.out.println("Digite a resposta");
            String respostaB = entrada.next().toUpperCase();

            if (respostaB.equals("C")) {
                pararCronometro();
                System.out.println("Resposta correta");
                String historia = "O Homem das Trevas sorri de forma debochada e diz\n\"Você conseguiu! Mas ainda terá muito trabalho pela frente\"";
        TextoAnimado.aparecerTexto(historia, 5);
                break;
            } else if (!respostaB.equals("C") && caminhoB == 2) {
                if (respostaB.equals("K") || respostaB.equals("O")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, respostaB);
                }
                pararCronometro();
                System.out.println("Não foi dessa vez! Voltando ao menu...");
                voltarAoMenu();
            } else {
                if (respostaB.equals("K") || respostaB.equals("O")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, respostaB);
                }
                System.out.println("Voce só tem mais uma chance");
                caminhoB++;
            }
        } while (caminhoB <= 2);
    }

    public static void desafioBuracoNegro(Scanner entrada) {
        posicao = 5;
        progresso2 = 4;
        String texto = "\nDe repente você é redirecionado até a porta e ao atravessá-la se depara com um buraco negro, onde você cai em um looping,"
                + "\npara conseguir parar é necessário responder 3 perguntas sobre laço de repetição, caso acerte você pode passar para"
                + "\na próxima sala e terá a opção de escolher entre duas pílulas.\n"
                + "\n===========================================================================================================================\n"
                + "\nQual é a diferença entre while e do-while em Java?"
                + "\nA) While executa o bloco pelo menos uma vez, enquanto do-while só executa se a condição inicial for verdadeira."
                + "\nB) Do-while executa o bloco pelo menos uma vez, enquanto while só executa se a condição inicial for verdadeira. "
                + "\nC) Não há diferença entre os dois. "
                + "\nD) While é mais eficiente que do-while.";

        TextoAnimado.aparecerTexto(texto, 5);

        int tentativa = 1;
        cronometro();

        do {
            System.out.println("Digite a resposta");
            String respostaB = entrada.next().toUpperCase();

            if (respostaB.equals("B")) {
                pararCronometro();
                TextoAnimado.aparecerTexto("Parabéns!! Você passou para a próxima questão.", 10);
                progresso2 = 5;
                System.out.println("=========================================================================");
                TextoAnimado.aparecerTexto("\nQual é a saída do seguinte código Java?\n"
                        + "\nfor (int i = 0; i < 5; i++) {"
                        + "\nif (i == 3) {"
                        + "\n   continue"
                        + "\n  }"
                        + "\nSystem.out.println(i)"
                        + "\n}\n"
                        + "\nA) 0 1 2 3 4 "
                        + "\nB) 0 1 2 4 "
                        + "\nC) 3 "
                        + "\nD) Nenhuma saída", 5);

                int tentativa1 = 1;
                cronometro();
                do {
                    System.out.println("Digite a resposta");
                    String respostaA = entrada.next().toUpperCase();

                    if (respostaA.equals("A")) {
                        pararCronometro();
                        TextoAnimado.aparecerTexto("Parabéns!! Você passou para a próxima questão.", 10);
                        progresso2 = 6;
                        System.out.println("=========================================================================");
                        TextoAnimado.aparecerTexto(
                                "\nQual laço é mais adequado para iterar sobre uma lista de elementos cujo"
                                        + "\ntamanho é conhecido antecipadamente?"
                                        + "\nA) while "
                                        + "\nB) do-while "
                                        + "\nC) for"
                                        + "\nD) Nenhum dos anteriores",
                                5);

                        int tentativa2 = 1;
                        cronometro();

                        do {
                            System.out.println("Digite a resposta");
                            String respostaC1 = entrada.next().toUpperCase();

                            if (respostaC1.equals("C")) {
                                pararCronometro();
                                TextoAnimado.aparecerTexto("Parabéns! Você acaba de sair do buraco negro.", 10);
                                System.out.println(
                                        "================================================================================");
                                System.out.println(
                                        "================================================================================");

                                break;
                            } else if (!respostaC1.equals("C") && tentativa2 == 2) {
                                if (respostaC1.equals("K") || respostaC1.equals("O")) {
                                    pararCronometro();
                                    verificarChamandoCompanheiro(companheiroEscolhido, respostaC1);
                                }
                                pararCronometro();
                                System.out.println("Não foi dessa vez! Voltando ao menu...");
                                voltarAoMenu();
                            } else {
                                if (respostaC1.equals("K") || respostaC1.equals("O")) {
                                    pararCronometro();
                                    verificarChamandoCompanheiro(companheiroEscolhido, respostaC1);
                                }
                                System.out.println("Voce só tem mais uma chance");
                                tentativa2++;
                            }
                        } while (tentativa2 <= 2);

                        break;
                    } else if (!respostaA.equals("A") && tentativa1 == 2) {
                        if (respostaA.equals("K") || respostaA.equals("O")) {
                            pararCronometro();
                            verificarChamandoCompanheiro(companheiroEscolhido, respostaA);
                        }
                        pararCronometro();
                        System.out.println("Não foi dessa vez! Voltando ao menu...");
                        voltarAoMenu();
                    } else {
                        if (respostaA.equals("K") || respostaA.equals("O")) {
                            pararCronometro();
                            verificarChamandoCompanheiro(companheiroEscolhido, respostaA);
                        }
                        System.out.println("\"Voce só tem mais uma chance\"");
                        tentativa1++;
                    }
                } while (tentativa1 <= 2);

                break;
            } else if (!respostaB.equals("B") && tentativa == 2) {
                if (respostaB.equals("K") || respostaB.equals("O")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, respostaB);
                }
                pararCronometro();
                System.out.println("Não foi dessa vez! Voltando ao menu...");
                voltarAoMenu();
            } else {
                if (respostaB.equals("K") || respostaB.equals("O")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, respostaB);
                }
                System.out.println("Voce só tem mais uma chance");
                tentativa++;
            }

        } while (tentativa <= 2);

    }

    public static void desafio05PilulaAzul(Scanner entrada) {
        progresso2 = 13;
        String historia = "Em um piscar de olhos você está diante de uma mesa repleta das suas comidas preferidas, no primeiro "
                + "\nmomento você fica confuso com aquele desafio, \"como uma última fase de jogo pode ser tão boa assim?\"\n"
                + "\nSua cabeça está confusa, neste momento criaturas mágicas começam a te servir e quando você está prestes a dar a primeira"
                + "\ngarfada em sua COMIDA PREFERIDA, você percebe que a princesa te olha fixamente e acha aquilo tudo muito estranho.\n"
                + "\nNo mesmo instante você começa a entender o que está acontecendo, coloca o garfo na mesa e começa a pensar qual"
                + "\nseria a resposta do enigma, a expressão facial da princesa muda rapidamente então ela pergunta \"Algum, problema?\""
                + "\npercebendo que você já entendeu que aquilo era uma farsa, todo aquele belo castelo começa a sumir e um lugar escuro "
                + "\ne sombrio se revela, e a princesa se torna uma mulher assustadora de olhos esbugalhados, expressão facial fria e postura"
                + "\nincomum.\n\"Quer dizer que você se acha esperto? Podemos dizer que não é tão esperto nassim Hahaha,como você pode pensar"
                + "\nque poderia sair do meu jogo?\" neste momento aquela terrível bruxa começa a se aproximar de você, nesse momento você só"
                + "\nconsegue pensar na resposta do enigma \"Ninguém nunca chegou até essa fase e não vai ser você que vai sair daqui agora, "
                + "\nvamos ter mais um morador para o meu lindo lago\" você sente sua força indo embora e no instante que acha que não vai mais"
                + "\nresistir grita a RESPOSTA  ";

        String enunciado = "Qual das opções abaixo representa a melhor resposta para o enigma, permitindo que você escape??\n"
                +
                "\nA) A resposta é um truque, não há resposta correta.\n"
                +
                "\nB) A princesa é, na verdade, uma bruxa que se alimenta de almas.\n"
                +
                "\nC) A comida do banquete está envenenada\n"
                +
                "\nD) A chave para a liberdade está escondida em algum lugar do castelo.\n";
        TextoAnimado.aparecerTexto(historia, 5);
        TextoAnimado.aparecerTexto(enunciado, 5);

        int tentativa = 1;
        cronometro();

        do {

            System.out.print("Digite sua resposta: ");
            String resp = entrada.next().toLowerCase();

            if (resp.equals("b")) {
                pararCronometro();
                System.out.print(
                        "A RESPOSTA É A LETRA B!!\nSua voz ressoa por todo mundo mágico, a bruxa fica sem reação e começa a gritar desesperadamente"
                                + "\n\"NÃÃO!! Isso é impossível, você não pode me vencer\" no mesmo instante a bruxa começa a se desintegrar "
                                + "\nsua morte e lenta e dolorosa.\n"
                                + "\nVocê vê uma luz muito forte, então um portal surge e você vai em direção a ele, a escuridão toma conta da sua visão."
                                + "\nAlgumas horas depois você acorda em casa novamente, tudo aquilo ainda parece muito doido, como aquilo poderia ter"
                                + "\nacontecido?"
                                + "\nO ambiente está em silêncio. A luz suave do dia entra pela janela, e você respira profundamente, aliviado."
                                + "\nVocê olha ao redor, mas não há mais sinais do jogo. A porta da sua casa está trancada, como se nada tivesse mudado."
                                + "\nNo entanto, uma sensação inquietante ainda paira no ar. Algo lhe diz que a verdadeira batalha não foi contra a bruxa,"
                                + "\n mas contra os próprios limites de sua mente."
                                + "\nVocê finalmente se sente livre. Pelo menos por agora.\n"
                                + "\n==========================================================================================================================="
                                + "\n                                                        FIM!!       "
                                + "\n===========================================================================================================================");
                voltarAoMenu();
                break;
            } else if (!(resp.equals("b")) && tentativa == 2) {
                if (resp.equals("k") || resp.equals("o")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, resp);
                }
                pararCronometro();
                System.out.println("Não foi dessa vez! Voltando ao menu...");
                voltarAoMenu();
            } else {
                if (resp.equals("k") || resp.equals("o")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, resp);
                }
                System.out
                        .println("Você tem só mais uma chance! Se não ficará preso para sempre na Cidade Dos Doces\n");
                tentativa++;
            }

        } while (tentativa <= 2);
    }

    public static void desafio05PilulaVermelha(Scanner entrada) {

        String historia = "Em um piscar de olhos você está diante de uma mesa repleta das suas comidas preferidas, no primeiro "
                + "\nmomento você fica confuso com aquele desafio, \"como uma última fase de jogo pode ser tão boa assim?\"\n"
                + "\nSua cabeça está confusa, neste momento criaturas mágicas começam a te servir e quando você está prestes a dar a primeira"
                + "\ngarfada em sua COMIDA PREFERIDA, você percebe que a princesa te olha fixamente e acha aquilo tudo muito estranho.\n"
                + "\nNo mesmo instante você começa a entender o que está acontecendo, coloca o garfo na mesa e começa a pensar qual"
                + "\nseria a resposta do enigma, a expressão facial da princesa muda rapidamente então ela pergunta \"Algum, problema?\""
                + "\npercebendo que você já entendeu que aquilo era uma farsa, todo aquele belo castelo começa a sumir e um lugar escuro "
                + "\ne sombrio se revela, e a princesa se torna uma mulher assustadora de olhos esbugalhados, expressão facial fria e postura"
                + "\nincomum.\n\"Quer dizer que você se acha esperto? Podemos dizer que não é tão esperto nassim Hahaha,como você pode pensar"
                + "\nque poderia sair do meu jogo?\" neste momento aquela terrível bruxa começa a se aproximar de você, nesse momento você só"
                + "\nconsegue pensar na resposta do enigma \"Ninguém nunca chegou até essa fase e não vai ser você que vai sair daqui agora, "
                + "\nvamos ter mais um morador para o meu lindo lago\" você sente sua força indo embora e no instante que acha que não vai mais"
                + "\nresistir grita a RESPOSTA  ";

        String enunciado = "Qual das opções abaixo representa a melhor resposta para o enigma, permitindo que você escape??\n"
                +
                "\nA) A resposta é um truque, não há resposta correta;\n"
                +
                "\nB) A princesa é, na verdade, uma bruxa que se alimenta de almas;\n"
                +
                "\nC) A comida do banquete está envenenada\n"
                +
                "\nD) A chave para a liberdade está escondida em algum lugar do castelo.\n";
        System.out.println(historia);
        System.out.println(enunciado);

        int tentativa = 1;
        cronometro();

        do {

            System.out.print("Digite sua resposta: ");
            String resp = entrada.next().toLowerCase();

            if (resp.equals("b")) {
                pararCronometro();
                System.out.print(
                        "A RESPOSTA É A LETRA B!!\nSua voz ressoa por todo mundo mágico, a bruxa fica sem reação e começa a gritar desesperadamente"
                                + "\n\"NÃÃO!! Isso é impossível, você não pode me vencer!\" No mesmo instante, a bruxa começa a se desintegrar,"
                                + "\nsua morte é lenta e dolorosa.\n"
                                + "\nApós atravessar um portal de luz intensa, você acorda em casa, mas tudo parece estranho e novo, como se"
                                + "\nestivesse vendo o mundo pela primeira vez."
                                + "\nO silêncio e a ausência de lembranças criam um vazio profundo, e você não consegue se lembrar de nada, nem"
                                + "\nmesmo dos rostos que antes pareciam familiares.\n\n"
                                + "\nQuando uma batida na porta revela um homem e uma mulher, você os encara sem reconhecer quem são. Eles se"
                                + "\napresentam como seus pais e, embora você não se lembre deles,"
                                + "\nalgo desperta dentro de você ao ouvir suas palavras. Um calor suave cresce em seu peito, uma sensação de conexão,"
                                + "\ncomo se, apesar do esquecimento, algo ainda fosse familiar.\n\n"
                                + "\nEles te garantem que, apesar da perda de memória, você nunca esteve sozinho e sempre terá o apoio deles. Mesmo sem"
                                + "\nlembranças específicas, o amor e o vínculo inquebrável entre vocês começam a preencher o vazio na sua mente.\n\n"
                                + "\nJuntos, você e seus pais irão reconstruir o que foi perdido. Não será fácil, mas a chance de recomeçar com o apoio \n"
                                + "\ndeles traz um novo sentido à sua vida."
                                + "\n==========================================================================================================================="
                                + "\n                                                        FIM.\n"
                                + "\n===========================================================================================================================");

                voltarAoMenu();
                break;
            } else if (!(resp.equals("b")) && tentativa == 2) {
                if (resp.equals("k") || resp.equals("o")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, resp);
                }
                System.out.println("Não foi dessa vez! Voltando ao menu...");
                voltarAoMenu();
            } else {
                if (resp.equals("k") || resp.equals("o")) {
                    pararCronometro();
                    verificarChamandoCompanheiro(companheiroEscolhido, resp);
                }
                System.out
                        .println("Você tem só mais uma chance! Se não ficará preso para sempre na Cidade Dos Doces\n");
                tentativa++;
            }

        } while (tentativa <= 2);
    }

    // Métodos auxiliares e de "Configurações"
    public static class TextoAnimado {

        public static void aparecerTexto(String texto, int delay) {
            for (char letra : texto.toCharArray()) {
                System.out.print(letra);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println();
        }
    }

    public static String verificarResposta(String resp, Scanner entrada) {

        boolean respostaCorreta = false;

        do {
            if (!resp.equals("a") && !resp.equals("b") && resp.equals("c") && resp.equals("d")) {
                System.out.println("Resposta inválida! Por favor, escolha entre as alternativas A, B, C ou D.");
                resp = entrada.next().toLowerCase();
            } else {
                respostaCorreta = true;
            }
        } while (!respostaCorreta);

        return resp;
    }

    // em cada desafio deve ter esse método
    public static void verificarChamandoCompanheiro(String companheiroEscolhido, String resp) {
        if (habilidadeUsada) {
            System.out.println(companheiroEscolhido + ": infelizmente não posso ajudar, já utilizei minha habilidade");
            return;
        }

        if (companheiroEscolhido == "Kira" && resp.equalsIgnoreCase("k")) {
            habilidadeKira();
        } else if (companheiroEscolhido == "Orion" && resp.equalsIgnoreCase("o")) {
            habilidadeOrion();
        } else {
            System.out.println("A digital enviada não é de seu companheiro.");
        }

    }

    public static void habilidadeKira() {
        Random random = new Random();
        Scanner entrada = new Scanner(System.in);
        habilidadeUsada = true;

        String questao1 = "\nQual é a principal diferença entre os laços while e for em Java?\n" +
                "a) O laço while é utilizado para iterações com um número conhecido de repetições, enquanto o for é utilizado para iterações com um número desconhecido.\n"
                +
                "b) O laço for é mais eficiente que o while em todas as situações.\n" +
                "c) O laço while verifica a condição antes de executar o bloco de código, enquanto o for verifica a condição após a execução.\n"
                +
                "d) A principal diferença está na sintaxe, não havendo diferenças funcionais significativas.";

        String questao2 = "\nConsidere o seguinte código:" + "int i = 0;\n" +
                "while (i < 10) {\n" +
                "    if (i % 2 == 0) {\n" +
                "        continue;\n" +
                "    }\n" +
                "    System.out.println(i);\n" +
                "    i++;\n" +
                "}\n" + "Qual será a saída desse código?\n" + "a) Os números pares de 0 a 9.\n" +
                "b) Os números ímpares de 1 a 9.\n" +
                "c) Os números ímpares de 0 a 8.\n" +
                "d) Um loop infinito.";

        String questao3 = "\nQual das seguintes afirmações sobre o laço do-while em Java é FALSA?\n" +
                "a) O bloco de código dentro do do-while é executado pelo menos uma vez.\n" +
                "b) A condição de parada é verificada no final do loop.\n" +
                "c) O do-while é mais eficiente que o while em todas as situações.\n" +
                "d) O do-while pode ser utilizado para criar loops infinitos.";

        String questao4 = "Considere o seguinte código: \n" + "int x = 10;\n" +
                "do {\n" +
                "    x--;\n" +
                "    if (x % 3 == 0) {\n" +
                "        break;\n" +
                "    }\n" +
                "    System.out.print(x + \" \");\n" +
                "} while (x > 0);\n" + "Qual será a saída desse código?\n" +
                "a) 9 8 7 6\n" +
                "b) 9 6 3\n" +
                "c) 9 8 7 6 5 4 3\n" +
                "d) Um loop infinito.";

        String questoes[] = { questao1, questao2, questao3, questao4 };
        String respostasCorretas[] = { "c", "b", "c", "b" };

        int indiceAleatorio = random.nextInt(questoes.length); // embaralhando questões
        String pergunta = questoes[indiceAleatorio];
        String respostaCorreta = respostasCorretas[indiceAleatorio];

        TextoAnimado.aparecerTexto(pergunta, 5);
        int tentativa = 1;
        boolean acertou = false;
        cronometro();

        do {
            System.out.print("\nDigite sua resposta: ");
            String resp = entrada.next().toLowerCase();
            resp = verificarResposta(resp, entrada); // resp rcebe o valor digitado dentro da função verificarResposta()

            if (resp.equals(respostaCorreta)) {
                pararCronometro();
                acertou = true;
                System.out.print("Parabéns! Resposta correta\n");
                retomarHistoria(entrada);
            } else {
                if (tentativa <= 2) {
                    System.out.println("Resposta errada! Você tem mais uma tentativa");
                    tentativa++;
                } else {
                    pararCronometro();
                    System.out.println("Não foi dessa vez... Voltando ao menu");
                    voltarAoMenu();
                }
            }
        } while (tentativa <= 2 && !acertou);

        entrada.close();
    }

    public static void habilidadeOrion() {
        habilidadeUsada = true;

        String questao1 = "O valor de incremento dentro do laço também é importante. Pergunte-se: quanto você deve aumentar o valor de i a cada iteração para garantir que apenas múltiplos de 4 sejam gerados? ";
        String questao2 = "Pense em um número que seja ímpar (resto 1 quando dividido por 2) e ao mesmo tempo divisível por 3, e que seja menor que 10. ";
        String questao3 = " Para saber se um número n é primo, basta verificar se ele não é divisível por nenhum número entre 2 e a raiz quadrada de n. Se encontrar algum número que divida exatamente o número n, então n não é primo. Caso contrário, ele é primo. ";
        String questao4 = "Reflita sobre o fato de que, no do-while, a condição de execução vem após o bloco de código, garantindo que ele seja executado pelo menos uma vez, enquanto no while, a condição é verificada antes da execução do bloco, podendo impedir a execução caso a condição seja falsa desde o início.";
        String questao5 = "No seu código, quando i é igual a 3, o continue faz com que a impressão de 3 seja pulada, mas os outros valores de i são impressos normalmente.\n"
                + //
                "\n" + //
                "Reflita sobre isso para entender qual é a saída correta. ";
        String questao6 = "Pense em um laço que seja mais direto para iterar de maneira controlada sobre um conjunto de elementos, onde você sabe exatamente o número de iterações que precisa fazer. Isso torna o for uma escolha mais adequada. ";
        String questao7 = "Pense em um loop que começa com o valor 3 e vai somando de 3 em 3 até atingir ou ultrapassar 15. O critério correto é garantir que o incremento seja de 3 e a condição de parada permita incluir o número 15 ";
        String questao8 = "Pense em uma solução simples e direta para percorrer todos os elementos do array e verificar se cada número é divisível por 2. Não é necessário ordenar o array ou usar técnicas complexas, apenas contar os pares durante a iteração. ";
        // 8 e 9 sao iguais
        String questao9 = "Pense em uma solução simples e direta para percorrer todos os elementos do array e verificar se cada número é divisível por 2. Não é necessário ordenar o array ou usar técnicas complexas, apenas contar os pares durante a iteração. ";
        String questao10 = "Verifique a declaração da variável, a condição de parada e o incremento.";
        String questao11 = "Pense no comportamento de um laço que depende de uma condição para continuar. Quando a condição não é mais satisfeita, o que acontece com o laço?";
        String questao12 = "Pense em um laço que ajusta o intervalo de pesquisa, sempre dividindo ao meio, até encontrar o elemento ou determinar que ele não existe.";
        String questao13 = "Reflita sobre a mudança repentina na situação: a princesa começa a se comportar de forma estranha e a sua verdadeira identidade é revelada. Pense na conexão entre o comportamento dela e o que ela realmente representa.";

        String questoes[] = { questao1, questao2, questao3, questao4, questao5, questao6, questao7, questao8, questao9,
                questao10, questao11, questao12, questao13 };

        int tentativa = 1;
        do {
            String pergunta = questoes[progresso2 - 1];
            TextoAnimado.aparecerTexto(pergunta, 5);
            tentativa++;
        } while (tentativa <= 1);
        
    }

    public static void retomarHistoria(Scanner entrada) { 
        switch (posicao) { // posição que o jogador está
            case 1:
                caminho(entrada);
                break;
            case 2:
            //Cidade dos doces 
                gatoSorridente(entrada);
                break;
            case 3:
                desafio3escolherPilula(entrada);
                break;
            case 4:
            //Casa abandonadda
                desafioBuracoNegro(entrada);
                break;
            case 5:
                gatoSorridente(entrada);
                break;
            case 6:
                desafioEspelhos(entrada);
                break;
            case 7:
                if (pilula.equals("Azul")) {
                    desafio05PilulaAzul(entrada);
                } else {
                    desafio05PilulaVermelha(entrada);
                }

            default:
                break;
        }
    }

    public static void cronometro() {
        cronometroAtivo = true; // ativando o cronometro sempre que a função cronometro é chamada
        timer = new Timer();
        int tempoTotalEmSegundos = 120;

        TimerTask tarefa = new TimerTask() {
            int tempoRestante = tempoTotalEmSegundos;

            public void run() {
                if (tempoRestante == 0) {
                    System.out.println("\nO tempo acabou!");
                    timer.cancel();
                    voltarAoMenu();
                } else if (!cronometroAtivo) {
                    timer.cancel();
                }

                tempoRestante--;

                int minutos = tempoRestante / 60;
                int segundos = tempoRestante % 60;

                String tempoFormatado = String.format("%02d:%02d ", minutos, segundos);
                System.out.print("\rTempo restante: " + tempoFormatado);
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 1000);/*
                                                    * intervalo para iniciar e em quanto tempo é executado (1000
                                                    * milesegundos) 1seg
                                                    */
    }

    public static void pararCronometro() {
        cronometroAtivo = false;
        if (timer != null) { // se o timer não for inicializo ou não tiver valor para a contagem
            timer.cancel();
        }
    }
}
