public class Bool extends Exp{
    private boolean valorBoolean;

    public Bool(boolean valorBoolean){
        this.valorBoolean = valorBoolean;
    }

    public boolean getValorBoolean(){
        return valorBoolean;
    }

    @Override
    Exp smallstep(Estado e){
        return this;
    }

    @Override
    public String toString(){
        return String.valueOf(valorBoolean);
    }

}