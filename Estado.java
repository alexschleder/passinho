import java.util.HashMap;
import java.util.Map;

public class Estado {
    private Map<String, Integer> mapeamento = new HashMap<>();
    /**
     * Adiciona uma nova variável ao ambiente.
     * @param variavel
     * @param valor
     */
    public void adicionar(String variavel, int valor) {
        mapeamento.put(variavel,valor);
    }

    /**
     * Atualiza o valor de uma variável no ambiente.
     * Assume a variável já existe no ambiente.
     * @param variavel
     * @param valor
     */
    public void atualizar(String variavel, int valor) {
        mapeamento.replace(variavel,valor);
    }

    /**
     * Observa o valor atual da variável no ambiente.
     * Assume a variável já existe no ambiente.
     * @param variavel
     * @return valor da variável
     */
    public int ler(String variavel) {
        return mapeamento.get(variavel);
    }

    @Override
    public String toString() {
        StringBuilder ambiente = new StringBuilder("[");
        var iterador = mapeamento.entrySet().iterator();
        while (iterador.hasNext()) {
            var e = iterador.next();
            ambiente.append(e.getKey());
            ambiente.append(" |-> ");
            ambiente.append(e.getValue());
            if (iterador.hasNext()) {
                ambiente.append(", ");
            }
        }
        ambiente.append("]");
        return ambiente.toString();
    }
}
