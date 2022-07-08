package wumpusworld;

import java.util.Vector;
import java.awt.Point;

/**
 * Essa classe representa o mapa do Wumpus World  lido
 * a partir do arquivo.
 * @author Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class WorldMap
{
    private int size;
    private Point wumpus;
    private Point gold;
    private Vector<Point> pits;
    
    /**
     * Cria uma nova instancia de mapa.
     * 
     * @param size Tamanho do mundo.
     */
    public WorldMap(int size)
    {
        this.size = size;
        pits = new Vector<Point>();
    }
    
    /**
     * Adiciona o  Wumpus.
     * 
     * @param x posição X
     * @param y posição Y
     */
    public void addWumpus(int x, int y)
    {
        wumpus = new Point(x,y);
    }
    
    /**
     * Adiciona o ouro.
     * 
     * @param x posição X
     * @param y posição Y
     */
    public void addGold(int x, int y)
    {
        gold = new Point(x,y);
    }
    
    /**
     * Adds a pit. The map can have any number of pits.
     * Adiciona um poço. O mapa não pode ter qualquer numero de poços.
     * 
     * @param x posição X
     * @param y posição Y
     */
    public void addPit(int x, int y)
    {
        pits.add(new Point(x,y));
    }
    
    /**
     * Retorna o tamanho do mapa.
     * 
     * @return O tamanho
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Retorna a posição do Wumpus.
     * 
     * @return A posição.
     */
    public Point getWumpus()
    {
        return wumpus;
    }
    
    /**
     * Retorna a posição do ouro.
     * 
     * @return A posição.
     */
    public Point getGold()
    {
        return gold;
    }
    
    /**
     * Retorna a posição de todos os poços.
     * 
     * @return Uma lista da posição de todos os poços.
     */
    public Vector<Point> getPits()
    {
        return pits;
    }
    
    /**
     * Checks if there is a pit in the specified location.
     * Verifica se aqui existe um poço nessa posição especifica
     * 
     * @param x  coordenada X
     * @param y  coordenada Y
     * @return True se aqui tem um poço, false se não
     */
    public boolean hasPit(int x, int y)
    {
        for (Point p:pits)
        {
            if (p.x == x && p.y == y)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gera uma instancia do Wumpus World a partir desse mapa
     * 
     * @return instancia do Wumpus World instancia
     */
    public World generateWorld()
    {
        World w = new World(size);
        w.addWumpus(wumpus.x, wumpus.y);
        w.addGold(gold.x, gold.y);
        for (int i = 0; i < pits.size(); i++)
        {
            w.addPit(pits.get(i).x, pits.get(i).y);
        }
        return w;
    }
}
