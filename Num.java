public class Num extends AritExp{
    private int valor;

    public Num(int valor) {
        super(valor);
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
