public class Var extends Exp {
  private String var;

  public Var(String var){
    this.var = var;
  }

  public String getValorVar(){
    return var;
  }

  @Override
  Exp smallstep(Estado e) {
    if(e.lerTipo(var) == Tipo.integer){
      return new Num(e.lerInt(var));
    } else if(e.lerTipo(var) == Tipo.bool){
      return new Bool(e.lerBool(var));
    } else {
      //TODO: undefined
    }
      
  }

     @Override
    public String toString(){
        return String.valueOf(var);
    }
}