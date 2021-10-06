public class Prog extends Exp
{
    protected Prog tipo;
    private class Skip extends Prog
    {
        public Skip()
        {
            super(false);
        }
    
        @Override
        Exp smallstep(Estado e) {
            return this;
        }
    
        @Override
        public String toString() {
            return "Skip";
        }
    }

    private class Atrib extends Prog
    {
        Var variavel;
        Exp valor;

        public Atrib(Var variavel, Exp valor)
        {
            super(false);
            this.variavel = variavel;
            this.valor = valor;
        }

        @Override
        Exp smallstep(Estado e) 
        {   
            if (!(valor instanceof Num) && (!(valor instanceof Bool)))
            {
                return new Prog(variavel, valor.smallstep(e));
            }
            else if (valor instanceof Num)
            {
                Num n = (Num) valor;
                e.adicionar(variavel.getValorVar(), n.getValor());
                return new Prog();
            } 
            else 
            {
                Bool b = (Bool) valor;
                e.adicionar(variavel.getValorVar(), b.getValorBoolean());
                return new Prog();
            }
        }
    
        @Override
        public String toString() {
            return variavel.getValorVar() + ":=" + valor.toString();
        }
    }

    private class Conseq extends Prog
    {
        Prog c1;
        Prog c2;

        public Conseq(Prog c1, Prog c2)
        {
            super(false);
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        Exp smallstep(Estado e) {
            if (c1.tipo instanceof Skip) return c2;
            else return new Prog((Prog) c1.smallstep(e), c2);
        }
    
        @Override
        public String toString() {
            return c1.toString() + ";" + c2.toString();
        }
    }
    
    private class WhileDo extends Prog
    {
        BoolExp b;
        Prog c;

        public WhileDo(BoolExp b, Prog c)
        {
            super(false);
            this.b = b;
            this.c = c;
        }

        @Override
        Exp smallstep(Estado e) {
            if (!(b instanceof Bool)) return new Prog((BoolExp)b.smallstep(e), c);
            else
            {
                Bool bool = (Bool) b;
                if (bool.getValorBoolean()) return new Prog(b, new Prog(c, new Prog(b, c)), new Prog());
                else return new Prog();
            }
        }
    
        @Override
        public String toString() {
            return "While " + b.toString() + " do " + c.toString();
        }
    }

    private class DoWhile extends Prog
    {
        Prog c;
        BoolExp b;

        public DoWhile(Prog c, BoolExp b)
        {
            super(false);
            this.c = c;
            this.b = b;
        }

        @Override
        Exp smallstep(Estado e) {
            return new Prog(c, new Prog(b, c));
        }
    
        @Override
        public String toString() {
            return "Do " + c.toString() + " while " + b.toString();
        }
    }

    private class IfThenElse extends Prog
    {
        BoolExp b;
        Prog c1;
        Prog c2;

        public IfThenElse(BoolExp b, Prog c1, Prog c2)
        {
            super(false);
            this.b = b;
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        Exp smallstep(Estado e) {
            if (!(b instanceof Bool)) return new Prog((BoolExp) b.smallstep(e), c1, c2);
            else 
            {
                Bool bool = (Bool) b;
                if (bool.getValorBoolean()) return c1;
                else return c2;
            }
        }
    
        @Override
        public String toString() {
            return "If " + c1.toString() + " then " + c2.toString();
        }
    }

    private class IfThen extends Prog
    {
        BoolExp b;
        Prog c1;

        public IfThen(BoolExp b, Prog c1)
        {
            super(false);
            this.b = b;
            this.c1 = c1;
        }

        @Override
        Exp smallstep(Estado e) {
            if (!(b instanceof Bool)) return new Prog((BoolExp) b.smallstep(e), c1);
            else 
            {
                Bool bool = (Bool) b;
                if (bool.getValorBoolean()) return c1;
                else return new Prog();
            }
        }
    
        @Override
        public String toString() {
            return "If " + b.toString() + " then " + c1.toString();
        }
    }

    private class Swap extends Prog
    {
        Var x;
        Var y;

        public Swap(Var x, Var y)
        {
            super(false);
            this.x = x;
            this.y = y;
        }

        @Override
        Exp smallstep(Estado e) {
            Tipo t = e.lerTipo(x.getValorVar());
            if (t == Tipo.integer)
            {
                int i = e.lerInt(x.getValorVar());
                if (e.lerTipo(y.getValorVar()) == Tipo.integer)
                {
                    e.adicionar(x.getValorVar(), e.lerInt(y.getValorVar()));
                    e.adicionar(y.getValorVar(), i);
                }
                else   
                {
                    e.adicionar(x.getValorVar(), e.lerBool(y.getValorVar()));
                    e.adicionar(y.getValorVar(), i);
                }
            }
            else 
            {
                boolean b = e.lerBool(x.getValorVar());
                if (e.lerTipo(y.getValorVar()) == Tipo.integer)
                {
                    e.adicionar(x.getValorVar(), e.lerInt(y.getValorVar()));
                    e.adicionar(y.getValorVar(), b);
                }
                else  
                {
                    e.adicionar(x.getValorVar(), e.lerBool(y.getValorVar()));
                    e.adicionar(y.getValorVar(), b);
                }
            }
            return new Prog();
        }
    
        @Override
        public String toString() {
            return "swap" + x.toString() + " " + y.toString();
        }
    }

    protected Prog(boolean a)
    {
        return;
    }

    public Prog() 
    {
        tipo = new Skip();
    }

    //x:=a || x:=b
    public Prog(Var x, Exp e)
    {
        tipo = new Atrib(x, e);
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
    //if b then c1 = (if b then c1 else skip)
    public Prog(BoolExp b, Prog c1, Prog c2)
    {
        if (c2.tipo instanceof Skip) tipo = new IfThen(b, c1);
        else tipo = new IfThenElse(b, c1, c2);
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
        if (tipo instanceof Skip) return true;
        else return false;
    }
}