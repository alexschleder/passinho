public class Bool extends BoolExp{
    private boolean valorBoolean;

    public Bool(boolean valorBoolean){
        super(valorBoolean);
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