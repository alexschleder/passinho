public class Main
{
  public static void main(String[] args) {
    BoolExp exp1 = new BoolExp(new Var("x"), new OpRel('<'), new Num(3));
    AritExp soma = new AritExp(new Var("x"), new OpArit('+'), new Num(1));
    Prog atrib = new Prog(new Var("x"), soma);
    //Exp ift = new Prog(exp2, atrib, new Prog());
    Exp whl= new Prog(exp1, atrib);
    System.out.println("teste");
    Exp expressao = whl;
    //Exp expressao = new BoolExp(exp2, new OpBool("&&"), exp1);
    //Exp expressao = new Prog(exp2, new Prog(), new Prog());
    //Estado [x->3]
    Estado estado = new Estado();
    estado.adicionar("x", 0);
    //Avaliação small-step
    while (!(expressao instanceof Num) && !(expressao instanceof Bool)) {
        System.out.println(expressao);
        System.out.println(estado);
        expressao = expressao.smallstep(estado);
        if (expressao instanceof Prog)
        {
          Prog p = (Prog) expressao;
          if (p.isSkip()) break; 
        }
    }
    System.out.println("estado: " + estado);
    System.out.println(expressao);
}
}
