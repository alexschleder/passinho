public class OpArit extends Exp {
  private char op;

  OpArit(char op) {
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
        return String.valueOf(op);
    }
}