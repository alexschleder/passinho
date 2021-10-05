public class OpBool extends Exp{

    private Exp exp1;
    private Exp exp2;
    private String op;

    public OpBool(Exp exp1, String op, Exp exp2){
        this.exp1 = exp1;
        this.op = op;
        this.exp2 = exp2;
    }

    @Override
    public String toString(){
        return exp1 + op + exp2;
    }

    @Override
    Exp smallstep(Estado e){
        if(!(exp1 instanceof Bool)){
            return new OpBool(exp1.smallstep(e), op, exp2);
        }else if(!(exp2 instanceof Bool)){
            return new OpBool(exp1, op, exp2.smallstep(e));
        }else{
            if(op.equals("&&")){
                return new Bool(((Bool)exp1).getValorBoolean() && ((Bool)exp2).getValorBoolean());
            }else{
                return new Bool(((Bool)exp1).getValorBoolean() || ((Bool)exp2).getValorBoolean());
            }
        }
    }
}