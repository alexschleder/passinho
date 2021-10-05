public class Num extends Exp{
    private int valor;

    public Num(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    Exp smallstep(Estado e) {
        return this;
    }

    @Override
    public String toString() {
        return Integer.toString(valor);
    }
}
