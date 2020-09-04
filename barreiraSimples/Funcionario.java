public class Funcionario {

    private int codigo;
    private double salario;

    private double ir;
    private double inss;
    private double prevPrivada;
    private double planoDeSaude;
    private double totalImpostos = 0;
    public Object sem;

    public Funcionario(int id, double salario) {
        this.codigo = id;
        this.salario = salario;

    }

    public void setIR(double desconto) {
        this.ir = desconto;
    }

    public void setINSS(double desconto) {
        this.inss = desconto;
    }

    public void setPrevPrivada(double desconto) {
        this.prevPrivada = desconto;
    }

    public void setPlanoDeSaude(double desconto) {
        this.planoDeSaude = desconto;
    }

    public void atualizarImpostos(double desconto) {
        this.totalImpostos += desconto;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public double getSalario() {
        return this.salario;
    }

    public double getTotalImpostos() {
        return this.totalImpostos;
    }

    public double getIR() {
        return this.ir;
    }

    public double getINSS() {
        return this.inss;
    }

    public double getPrevPrivada() {
        return this.prevPrivada;
    }

    public double getPlanoDeSaude() {
        return this.planoDeSaude;
    }

}
