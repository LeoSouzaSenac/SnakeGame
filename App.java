// Declaração do pacote para organização do código
package com.example;

// Importação da classe JFrame do pacote javax.swing, que é usada para criar a interface gráfica da aplicação
import javax.swing.*;

// Classe principal que contém o método main para iniciar a aplicação
public class App {
    public static void main(String[] args) throws Exception {
        // Configuração do tamanho do tabuleiro
        int boardWidth = 600;
        int boardHeight = boardWidth;

        // Criação de uma janela Swing (frame) para o jogo Snake
        JFrame frame = new JFrame("Snake");

        // Define a visibilidade da janela
        // frame.setVisible(true); // Esta chamada é movida para o final do método

        // Configuração do tamanho da janela
        frame.setSize(boardWidth, boardHeight);

        // Centraliza a janela na tela
        frame.setLocationRelativeTo(null);

        // Impede que o usuário redimensione a janela
        frame.setResizable(false);

        // Define a operação padrão quando a janela é fechada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criação de uma instância do jogo Snake (SnakeGame)
        SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);

        // Adiciona o componente SnakeGame à janela
        frame.add(snakeGame);

        // Faz a janela se ajustar ao tamanho do componente
        frame.pack();

        // Define o foco no componente SnakeGame para capturar eventos de teclado
        snakeGame.requestFocus();

        // Torna a janela visível
        frame.setVisible(true);
    }
}
