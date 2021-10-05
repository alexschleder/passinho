public class BoolExp extends Exp{

    private Exp exp1;
    private Exp exp2;
    private Exp op;

    public BoolExp(Exp exp1, Exp op, Exp exp2){
        this.exp1 = exp1;
        this.op = op;
        this.exp2 = exp2;
    }

    public BoolExp(Exp op, Exp exp1){ //!b
        this.op = op;
        this.exp1 = exp1;
    }

    // 𝑏 ∷= 𝑏𝑙 | 𝑥 | 𝑏1 𝑏𝑜𝑝 𝑏2 | !𝑏 | 𝑎1 𝑟𝑜𝑝 𝑎2 

    @Override
    public String toString(){
        return exp1 + op.toString() + exp2;
    }

    @Override
    Exp smallstep(Estado e){
        if(op.toString().equals("!")){
            if(!(exp1 instanceof Bool)){
                return new BoolExp(op, exp1.smallstep(e));
            }else{
                return new Bool(!((Bool)exp1).getValorBoolean());
            }
        }
        if(op instanceof OpBool){
                if(!(exp1 instanceof Bool)){
                    return new BoolExp(exp1.smallstep(e), op, exp2);
                }else if(!(exp2 instanceof Bool)){
                    return new BoolExp(exp1, op, exp2.smallstep(e));
                }else{
                    if(op.toString().equals("&&")){
                        return new Bool(((Bool)exp1).getValorBoolean() && ((Bool)exp2).getValorBoolean());
                    }else{
                        return new Bool(((Bool)exp1).getValorBoolean() || ((Bool)exp2).getValorBoolean());
                    }
                }
            }else{ //OpRel
                if(!(exp1 instanceof Num)){ //isso já confere se é num ou AritExp?
                    return new BoolExp(exp1.smallstep(e), op, exp2);
                }else if(!(exp2 instanceof Num)){
                    return new BoolExp(exp1, op, exp2.smallstep(e));
                }else{
                    if(op.toString().equals("=")){ //if pra cada comparação: > < =
                        return new Bool(((Num)exp1).getValor() == ((Num)exp2).getValor());
                    }else if(op.toString().equals(">")){
                        return new Bool(((Num)exp1).getValor() > ((Num)exp2).getValor());
                    }else{
                        return new Bool(((Num)exp1).getValor() < ((Num)exp2).getValor());
                    }
                }
            }
    }
}