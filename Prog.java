public class Prog extends Exp
{
    Prog tipo;
    private class Skip extends Prog
    {
        @Override
        Exp smallstep(Estado e) {
            return this;
        }
    
        @Override
        public String toString() {
            return "Skip";
        }

        @Override
        public IsSkip()
        {
            return true;
        }
    }

    private class Atrib extends Prog
    {
        Var variavel;
        Exp valor;

        public Atrib(String variavel, Exp valor)
        {
            this.variavel = variavel;
            this.valor = valor;
        }

        @Override
        Exp smallstep(Estado e) 
        {
            if (valor instanceof Num) e.adicionar(variavel.get, valor);
        }
    
        @Override
        public String toString() {
            //return variavel.getValorVar + ":=" + ;
        }
    }

    private class Conseq
    {
        Prog c1;
        Prog c2;

        public Conseq(Prog c1, Prog c2)
        {
            this.c1 = c1;
            this.c2 = c2;
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
    
    private class WhileDo extends Prog
    {
        BoolExp b;
        Prog c;

        public WhileDo(BoolExp b, Prog c)
        {
            this.b = b;
            this.c = c;
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

    private class DoWhile extends Prog
    {
        Prog c;
        BoolExp b;

        public DoWhile(Prog c, BoolExp b)
        {
            this.c = c;
            this.b = b;
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

    private class IfThenElse extends Prog
    {
        BoolExp b;
        Prog c1;
        Prog c2;

        public IfThenElse(BoolExp b, Prog c1, Prog c2)
        {
            this.b = b;
            this.c1 = c1;
            this.c2 = c2;
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

    private class IfThen
    {
        BoolExp b;
        Prog c1;

        public IfThen(BoolExp b, Prog c1)
        {
            this.b = b;
            this.c1 = c1;
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

    private class Swap
    {
        Var x;
        Var y;

        public Swap(Var x, Var y)
        {
            this.x = x;
            this.y = y;
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

    //x:=a
    public Prog(Var x, AritExp a)
    {
        tipo = new Atrib(x, a);
    }

    //x:=b
    public Prog(Var x, BoolExp b)
    {
        tipo = new Atrib(x, a);
    }

    //c1;c2
    public Prog(Prog c1, Prog c2)
    {
        tipo = new Conseq(c1, c2);
    }

    //while b do c
    public Prog(BoolExp b, Prog c)
    {
        tipo = new WhileDo(b, c);
    }

    //do c while b
    public Prog(Prog c, BoolExp b)
    {
        tipo = new DoWhile(c, b);
    }

    //if b then c1 else c2
    public Prog(BoolExp b, Prog c1, Prog c2)
    {
        tipo = new IfThenElse(b, c1, c2);
    }

    //if b then c1
    public Prog ()
    {
        //TODO
        //tipo = new IfThen(b, c1);
    }

    //swap x y
    public Prog(Var x, Var y)
    {
        tipo = new Swap(x, y);
    }

    @Override
    Exp smallstep(Estado e) {
        return tipo.smallstep(e);
    }

    @Override
    public String toString() {
        return tipo.toString();
    }

    public boolean isSkip()
    {
        return false;
    }
}