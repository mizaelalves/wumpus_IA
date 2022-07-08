package wumpusworld;

import java.io.*;

/**
 *Le o arquivo de configuração config.txt
 * 
 * @author Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class Config 
{
    /**
     * Verifica a opção da aplicação('gui', 'sim' or 'simdb').
     * 
     * @return A opção. Por padrão é 'gui'. 
     */
    public static String getOption()
    {
        String option = "gui";
        try
        {
            BufferedReader r = new BufferedReader(new FileReader("config.txt"));
            String line = r.readLine();
            while (line != null)
            {
                if (line.startsWith("Option="))
                {
                    String[] tokens = line.split("=");
                    if (tokens[1].equalsIgnoreCase("gui")) option = "gui";
                    if (tokens[1].equalsIgnoreCase("sim")) option = "sim";
                    if (tokens[1].equalsIgnoreCase("simulator")) option = "sim";
                    if (tokens[1].equalsIgnoreCase("simdb")) option = "simdb";
                    if (tokens[1].equalsIgnoreCase("simulatordb")) option = "simdb";
                }
                line = r.readLine();
            }
            r.close();
        }
        catch (Exception ex)
        {
            option = "gui";
        }
        return option;
    }
    
    /**
     * Retorna o path do arquivo de mapa.
     * 
     * @return Path do arquivo de mapa, ou limpo se a string não for encontrada. 
     */
    public static String getMapfile()
    {
        String mapfile = "";
        try
        {
            BufferedReader r = new BufferedReader(new FileReader("config.txt"));
            String line = r.readLine();
            while (line != null)
            {
                if (line.startsWith("Mapfile="))
                {
                    String[] tokens = line.split("=");
                    mapfile = tokens[1];
                }
                line = r.readLine();
            }
            r.close();
        }
        catch (Exception ex)
        {
            mapfile = "";
        }
        return mapfile;
    }
}
