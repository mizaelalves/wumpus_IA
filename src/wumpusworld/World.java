package wumpusworld;

import java.util.Vector;

/**
 * Essa classe lida com uma instancia do Wumpus World. Ela contem o estado do
 * mundo, onde essa ações ção disponíveis, e atualizações do mundo quando uma
 * ação é executada.
 * 
 * @author  Michael Oliveira, Mizael, João Lucas, Edith Carollaine
 */
public class World 
{
    private int size;
    private String[][] w;
    private int pX = 1;
    private int pY = 1;
    private boolean wumpusAlive = true;
    private boolean hasArrow = true;
    private boolean isInPit = false;
    private boolean hasGold = false;
    private boolean gameOver = false;   
    private int score = 0;
    
    //Constantes da direção do Player.
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;
    
    //Inicia a direção
    private int dir = DIR_RIGHT;
    
    //Contantes da percepção.
    public static final String BREEZE = "B";
    public static final String STENCH = "S";
    public static final String PIT = "P";
    public static final String WUMPUS = "W";
    public static final String GLITTER = "G";
    public static final String GOLD = "T";
    public static final String UNKNOWN = "U";
    
    //Constantes da ação.
    public static final String A_MOVE = "m";
    public static final String A_GRAB = "g";
    public static final String A_CLIMB = "c";
    public static final String A_SHOOT = "s";
    public static final String A_TURN_LEFT = "l";
    public static final String A_TURN_RIGHT = "r";
    
    /**
     * Cria um novo Wumpus World. O Wumpus World trabalha com
     * qualquer tamanho 4 ou maior, mas somente o tamanho 4 é
     * suportado pelo GUI.
     * 
     * @param size Tamanho do mundo.
     */
    public World(int size)
    {
        this.size = size;
        w = new String[size+1][size+1];
        
        for (int x = 0; x <= size; x++)
        {
            for (int y = 0; y <= size; y++)
            {
                w[x][y] = UNKNOWN;
            }
        }
        
        setVisited(1, 1);
    }

    public World(String[][] w1,int size, int px, int py, int dirr)
    {
        this.size = size;
        pX=px;
        pY=py;
        dir = dirr;
        this.w=w1;
    }
    /**
     * Retorna a pontuação atual.
     * 
     * @return Apontuação.
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Returns the size of this Wumpus World.
     * Retorna o tamanho do mundo.
     * 
     * @return O mundo
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Verifica se o jogo acabou ou não.
     * 
     * @return True se o jogo acabou, false if não.
     */
    public boolean gameOver()
    {
        return gameOver;
    }
    
    /**
     * Retorna a Posição X do player.
     * 
     * @return Posição X.
     */
    public int getPlayerX()
    {
        return pX;
    }
    
    /**
     * Retorna a Posição Y do player.
     * 
     * @return Posição Y.
     */
    public int getPlayerY()
    {
        return pY;
    } 
           
    /**
     * Verifica se o player está em um poço e precisa pular.
     * 
     * @return True se está em um poço, false se não.
     */
    public boolean isInPit()
    {
        return isInPit;
    }
    
    /**
     * Verifica se o player tem uma flecha.
     * 
     * @return True se o playser tem uma flecha, False se não.
     */
    public boolean hasArrow()
    {
        return hasArrow;
    }
    
    /**
     * Verifica se o Wumpus está vivo
     * 
     * @return True se o Wumpus está vivo, False se não.
     */
    public boolean wumpusAlive()
    {
        return wumpusAlive;
    }
    
    /**
     * Verifica se o player carrega o ouro.
     * 
     * @return True se o player esta com o ouro, False se não
     */
    public boolean hasGold()
    {
        return hasGold;
    }
    
    /**
     * Retorna a direção do player.
     * 
     * @return Direção
     */
    public int getDirection()
    {
        return dir;
    }
    
