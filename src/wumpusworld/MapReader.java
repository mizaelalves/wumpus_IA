package wumpusworld;

import java.io.*;
import java.util.Vector;
/**
 * This class read maps from a file.
 * 
 * @author Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class MapReader
{
    private Vector<WorldMap> maps;
    private String mapFilename;
    
    /**
     * Cria uma nova instancia da classe.
     */
    public MapReader()
    {
        mapFilename = Config.getMapfile();
        maps = new Vector<WorldMap>();
    }
    
    /**
     * Le os mapas a partir de uma arquivo de mara e retorna
     * o vetor com os mapas. O programa existe se um error é
     * encontrado.
     * 
     * @returnRetorna uma lista de objetos do mapa, ou vazio se a lista não for encontrada.
     */
    public Vector<WorldMap> readMaps()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(mapFilename));
            
            WorldMap wm = null;
            String line = reader.readLine();
            while (line != null)
            {
                line = line.toUpperCase();
                
                //Novo mapa
                if (line.startsWith("NEW"))
                {
                    String[] t = split(line);
                    int size = Integer.parseInt(t[1]);
                    wm = new WorldMap(size);
                }
                
                //Poço
                if (line.startsWith("P"))
                {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addPit(x,y);
                }
                
                //Wumpus
                if (line.startsWith("W"))
                {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addWumpus(x,y);
                }
                
                //Ouro
                if (line.startsWith("G"))
                {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addGold(x,y);
                }
                
                //Fim do mapa
                if (line.startsWith("END"))
                {
                    maps.add(wm);
                }
                
                line = reader.readLine();
            }
            
            reader.close();
        }
        catch (Exception ex)
        {
            
        }
        
        //Adiciona algum mapa aleatório
        maps.add(MapGenerator.getRandomMap(42));
        maps.add(MapGenerator.getRandomMap(1977));
        maps.add(MapGenerator.getRandomMap(1990));
        
        return maps;
    }
    
    /**
     * Pula uma linha com o espaço em branco delimitado
     * 
     * @param line A linha foi pulada
     * @return Tokens
     */
    private String[] split(String line)
    {
        return line.split(" ");
    }
}
