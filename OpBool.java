public class OpBool extends Exp{

    private String op;

    public OpBool(String op){
        this.op = op;
    }

    public String getValor(){
        return op;
    }

    @Override
    public String toString(){
        return op;
    }

    @Override
    Exp smallstep(Estado e){
        return this;
    }
}