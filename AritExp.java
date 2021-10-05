public class AritExp extends Exp {
  Exp exp1;
  Exp exp2;
  OpArit op;

  public AritExp(Exp exp1){
    this.exp1 = exp1;
  }

  public AritExp(Exp exp1, Exp exp2, OpArit op){
    this.exp1 = exp1;
    this.exp2 = exp2;
    this.op = op;
  }

  public AritExp(AritExp exp1, Exp exp2, OpArit op ){
    
  }

  public AritExp(AritExp exp1, AritExp exp2, OpArit op ){
    
  }



  @Override
    Exp smallstep(Estado e) {
        return this;
    }

  @Override
  public String toString() {
      return "";
  }
}