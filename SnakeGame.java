// Declaração do pacote para organização do código
package com.example;

// Importação de classes necessárias para a implementação do jogo Snake
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

// Classe que representa o jogo Snake e herda funcionalidades de JPanel, ActionListener e KeyListener
public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    // Classe interna que representa uma unidade (Tile) no jogo
    private class Tile {
        int x;
        int y;

        // Construtor da classe Tile
        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }  

    // Variáveis para o tamanho do tabuleiro
    int boardWidth;
    int boardHeight;
    int tileSize = 25;
    
    // Variáveis para a cobra (snake)
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    // Variáveis para a comida (food)
    Tile food;
    Random random;

    // Lógica do jogo
    int velocityX;
    int velocityY;
    Timer gameLoop;

    // Sinaliza o fim do jogo
    boolean gameOver = false;

    // Construtor da classe SnakeGame
    SnakeGame(int boardWidth, int boardHeight) {
        // Inicialização das variáveis do tabuleiro
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        // Inicialização da cabeça da cobra e do corpo
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        // Inicialização da comida e do gerador de números aleatórios
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        // Velocidade inicial da cobra
        velocityX = 1;
        velocityY = 0;

        // Inicialização do timer do jogo
        gameLoop = new Timer(100, this); // Intervalo entre frames em milissegundos
        gameLoop.start();
    }	
    
    // Método para desenhar os componentes na tela
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Método para desenhar os elementos do jogo
    public void draw(Graphics g) {
        // Linhas do grid
        for(int i = 0; i < boardWidth/tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize); 
        }

        // Desenho da comida em vermelho
        g.setColor(Color.red);
        g.fill3DRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize, true);

        // Desenho da cabeça da cobra em verde
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize, true);

        // Desenho do corpo da cobra
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize, true);
        }

        // Desenho da pontuação na tela
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
        else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    // Método para posicionar a comida em uma posição aleatória
    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }

    // Método para movimentar a cobra
    public void move() {
        // Comer a comida
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Movimentar o corpo da cobra
        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Movimentar a cabeça da cobra
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Condições de fim de jogo
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            // Colisão com a cabeça da cobra
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        // Colisão com as bordas do tabuleiro
        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > boardWidth ||
            snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > boardHeight ) {
            gameOver = true;
        }
    }

    // Método para verificar colisões entre dois Tiles
    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    // Método chamado a cada intervalo de tempo pelo timer do jogo
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }  

    // Método chamado quando uma tecla é pressionada
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = 1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = -1;
            velocityY = 0;
        }
    }

    // Métodos não utilizados, mas necessários devido à implementação da interface KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
