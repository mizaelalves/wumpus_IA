package wumpusworld;

import java.util.Random;

/**
 * A classe gera um mapa Wumpus World aleatório
 *
 * @author Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class MapGenerator 
{
    /**
     * Gera um mapa aleatório do Wumpus World.
     * 
     * @param seed Semeia para o aleatório. Mesma semente sempre resulta no mesmo mapa aleatório.
     * @return Gera um Wumpus World Gera um
     */
    public static WorldMap getRandomMap(int seed)
    {
        Random rnd = new Random(seed);
        WorldMap w = new WorldMap(4);
        
        addRandomWumpus(w,rnd);
        addRandomGold(w,rnd);
        addRandomPit(w,rnd);
        addRandomPit(w,rnd);
        addRandomPit(w,rnd);
        
        return w;
    }
    
    /**
     * Adiciona o poço em um quadrado aleatório
     * 
     * @param w Wumpus World
     * @param r Randomizer
     */
    private static void addRandomPit(WorldMap w, Random r)
    {
        boolean valid = false;
        while (!valid)
        {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1) && !w.hasPit(x, y))
            {
                valid = true;
                w.addPit(x, y);
            }
        }
    }
    
    /**
     * Adiciona o Wumpus em um bloco aleatório.
     * 
     * @param w Wumpus World
     * @param r Randomizer
     */
    private static void addRandomWumpus(WorldMap w, Random r)
    {
        boolean valid = false;
        while (!valid)
        {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1))
            {
                valid = true;
                w.addWumpus(x, y);
            }
        }
    }
    
    /**
     * Adiciona o ouro em um bloco aleatório.
     * 
     * @param w Wumpus World
     * @param r Randomizer
     */
    private static void addRandomGold(WorldMap w, Random r)
    {
        boolean valid = false;
        while (!valid)
        {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1))
            {
                valid = true;
                w.addGold(x, y);
            }
        }
    }
    
    /**
     * 
     * 
     * @param rnd
     * @return 
     */
    private static int rnd(Random rnd)
    {
        return rnd.nextInt(4) + 1;
    }
}
