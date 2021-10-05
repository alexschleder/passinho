public class OpRel extends Exp
{
    private char op;

    public OpRel(char op)
    {
        if (op != '=' || op != '>' || op != '<') return; //todo
        this.op = op;
    }

    public char getValor()
    {
        return op;
    }

    @Override
    Exp smallstep(Estado e) {
        return this;
    }

    @Override
    public String toString() {
        return op+"";
    }
}