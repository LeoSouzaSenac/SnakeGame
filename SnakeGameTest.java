package com.example;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

public class SnakeGameTest {

    private SnakeGame snakeGame;

    @Before
    public void setUp() {
        // Configuração inicial antes de cada teste
        snakeGame = new SnakeGame(800, 600);  // Substitua com os tamanhos reais do tabuleiro
    }

    @Test
    public void testMoveUp() {
        // Simula o pressionamento da tecla UP
        snakeGame.keyPressed(createKeyEvent(KeyEvent.VK_UP));
        
        // Verifica se a velocidade foi atualizada corretamente
        assertEquals(0, snakeGame.velocityX);
        assertEquals(-1, snakeGame.velocityY);
    }
    
    @Test
    public void testMoveDown() {
        // Simula o pressionamento da tecla DOWN
        snakeGame.keyPressed(createKeyEvent(KeyEvent.VK_DOWN));
        
        // Verifica se a velocidade foi atualizada corretamente
        assertEquals(0, snakeGame.velocityX);
        assertEquals(1, snakeGame.velocityY);
    }

    @Test
    public void testMoveLeft() {
        // Simula o pressionamento da tecla LEFT
        snakeGame.keyPressed(createKeyEvent(KeyEvent.VK_LEFT));
        
        // Verifica se a velocidade foi atualizada corretamente
        assertEquals(-1, snakeGame.velocityX);
        assertEquals(0, snakeGame.velocityY);
    }

    @Test
    public void testMoveRight() {
        // Simula o pressionamento da tecla RIGHT
        snakeGame.keyPressed(createKeyEvent(KeyEvent.VK_RIGHT));
        
        // Verifica se a velocidade foi atualizada corretamente
        assertEquals(1, snakeGame.velocityX);
        assertEquals(0, snakeGame.velocityY);
    }

    // Método auxiliar para criar um evento KeyEvent
    private KeyEvent createKeyEvent(int keyCode) {
        return new KeyEvent(snakeGame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }
}
