package View;

import Model.Funcionario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Entrada
        Scanner stdin = new Scanner(System.in);

        // Objeto
        Funcionario f1 = new Funcionario();

        // Variáveis locais
        byte menu;                    // Opção do Menu
        byte menuInsalubre;           // Menu da tela de insalubridade
        String trash;                 // Variável pra armazenar o "Pressione ENTER pra continuar"
        String nome;                  // Para validar Nome
        String cpf;                   // Para validar CPF
        int horasTrabalhadas;         // Horas trabalhadas no mês
        double valeTransporte = 0.0;  // Valor mensal do vale transporte necessário
        double valeRefeicao = 0.0;    // Valor mensal do vale refeição necessário

        // Loop principal
        do {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("1- Cadastrar Dados\n" +
                    "2- Ver Dados Cadastrados\n" +
                    "3- Ver salário bruto\n" +
                    "4- Ver salário Família\n" +
                    "5- Ver Valor Vale Transporte\n" +
                    "6- Ver Insalubridade\n" +
                    "7- Ver INSS\n" +
                    "8- Ver Valor Refeição\n" +
                    "9- Ver total Horas Extras\n" +
                    "10- Ver Salário Final\n" +
                    "0- Sair\n");
            menu = stdin.nextByte();

            switch (menu) {
                case 0:
                    break;
                case 1:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                    System.out.println("--CADASTRO--");

                    // NOME
                    System.out.print("Digite o nome do colaborador: ");
                    nome = stdin.next();

                    if (nome.length() < 2) {
                        System.out.println("Nome Inválido!");
                        break;
                    } else {
                        f1.setNome(nome);
                    }

                    // CPF
                    System.out.print("Digite o CPF do colaborador: ");
                    cpf = stdin.next();

                    if (cpf.length() < 11) {
                        System.out.println("CPF Inválido!");
                        break;
                    } else {
                        f1.setCpf(cpf);
                    }

                    // Cargo
                    System.out.print("Digite o cargo do colaborador: ");
                    f1.setCargo(stdin.next());

                    // Valor Hora
                    System.out.print("Digite o valor recebido por hora: ");
                    f1.setValorHora(stdin.nextDouble());

                    // Quantidade de horas
                    System.out.print("Digite a quantidade de horas trabalhadas no mês: ");
                    horasTrabalhadas = stdin.nextInt();

                    if (horasTrabalhadas > 220) {
                        System.out.println("Escravizando o coitado hein? Escala maior que 220 não é previsto pela CLT!");
                        break;
                    } else {
                        f1.setQuantidadeHoras(horasTrabalhadas);
                    }

                    // Horas Extras 50
                    System.out.print("Digite o número de horas extras (50%) trabalhadas no mês: ");
                    f1.setHorasExtras50(stdin.nextDouble());

                    // Horas Extras 100
                    System.out.print("Digite o número de horas extras (100%) trabalhadas no mês: ");
                    f1.setHorasExtras100(stdin.nextDouble());

                    // Dependentes
                    System.out.print("Digite o número de dependentes: ");
                    f1.setDependentes(stdin.nextInt());

                    // insalubridade
                    do {
                        System.out.println("Digite o nível de insalubridade: ");
                        System.out.println("1 – Sem Insalubridade\n" +
                                "2 – Mínimo\n" +
                                "3 – Médio\n" +
                                "4 – Máximo");
                        menuInsalubre = stdin.nextByte();
                        if ((menuInsalubre < 1) || (menuInsalubre > 4)) {
                            System.out.println("Opção Inválida! Tente novamente!");
                            // System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        }
                    }
                    while ((menuInsalubre < 1) || (menuInsalubre > 4));
                    // Botei o (byte) pq senao o IDEA nao rodava
                    f1.setTipoInsalubridade((byte) (menuInsalubre - 1));

                    // Ó VALEOVALEOVALE
                    System.out.print("Digite o valor mensal do vale-transporte: ");
                    valeTransporte = stdin.nextDouble();

                    // VALEVALEVALE
                    System.out.print("Digite o valor DIÁRIO do vale-refeição: ");
                    valeRefeicao = stdin.nextDouble();

                    // Break final
                    break;
                // Dados cadastrados
                case 2:
                    System.out.println(f1);
                    break;
                // Salário Bruto
                case 3:
                    System.out.println("Salário Bruto: " + String.format("R$ %.2f", f1.calcularSalarioBruto()));
                    break;
                // Salário Família
                case 4:
                    System.out.println("O Salário-Família total é: " + String.format("R$ %.2f", f1.calcularSalarioFamilia()));
                    break;
                // Valor Vale Transporte
                case 5:
                    System.out.println("Vale-transporte: " + String.format("R$ %.2f", f1.calcularValeTransporte(valeTransporte)));
                    break;
                // Insalubridade
                case 6:
                    System.out.println("Insalubridade: " + String.format("R$ %.2f", f1.calcularInsalubridade()));
                    break;
                // INSS
                case 7:
                    System.out.println("INSS: " + String.format("R$ %.2f", f1.calcularInss()));
                    break;
                // Vale Refeição
                case 8:
                    System.out.println("Vale-refeição: " + String.format("R$ %.2f", f1.calcularValeRefeicao(valeRefeicao)));
                    break;
                // Total horas extras
                case 9:
                    System.out.println("Total de Horas-Extras: " + String.format("R$ %.2f", f1.calcularTotalHorasExtras()));
                    break;
                // Salário Líquido
                case 10:
                    System.out.println("Salário Total/Líquido: " + String.format("R$ %.2f", f1.calcularSalarioLiquido(valeTransporte, valeRefeicao)));
                    break;
                default:
                    System.out.println("Opção Inválida! Tente novamente!");
            }

            System.out.print("Pressione ENTER para continuar...");
            trash = stdin.next();

        }
        while (menu != 0);
    }
}
