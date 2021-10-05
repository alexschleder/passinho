import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;

public class Estado {
    private Map<String, Integer> mapeamentoInt = new HashMap<>();
    private Map<String, Boolean> mapeamentoBool = new HashMap<>();
    /**
     * Adiciona uma nova variável ao ambiente.
     * @param variavel
     * @param valor
     */
    public void adicionar(String variavel, int valor) {
        mapeamentoInt.put(variavel,valor);
        mapeamentoBool.remove(variavel);
    }

    public void adicionar(String variavel, boolean valor) {
        mapeamentoBool.put(variavel,valor);
        mapeamentoInt.remove(variavel);
    }

    public Tipo lerTipo(String variavel)
    {
        if (mapeamentoInt.get(variavel) != null) return Tipo.integer;
        if (mapeamentoBool.get(variavel) != null) return Tipo.bool;
        return Tipo.undefined;
    }

    /**
     * Observa o valor atual da variável no ambiente.
     * Assume a variável já existe no ambiente.
     * @param variavel
     * @return valor da variável
     */
    public int lerInt(String variavel) {
        return mapeamentoInt.get(variavel);
    }

    public boolean lerBool(String variavel) {
        return mapeamentoBool.get(variavel);
    }

    @Override
    public String toString() {
        StringBuilder ambiente = new StringBuilder("[");
        Iterator<Entry<String,Integer>> iterador = mapeamentoInt.entrySet().iterator();
        while (iterador.hasNext()) {
            Entry<String,Integer> e = iterador.next();
            ambiente.append(e.getKey());
            ambiente.append(" |-> ");
            ambiente.append(e.getValue());
            if (iterador.hasNext()) {
                ambiente.append(", ");
            }
        }
        ambiente.append("\n");
        Iterator<Entry<String,Boolean>> iterador2 = mapeamentoBool.entrySet().iterator();
        while (iterador2.hasNext()) {
            Entry<String,Boolean> e = iterador2.next();
            ambiente.append(e.getKey());
            ambiente.append(" |-> ");
            ambiente.append(e.getValue());
            if (iterador2.hasNext()) {
                ambiente.append(", ");
            }
        }
        ambiente.append("]");
        return ambiente.toString();
    }
}
