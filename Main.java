public class Main
{
  public static void main(String[] args) {
    BoolExp exp1 = new BoolExp(new Bool(false), new OpBool("||"), new Bool(false));
    BoolExp exp2 = new BoolExp(new Bool(true), new OpBool("||"), new Bool(false));
    Prog atrib = new Prog(new Var("x"), exp2);
    Exp ift = new Prog(exp1, atrib);
    System.out.println("teste");
    Exp expressao = ift;
    //Exp expressao = new BoolExp(exp2, new OpBool("&&"), exp1);
    //Exp expressao = new Prog(exp2, new Prog(), new Prog());
    //Estado [x->3]
    Estado estado = new Estado();
    //Avaliação small-step
    while (!(expressao instanceof Num) && !(expressao instanceof Bool)) {
        System.out.println(expressao);
        //System.out.println(estado);
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
