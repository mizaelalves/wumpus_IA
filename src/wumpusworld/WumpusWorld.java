package wumpusworld;

import java.util.Vector;
/**
 * Inicia a classe para o programa Wumpus World. O programa tem 
 * 3 opções: 1) Inicia o GUI onde o Wumpus World pode resolver etapa
 * por etapa um agente 2) Inicia uma simulação com mundos randomicos 
 * acima do numero de jogos ou 3) Inicia uma simulação com os mundos 
 * lidos no arquivo map.txt.
 * 
 * @author Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class WumpusWorld {

    /**
     * @param args argumento de linha de comando
     */
    public static void main(String[] args)
    {
        WumpusWorld ww = new WumpusWorld();
    }
    
    /**
     * Inicia o programa.
     * 
     */
    public WumpusWorld()
    {
        String option = Config.getOption();
        
        if (option.equalsIgnoreCase("gui"))
        {
            showGUI();
        }
        if (option.equalsIgnoreCase("sim"))
        {
            runSimulator();
        }
        if (option.equalsIgnoreCase("simdb"))
        {
            runSimulatorDB();
        }
    }
    
    /**
     * Inicia o programa no modo GUI.
     */
    private void showGUI()
    {
        GUI g = new GUI();
    }
    
    /**
     * Inicia o programa no simulador no modo
     * de leitura do arquivo
     */
    private void runSimulatorDB()
    {
        MapReader mr = new MapReader();
        Vector<WorldMap> maps = mr.readMaps();
        
        double totScore = 0;
        for (int i = 0; i < maps.size(); i++)
        {
            World w = maps.get(i).generateWorld();
            totScore += (double)runSimulation(w);
        }
        totScore = totScore / (double)maps.size();
        System.out.println("Average score: " + totScore);
    }
    
    /**
     * Inicia o programa no modo de simulação
     * com mapas randomicos.
     */
    private void runSimulator()
    {
        double totScore = 0;
        for (int i = 0; i < 10; i++)
        {
            WorldMap w = MapGenerator.getRandomMap(i);
            totScore += (double)runSimulation(w.generateWorld());
        }
        totScore = totScore / (double)10;
        System.out.println("Average score: " + totScore);
    }
    
    /**

     * Inicia o solucionador do agente para o mundo específico
     * @param w Wumpus World
     * @return Pontuação alcançada
     */
    private int runSimulation(World w)
    {
        int actions = 0;
        Agent a = new MyAgent(w);
        while (!w.gameOver())
        {
            a.doAction();
            actions++;
        }
        int score = w.getScore();
        System.out.println("Simulation ended after " + actions + " actions. Score " + score);
        return score;
    }
}
