package Model;

public class Funcionario {
    // CONSTANTES
    // Salário mínimo no RS para trabalhadores técnicos de nível médio, tanto em cursos integrados como subsequentes ou concomitantes.
    // FONTE: https://g1.globo.com/rs/rio-grande-do-sul/noticia/2019/05/28/reajuste-de-34percent-no-salario-minimo-regional-do-rs-e-aprovado-na-assembleia.ghtml
    private final double SALARIO_MINIMO_RS = 1567.81;
    // Atributos
    private String nome;
    private String cpf;
    private String cargo;
    private double valorHora;
    private double quantidadeHoras;
    private double horasExtras50;
    private double horasExtras100;
    private int dependentes;
    private byte tipoInsalubridade;

    // Acessores
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public double getValorHora() {
        return valorHora;
    }
    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }
    public double getQuantidadeHoras() {
        return quantidadeHoras;
    }
    public void setQuantidadeHoras(double quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }
    public double getHorasExtras50() {
        return horasExtras50;
    }
    public void setHorasExtras50(double horasExtras50) {
        this.horasExtras50 = horasExtras50;
    }
    public double getHorasExtras100() {
        return horasExtras100;
    }
    public void setHorasExtras100(double horasExtras100) {
        this.horasExtras100 = horasExtras100;
    }
    public int getDependentes() {
        return dependentes;
    }
    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }
    public byte getTipoInsalubridade() {
        return tipoInsalubridade;
    }
    public void setTipoInsalubridade(byte tipoInsalubridade) {
        this.tipoInsalubridade = tipoInsalubridade;
    }

    // Métodos
    public double calcularSalarioBruto() {
        // SALÁRIO BRUTO é valor hora multiplicado pela quantidade de horas.
        return this.quantidadeHoras * this.valorHora;
    }

    public double calcularValeTransporte(double valorTransporteMensal) {
        double salarioBruto = this.calcularSalarioBruto();
        double seisPorCento = salarioBruto * 0.06;

        // Se o 6% do salário bruto for maior que o valorTransporteMensal
        if (seisPorCento > valorTransporteMensal) {
            // retornar zero
            return 0;
        }
        else {
            // Senão será 6% do salário bruto.
            return seisPorCento;
        }
    }

    public double calcularSalarioFamilia() {
        double salarioBruto = this.calcularSalarioBruto();
        double beneficioPorDependente = 0;

        if (salarioBruto <= 907.77) {
            beneficioPorDependente = 46.54;
        }
        else if ((salarioBruto >= 907.78) && (salarioBruto <= 1364.43)) {
            beneficioPorDependente = 32.80;
        }
        else {
            beneficioPorDependente = 0;
        }

        // Porque tudo que é multiplicado por zero é zero!
        return this.dependentes * beneficioPorDependente;
    }

    public double calcularInsalubridade() {
        double salarioBruto = this.calcularSalarioBruto();
        byte tipoInsalubridade = this.tipoInsalubridade;

        // Não sei pq mas o IDEA sugeriu assim
        double grauInsalubridade = switch (tipoInsalubridade) {
            case 1 -> 0.1;
            case 2 -> 0.2;
            case 3 -> 0.4;
            default -> 0.0;
        };

        return salarioBruto * grauInsalubridade;
    }

    public double calcularInss() {
        double salarioBruto = this.calcularSalarioBruto();
        double aliquotaINSS;

        if (salarioBruto <= 1751.81) {
            aliquotaINSS = 0.08;
        } else if ((salarioBruto >= 1751.82) && (salarioBruto <= 2919.72)) {
            aliquotaINSS = 0.09;
        } else if ((salarioBruto >= 2919.73) && (salarioBruto <= 5839.45)) {
            aliquotaINSS = 0.11;
        } else {
            aliquotaINSS = 0.27;
        }

        return salarioBruto * aliquotaINSS;
    }

    public double calcularValeRefeicao(double valorVale) {
        return (valorVale * 25) * 0.20;

    }

    public double calcularValorExtra50() {
        return this.horasExtras50 * (this.valorHora * 1.5);
    }

    public double calcularValorExtra100() {
        return this.horasExtras100 * (this.valorHora * 2);
    }

    public double calcularTotalHorasExtras() {
        return this.calcularValorExtra50() + this.calcularValorExtra100();
    }

    public double calcularSalarioLiquido(double valorTransporteMensal, double valorVale) {
        return this.calcularSalarioBruto() + ((this.calcularTotalHorasExtras() + this.calcularInsalubridade() + this.calcularSalarioFamilia()) - (this.calcularValeTransporte(valorTransporteMensal) + this.calcularValeRefeicao(valorVale) + this.calcularInss()));
    }

    // toString
    @Override
    public String toString() {
        return "Nome: " + nome + '\'' +
                "\nCPF: " + cpf + '\'' +
                "\nCargo: " + cargo + '\'' +
                "\nValor da Hora: " + valorHora +
                "\nHoras Trabalhadas: " + quantidadeHoras +
                "\nHoras Extras (50%): " + horasExtras50 +
                "\nHoras Extras (100%): " + horasExtras100 +
                "\nDependentes: " + dependentes +
                "\nTipo de Insalubridade: " + tipoInsalubridade;
    }
}
