public class Var extends Exp {
  


  @Override
    Exp smallstep(Estado e) {
        return this;
    }
}