    /**
     * Verifica se o bloco tem uma brisa. Retorna false
     * se a posição é invalida, ou contem um bloco desconhecido.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o bloco tem uma brisa
     */
    public boolean hasBreeze(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(BREEZE))
            return true;
        else
            return false;
    }
    
    /**
     * Verifica se o bloco tem um fedor. Retorna false
     * se a posição é invalida, ou contem um bloco desconhecido.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o bloco tem um fedor.
     */
    public boolean hasStench(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(STENCH))
            return true;
        else
            return false;
    }
    
    /**
     * Verifica se o bloco tem um brilho. Retorna false
     * se a posição é invalida, ou contem um bloco desconhecido.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True o bloco tem um brilho
     */
    public boolean hasGlitter(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(GLITTER))
            return true;
        else
            return false;
    }
    
    /**
     * Verifica se o quadrado é um poço. Retorna false
     * se a posição é invalida, ou contem um bloco desconhecido.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o bloco é um poço
     */
    public boolean hasPit(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(PIT))
            return true;
        else
            return false;
    }
    
    /**
     * Verifica se o Wumpus esta no bloco. Retorna false
     * se a posição é invalida ou o bloco é desconhecido.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o Wumpus esta no bloco
     */
    public boolean hasWumpus(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(WUMPUS))
            return true;
        else
            return false;
    }
    
     /**
     * Verifica se o player esta no bloco.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o player esta no bloco
     */
    public boolean hasPlayer(int x, int y)
    {
        if (pX == x && pY == y)
        {
            return true;
        }
        return false;
    }
    
     /**
     * Verifica se o bloco foi visitado. Retorna false
     * se a posição é invalida
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o bloco foi visitado
     */
    public boolean isVisited(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        
        return !isUnknown(x, y);
    }
    
    /**
     * verifica se o bloco é desconhecido, retorna false
     * se é uma posição invalida.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True if the square is unknown
     */
    public boolean isUnknown(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        
        if (w[x][y].contains(UNKNOWN))
            return true;
        else
            return false;  
    }
    
    /**
     * Verifica se o bloco é valido, dentro 
     * dos limites do mundo
     * 
     * @param x Posição X
     * @param y Posição Y
     * @return True se o bloco é valido
     */
    public boolean isValidPosition(int x, int y)
    {
        if (x < 1) return false;
        if (y < 1) return false;
        if (x > size) return false;
        if (y > size) return false;
        return true;
    }
    
    /**
     * Adiciona percepção ao bloco.
     * 
     * @param x Posição X
     * @param y Posição Y
     * @param s Adiciona a percepção
     */
    private void append(int x, int y, String s)
    {
        if (!isValidPosition(x,y))
            return;
        
        if (!w[x][y].contains(s))
        {
            w[x][y] += s;
        }
    }
    
    /**
     * Adiciona o Wumpus a um bloco.
     * 
     * @param x Posição X
     * @param y Posição Y
     */
    public void addWumpus(int x, int y)
    {
        if (!w[x][y].contains(WUMPUS))
        {
            append(x,y,WUMPUS);
            append(x-1,y,STENCH);
            append(x+1,y,STENCH);
            append(x,y-1,STENCH);
            append(x,y+1,STENCH);
        }
    }
    
    public World cloneWorld()
    {
        //this.size = size;
        String[][] w1 = new String[size+1][size+1];
        int px,py,dirr;
        px=pX;
        py=pY;
        dirr=dir;
        for (int x = 0; x <= size; x++)
        {
            for (int y = 0; y <= size; y++)
            {
                w1[x][y]= w[x][y];
            }
        }
        
        return new World(w1,size,px,py,dirr);
    }
    
    /**
     * Adiciona o poçõ no quadrado
     * 
     * @param x Posição X
     * @param y Posição Y
     */
    public void addPit(int x, int y)
    {
        if (!w[x][y].contains(PIT))
        {
            append(x,y,PIT);
            append(x-1,y,BREEZE);
            append(x+1,y,BREEZE);
            append(x,y-1,BREEZE);
            append(x,y+1,BREEZE);
        }
    }
    
    /**

     * Adiciona o tesouro em um bloco
     * 
     * @param x Posição X
     * @param y Posição Y
     */
    public void addGold(int x, int y)
    {
        if (!w[x][y].contains(GLITTER))
        {
            append(x,y,GLITTER);
        }
    }
    
    /**
     * Set se o bloco foi visitado.
     * 
     * @param x Posição X
     * @param y Posição Y
     */
    private void setVisited(int x, int y)
    {
        if (w[x][y].contains(UNKNOWN))
        {
            w[x][y] = w[x][y].replaceAll(UNKNOWN, "");
        }
    }
    
    /**
     * Executa uma ação no mundo
     * 
     * @param a Ve ação constatate
     * @return True se a ação foi um sucesso, senão ação falhou
     */
    public boolean doAction(String a)
    {
        if (gameOver) return false;
        
        //Essa função custa um ponto
        score -= 1;
        
        if (a.equals(A_MOVE))
        {
            if (!isInPit)
            {
                if (dir == DIR_LEFT) return move(pX-1,pY);
                if (dir == DIR_RIGHT) return move(pX+1,pY);
                if (dir == DIR_UP) return move(pX,pY+1);
                if (dir == DIR_DOWN) return move(pX,pY-1);
            }
        }
        if (a.equals(A_TURN_LEFT))
        {
            dir--;
            if (dir < 0) dir = 3;
            return true;
        }
        if (a.equals(A_TURN_RIGHT))
        {
            dir++;
            if (dir > 3) dir = 0;
            return true;
        }
        if (a.equals(A_GRAB))
        {
            if (hasGlitter(pX,pY))
            {
                w[pX][pY] = w[pX][pY].replaceAll(GLITTER, "");
                score += 1000;
                hasGold = true;
                gameOver = true;
                return true;
            }
        }
        if (a.equals(A_SHOOT))
        {
            if (hasArrow)
            {
                score -= 10;
                hasArrow = false;
                shoot();
                return true;
            }
        }
        if (a.equals(A_CLIMB))
        {
            isInPit = false;
        }
        
        //Ação falhou
        return false;
    }
    
    /**
     * Verifica se o Wumpus levou um tiro.
     */
    private void shoot()
    {
        if (dir == DIR_RIGHT)
        {
            for (int x = pX; x <= size; x++)
            {
                if (w[x][pY].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_LEFT)
        {
            for (int x = pX; x >= 0; x--)
            {
                if (w[x][pY].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_UP)
        {
            for (int y = pY; y <= size; y++)
            {
                if (w[pX][y].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_DOWN)
        {
            for (int y = pY; y >= 0; y--)
            {
                if (w[pX][y].contains(WUMPUS)) removeWumpus();
            }
        }
    }
    
    /**
     * Remove o Wumpus (e o cheiro) do Wumpus World.
     * Usado quando o Wumpus leva um hit.
     */
    private void removeWumpus()
    {
        for (int x = 1; x <= 4; x++)
        {
            for (int y = 1; y <= 4; y++)
            {
                w[x][y] = w[x][y].replaceAll(WUMPUS, "");
                w[x][y] = w[x][y].replaceAll(STENCH, "");
            }
        }
        
        wumpusAlive = false;
    }
    
    /**
     * Executes a move forward to a new square.
     * 
     * @param nX Nova posição para X
     * @param nY Nova posição para Y
     * @return True se o movimento foi ocorrido com sucesso, False para o outro lado
     */
    private boolean move(int nX, int nY)
    {
        //verifica se é valida
        if (!isValidPosition(nX, nY))
        {
            return false;
        }
        
        pX = nX;
        pY = nY;
        
        setVisited(pX, pY);
        
        if(hasWumpus(pX,pY))
        {
            score -= 1000;
            gameOver = true;
        }
        if (hasPit(pX,pY))
        {
            score -= 1000;
            isInPit = true;
        }
        
        return true;    
    }
}
