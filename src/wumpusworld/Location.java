package wumpusworld;

/**
 * Essa classe é usada por um agente quando procura por
 * um novo bloco para se mover, Ele contem aposição para um
 * quadrado, e uma prioridade. Baixo valores prévios = alta prioridade
 * 
 * @author Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class Location implements Comparable
{
    /**
     * posição X
     */
    public int x;
    /**
     * posição Y
     */
    public int y;
    /**
     * Valor da prioridade
     */
    public int prio;
    
    /**
     * Cria uma nova instancia da localização
     * 
     * @param x posição X
     * @param y posição Y
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.prio = 0;
    }
    
    /**
     * Cria uma nova instancia da localização
     * 
     * @param x posição X
     * @param y posição Y
     * @param prio Valor da prioridade
     */
    public Location(int x, int y, int prio)
    {
        this.x = x;
        this.y = y;
        this.prio = prio;
    }
    
    /**
     * Usado para sortear a listar o valor das 
     * possibilidades = alta prioridade.
     * 
     * @param o Localização do objeto
     * @return 1 if prio(o) > prio(this). -1 if prio(o) > prio(this). 0 se não.
     */
    public int compareTo(Object o)
    {
        Location l = (Location)o;
        if (l.prio < prio) return 1;
        if (l.prio > prio) return -1;
        return 0;
    }
}
