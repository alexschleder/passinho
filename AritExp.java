public class AritExp extends Exp {
  Exp exp1;
  Exp exp2;
  OpArit op;

  public AritExp(Exp exp1, OpArit op, Exp exp2) {
    this.exp1 = exp1;
    this.exp2 = exp2;
    this.op = op;
  }

  protected AritExp(int valor) {
}

  @Override
  Exp smallstep(Estado e) {
    if (!(exp1 instanceof Num)) {
      return new AritExp(exp1.smallstep(e), op, exp2);
    } else if (!(exp2 instanceof Num)) {
      return new AritExp(exp1, op, exp2.smallstep(e));
    } else {
      if (op.getValor() == '+') {
        return new Num(((Num) exp1).getValor() + ((Num) exp2).getValor());
      } else if (op.getValor() == '-') {
        return new Num(((Num) exp1).getValor() - ((Num) exp2).getValor());
      } else { // op == *
        System.out.println("aaaa");
        return new Num(((Num) exp1).getValor() * ((Num) exp2).getValor());
      }
    }
  }

  @Override
  public String toString() {
    return exp1.toString() + " " + op.toString() + " " + exp2.toString();
  }
}